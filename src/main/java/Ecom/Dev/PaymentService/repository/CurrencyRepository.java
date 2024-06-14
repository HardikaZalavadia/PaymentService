package Ecom.Dev.PaymentService.repository;

import Ecom.Dev.PaymentService.entity.Currency;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CurrencyRepository extends JpaRepository<Currency, UUID> {
}
