package dev.sunless.auth_api.configuration;

import dev.sunless.auth_api.repositories.impl.BaseRepositoryImpl;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(
        basePackages = "dev.sunless.auth_api.repositories",
        repositoryBaseClass = BaseRepositoryImpl.class
)
public class JpaConfiguration {
}
