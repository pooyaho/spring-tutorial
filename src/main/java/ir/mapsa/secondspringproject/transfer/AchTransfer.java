package ir.mapsa.secondspringproject.transfer;

import org.springframework.stereotype.Service;

@Service
public class AchTransfer implements BaseTransfer {
    @Override
    public void transfer(TransferDto transfer) {

    }

    @Override
    public boolean resolve(TransferDto dto) {
        return dto.getDestination().startsWith("IR") && dto.getAmount() < 200000000;
    }
}
