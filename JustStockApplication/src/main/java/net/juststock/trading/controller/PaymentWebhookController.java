//package net.juststock.trading.controller;
//
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("/api/payments")
//public class PaymentWebhookController {
//
//  private final CheckoutService svc;
//
//  public PaymentWebhookController(CheckoutService svc) { this.svc = svc; }
//
//  // Configure this exact URL in the payment gateway dashboard
//  @PostMapping("/razorpay/webhook")
//  public ResponseEntity<Void> razorpayWebhook(
//      @RequestHeader("X-Razorpay-Signature") String signature,
//      @RequestBody String payload
//  ) {
//    svc.handleWebhook(signature, payload);
//    return ResponseEntity.ok().build();
//  }
//}
