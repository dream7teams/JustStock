//package net.juststock.trading.controller;
//
//
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import net.juststock.trading.services.ICheckoutService;
//
//@RestController
//@RequestMapping("/api/checkout")
//public class CheckoutController {
//
//  private final ICheckoutService svc;
//
//  public CheckoutController(ICheckoutService svc) { this.svc = svc; }
//
//  // For demo: user info in headers. In prod, get from JWT (SecurityContext).
//  @PostMapping("/signal")
//  public ResponseEntity<CreateSignalCheckoutRes> checkoutSignal(
//      @RequestHeader("X-User-Id") Long userId,
//      @RequestHeader("X-User-Contact") String userContact,
//      @RequestBody CreateSignalCheckoutReq req
//  ) {
//    return ResponseEntity.ok(svc.createSignalCheckout(userId, userContact, req));
//  }
//}
