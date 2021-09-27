package org.oagBlog.blogapp.config.audit;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.UUID;

/**
 * Kim yozganini qaytaradi
 */
@Configuration
@EnableJpaAuditing
public class AuditingConfig {


    @Bean
    public AuditorAware<UUID> auditorAware(){
        return new SpringSecurityAuditAwareImpl();
    }

}
