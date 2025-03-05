package dev.zerphyis.upload.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import dev.zerphyis.upload.service.JavaMailSenderMock;

@Configuration
public class JavaMailSenderConfig {

    @Bean
    JavaMailSender javaMailSender() {
        return new JavaMailSenderMock();
    }
}
