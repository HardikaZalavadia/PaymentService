package Ecom.Dev.PaymentService.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;


import java.util.UUID;

@Entity
@Getter
@Setter
public class Payment extends BaseModel{
    private double amount;
    private UUID userId;
    private UUID orderId;
    private String transactionId;

    @OneToOne
    private Currency currency;
    @Enumerated(EnumType.STRING)

    private PaymentStatus paymentStatus;
}
