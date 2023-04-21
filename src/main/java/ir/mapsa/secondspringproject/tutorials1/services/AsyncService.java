package ir.mapsa.secondspringproject.tutorials1.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class AsyncService {

    @Autowired
    private ShaparakService shaparakService;

    @Async
    public CompletableFuture<String> doSomething(int i) {
        try {
            System.out.println(Thread.currentThread().getName());
            Thread.sleep(10000);
            if (i % 2 == 0) {
                throw new IllegalArgumentException();
            }
            return CompletableFuture.supplyAsync(() -> "Task executed!");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    //    @Scheduled(cron = "*/5 * * * * *")
    public void doSomethingScheduled() {
        if (System.currentTimeMillis() % 2 == 0) {
            throw new IllegalStateException();
        }
        System.out.println("Hello world!");
    }
}
