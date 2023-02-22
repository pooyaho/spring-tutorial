package ir.mapsa.secondspringproject;

import ir.mapsa.secondspringproject.factory.TesterClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class SecondSpringProjectApplication  implements ApplicationRunner {

    @Autowired
    private ApplicationContext applicationContext;

    public static void main(String[] args) {
        SpringApplication.run(SecondSpringProjectApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        TesterClass testerClass = applicationContext.getBean(TesterClass.class);
        testerClass.testFactory();
    }
}
