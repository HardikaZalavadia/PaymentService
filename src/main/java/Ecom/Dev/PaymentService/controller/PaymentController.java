package Ecom.Dev.PaymentService.controller;

import Ecom.Dev.PaymentService.dto.PaymentRequestDto;
import Ecom.Dev.PaymentService.service.PaymentService;
import com.razorpay.RazorpayException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaymentController {
    private PaymentService paymentService;

    @Autowired
    PaymentController(PaymentService paymentService){
        this.paymentService = paymentService;
    }

    @PostMapping("/payment") //this API called from Order service
    public ResponseEntity<String> doPayment(@RequestBody PaymentRequestDto paymentRequestDto) throws RazorpayException {
        return ResponseEntity.ok(paymentService.generatePaymentLink(paymentRequestDto));
    }

    @GetMapping("/hello")
    public ResponseEntity<String> hello(){
        return ResponseEntity.ok("Hello World! this is hardika's laptop....");
    }
}
