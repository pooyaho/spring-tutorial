package ir.mapsa.secondspringproject.transfer;

import lombok.Data;

import java.util.Map;

@Data
public class TransferDto {
    private String source;
    private String destination;
    private Long amount;

    private Map<String, String> additionalData;
}
