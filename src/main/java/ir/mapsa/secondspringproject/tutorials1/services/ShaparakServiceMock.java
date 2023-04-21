package ir.mapsa.secondspringproject.tutorials1.services;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("dev")
@ConditionalOnProperty(value = "dev.enabled", havingValue = "true")
public class ShaparakServiceMock implements ShaparakService {
    @Override
    public void settle() {
        // mock shaparak
    }
}
