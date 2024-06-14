package Ecom.Dev.PaymentService.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebhookController {

    @PostMapping("/rzp/webhook")
    public ResponseEntity<String> webhook() {
        System.out.println("Razorpay webhook callback...");
        return ResponseEntity.ok("OK");
    }
}
