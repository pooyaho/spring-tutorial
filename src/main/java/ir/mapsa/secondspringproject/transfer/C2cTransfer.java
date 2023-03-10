package ir.mapsa.secondspringproject.transfer;

import org.springframework.stereotype.Service;

@Service
public class C2cTransfer implements BaseTransfer {
    @Override
    public void transfer(TransferDto transfer) {

    }

    @Override
    public boolean resolve(TransferDto dto) {
        return !dto.getDestination().startsWith("IR") && dto.getSource().startsWith("6221");
    }
}
