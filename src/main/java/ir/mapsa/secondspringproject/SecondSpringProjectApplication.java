package ir.mapsa.secondspringproject;

import ir.mapsa.secondspringproject.tutorials1.services.StudentService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableJpaRepositories
@EnableTransactionManagement
@EnableJpaAuditing
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class SecondSpringProjectApplication {
    public static void main(String[] args) {
        SpringApplication.run(SecondSpringProjectApplication.class, args);
    }

    @Bean
    public StudentService studentService() {
        return new StudentService();
    }
}
