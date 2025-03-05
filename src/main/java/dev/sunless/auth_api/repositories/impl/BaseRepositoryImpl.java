package dev.sunless.auth_api.repositories.impl;

import dev.sunless.auth_api.exceptions.SoftDeleteException;
import dev.sunless.auth_api.models.BaseEntity;
import dev.sunless.auth_api.repositories.BaseRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class BaseRepositoryImpl<T, ID extends Serializable> extends SimpleJpaRepository<T, ID> implements BaseRepository<T, ID> {

    @PersistenceContext
    private final EntityManager entityManager;

    public BaseRepositoryImpl(JpaEntityInformation<T, ID> entityInformation, EntityManager entityManager) {
        super(entityInformation, entityManager);
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void softDeleteById(ID id) {
        T entity = this.findById(id).orElseThrow(() -> new IllegalArgumentException("Entity not found"));

        if (entityIsSoftDeleted(entity)) throw new IllegalArgumentException("Entity is already soft-deleted");
        setDeletedAtField(entity, LocalDateTime.now());
    }

    private void setDeletedAtField(T entity, LocalDateTime value) {
        if (entity instanceof BaseEntity) {
            ((BaseEntity) entity).setDeletedAt(value);
            entityManager.merge(entity);
        }
        else throw new SoftDeleteException("This entity does not inherit from the base entity");
    }

    private boolean entityIsSoftDeleted(T entity) {
        if (entity instanceof BaseEntity) return ((BaseEntity) entity).isSoftDeleted();
        else throw new SoftDeleteException("This entity does not inherit from the base entity");
    }

    @Override
    public void undeleteById(ID id) {
        T entity = this.findById(id).orElseThrow(() -> new IllegalArgumentException("Entity not found"));
        setDeletedAtField(entity, null);
    }

    @Override
    public List<T> findAllActive() {
        String jpql = """
                SELECT e FROM %s e
                WHERE e.deletedAt IS NULL
                """.formatted(getDomainClass().getName());
        return entityManager.createQuery(jpql, getDomainClass()).getResultList();
    }

    @Override
    public Optional<T> findByIdActive(ID id) {
        String jpql = """
                SELECT e FROM %S e
                WHERE e.id = :id
                AND e.deletedAt IS NULL
                """.formatted(getDomainClass().getName());
        return Optional.of(
                entityManager.createQuery(jpql, getDomainClass())
                        .setParameter("id", id)
                        .getSingleResult()
        );
    }
}
