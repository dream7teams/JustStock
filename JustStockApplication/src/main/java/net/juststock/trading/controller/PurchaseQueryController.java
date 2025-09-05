//package net.juststock.trading.controller;
//
//import net.juststock.trading.services.CheckoutService;
//import net.juststock.trading.web.dto.PurchaseView;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("/api/purchases")
//public class PurchaseQueryController {
//
//  private final CheckoutService svc;
//
//  public PurchaseQueryController(CheckoutService svc) { this.svc = svc; }
//
//  // GET /api/purchases/{id}
//  @GetMapping("/{id}")
//  public ResponseEntity<PurchaseView> view(@PathVariable Long id) {
//    return ResponseEntity.ok(svc.view(id));
//  }
//}
