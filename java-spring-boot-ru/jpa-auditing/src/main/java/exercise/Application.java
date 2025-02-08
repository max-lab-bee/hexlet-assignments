package exercise;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

// BEGIN
@SpringBootApplication
@EnableJpaAuditing

// END

public class Application {

    public static void main(String[] args) {

        SpringApplication.run(Application.class, args);
    }
}
