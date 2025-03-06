package dev.sunless.auth_api.repositories;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;
import java.util.Optional;

@NoRepositoryBean
public interface BaseRepository<T, ID> extends JpaRepository<T, ID> {

    @Transactional
    void softDeleteById(ID id);

    @Transactional
    void undeleteById(ID id);

    List<T> findAllActive();

    List<T> findAllInactive();

    Optional<T> findByIdActive(ID id);
}
