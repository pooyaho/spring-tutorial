package ir.mapsa.secondspringproject.tutorials1.services;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("!dev")
public class ShaparakServiceImpl implements ShaparakService {
    @Override
    public void settle() {
        // connect to shaparak
    }
}
