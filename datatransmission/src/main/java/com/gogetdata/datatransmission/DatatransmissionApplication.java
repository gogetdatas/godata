package com.gogetdata.datatransmission;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class DatatransmissionApplication {
    public static void main(String[] args) {
        SpringApplication.run(DatatransmissionApplication.class, args);
    }
}
