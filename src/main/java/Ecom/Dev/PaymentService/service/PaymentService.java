package Ecom.Dev.PaymentService.service;

import Ecom.Dev.PaymentService.dto.PaymentRequestDto;
import com.razorpay.RazorpayException;

public interface PaymentService {
    String generatePaymentLink(PaymentRequestDto paymentRequestDto) throws RazorpayException;
}
