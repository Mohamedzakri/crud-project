package it.mohamed.crudproject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditorAware")



public class CrudProjectApplication {

    private static final Logger logger = LoggerFactory.getLogger(CrudProjectApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(CrudProjectApplication.class, args);
        logger.info("**** CrudApp started ****");
    }

}
