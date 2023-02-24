package ir.mapsa.secondspringproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class SecondSpringProjectApplication  {


    public static void main(String[] args) {
        SpringApplication.run(SecondSpringProjectApplication.class, args);
    }

}
