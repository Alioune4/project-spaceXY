package fr.epita.discover.infrastructure.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * @author lummix (julienmeyer32@gmail.com)
 * @project spacexy
 * 24/06/2024
 */
@Configuration
@EnableJpaRepositories(basePackages = "fr.epita.discover.domain.repositories")
public class DataConfig {
}
