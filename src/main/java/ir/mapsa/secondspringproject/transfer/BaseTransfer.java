package ir.mapsa.secondspringproject.transfer;

public interface BaseTransfer {
    void transfer(TransferDto transfer);

    boolean resolve(TransferDto dto);
}
