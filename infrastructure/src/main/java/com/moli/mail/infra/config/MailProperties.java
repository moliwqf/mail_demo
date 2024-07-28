package com.moli.mail.infra.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author moli
 * @time 2024-07-26 17:45:30
 */
@Data
@Component
@ConfigurationProperties(prefix = "spring.mail")
public class MailProperties {

    private String username;
}
