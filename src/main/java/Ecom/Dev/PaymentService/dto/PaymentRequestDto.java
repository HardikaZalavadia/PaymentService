package Ecom.Dev.PaymentService.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class PaymentRequestDto {
    private long amount;
    private UUID OrderId;
    private String description;
    private String customerName;
    private String customerEmail;
    private String customerPhone;
}
