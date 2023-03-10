package ir.mapsa.secondspringproject.transfer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransferLocator {
    @Autowired
    private List<BaseTransfer> transfers;

    public void transfer(TransferDto dto) {
        for (BaseTransfer transfer : transfers) {
            if (transfer.resolve(dto)) {
                transfer.transfer(dto);
                break;
            }
        }
    }
}
