package uz.digitalone.houzingapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import javax.persistence.EntityListeners;

@Configuration
@EnableJpaAuditing
@EntityListeners(value = {AuditingEntityListener.class})
public class AuditingConfig {

    @Bean
    AuditorAware<Long> auditorAware(){return new SpringSecurityAuditConfig();}
}
