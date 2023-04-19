package ir.mapsa.secondspringproject;

import ir.mapsa.secondspringproject.tutorials1.models.Student;
import ir.mapsa.secondspringproject.tutorials1.services.CachedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.MongoTransactionManager;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.util.StopWatch;

@SpringBootApplication
@EnableJpaRepositories
@EnableTransactionManagement
@EnableJpaAuditing
@EnableMongoAuditing
//@EnableAspectJAutoProxy(proxyTargetClass = true)
@EnableAsync
@EnableScheduling
@EnableCaching
public class SecondSpringProjectApplication implements ApplicationRunner {
    @Autowired
    private CachedService cachedService;

    public static void main(String[] args) {
        SpringApplication.run(SecondSpringProjectApplication.class, args);
    }

//    @Bean
//    public StudentService studentService() {
//        return new StudentService();
//    }

    @Bean
    public MongoTransactionManager transactionManager(MongoDatabaseFactory dbFactory) {
        return new MongoTransactionManager(dbFactory);
    }


    @Override
    public void run(ApplicationArguments args) throws Exception {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start("First call");
        System.out.println(cachedService.doSomething(Student.builder().studentId("a").build()));
        stopWatch.stop();
        stopWatch.start("Second call");
        System.out.println(cachedService.doSomething(Student.builder().studentId("b").build()));
        stopWatch.stop();
        cachedService.removeCache(Student.builder().studentId("a").build());
        stopWatch.start("Third call");
        System.out.println(cachedService.doSomething(Student.builder().studentId("a").build()));
        stopWatch.stop();
        stopWatch.start("Forth call");
        System.out.println(cachedService.doSomething(Student.builder().studentId("b").build()));
        stopWatch.stop();
        System.out.println(stopWatch.prettyPrint());
    }
}
