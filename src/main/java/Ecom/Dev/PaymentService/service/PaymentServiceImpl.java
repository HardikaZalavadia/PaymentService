package Ecom.Dev.PaymentService.service;

import Ecom.Dev.PaymentService.config.RazorpayClientConfig;
import Ecom.Dev.PaymentService.dto.PaymentRequestDto;
import Ecom.Dev.PaymentService.entity.Payment;
import Ecom.Dev.PaymentService.entity.PaymentStatus;
import Ecom.Dev.PaymentService.repository.PaymentRepository;
import com.razorpay.PaymentLink;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class PaymentServiceImpl implements PaymentService{

    private RazorpayClientConfig razorpayClientConfig;
    private PaymentRepository paymentRepository;

    @Autowired
    public PaymentServiceImpl(RazorpayClientConfig razorpayClientConfig, PaymentRepository paymentRepository) {
        this.razorpayClientConfig = razorpayClientConfig;
        this.paymentRepository = paymentRepository;
    }
    @Override
    public String generatePaymentLink(PaymentRequestDto paymentRequestDTO) throws RazorpayException {
        Payment payment = new Payment();
        payment.setAmount(paymentRequestDTO.getAmount());
        payment.setOrderId(paymentRequestDTO.getOrderId());
        payment.setPaymentStatus(PaymentStatus.INPROGRESS);
        paymentRepository.save(payment);

        RazorpayClient razorpay = razorpayClientConfig.getRazorpayClient();
        JSONObject paymentLinkRequest = new JSONObject();
        paymentLinkRequest.put("amount",paymentRequestDTO.getAmount());
        paymentLinkRequest.put("currency","INR");
        paymentLinkRequest.put("accept_partial",false);
        paymentLinkRequest.put("expire_by", Instant.now().toEpochMilli() + 600000);
        paymentLinkRequest.put("reference_id", paymentRequestDTO.getOrderId());
        paymentLinkRequest.put("description", paymentRequestDTO.getDescription());
        JSONObject customer = new JSONObject();
        customer.put("name", paymentRequestDTO.getCustomerName());
        customer.put("contact",paymentRequestDTO.getCustomerPhone());
        customer.put("email",paymentRequestDTO.getCustomerEmail());
        paymentLinkRequest.put("customer",customer);
        JSONObject notify = new JSONObject();
        notify.put("sms",true);
        notify.put("email",true);
        paymentLinkRequest.put("notify",notify);
        paymentLinkRequest.put("reminder_enable",true);
        PaymentLink paymentLink = razorpay.paymentLink.create(paymentLinkRequest);
        return paymentLink.toString();
    }
}
