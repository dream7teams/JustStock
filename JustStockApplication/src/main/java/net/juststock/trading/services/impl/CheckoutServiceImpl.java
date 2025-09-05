//package net.juststock.trading.services.impl;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import java.time.Instant;
//
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import net.juststock.trading.domain.AdvisorySignal;
//import net.juststock.trading.domain.PaymentEvent;
//import net.juststock.trading.domain.SignalPurchase;
//import net.juststock.trading.domain.User;
//import net.juststock.trading.repository.AdvisorySignalRepo;
//import net.juststock.trading.repository.PaymentEventRepo;
//import net.juststock.trading.repository.SignalPurchaseRepo;
//import net.juststock.trading.repository.UserRepo;
//import net.juststock.trading.services.ICheckoutService;
//import net.juststock.trading.services.PaymentProvider;
//import net.juststock.trading.web.dto.CreateSignalCheckoutReq;
//import net.juststock.trading.web.dto.CreateSignalCheckoutRes;
//import net.juststock.trading.web.dto.PurchaseView;
//
//@Service
//public class CheckoutServiceImpl implements ICheckoutService {
//
//    private final AdvisorySignalRepo signals;
//    private final SignalPurchaseRepo purchases;
//    private final PaymentEventRepo events;
//    private final UserRepo users;
//    private final PaymentProvider provider;
//    private final ObjectMapper om = new ObjectMapper();
//
//    // demo price (₹999.00). Replace with per-signal pricing if needed.
//    private final long priceCents = 99900;
//
//    public CheckoutServiceImpl(AdvisorySignalRepo signals,
//                               SignalPurchaseRepo purchases,
//                               PaymentEventRepo events,
//                               UserRepo users,
//                               PaymentProvider provider) {
//        this.signals = signals;
//        this.purchases = purchases;
//        this.events = events;
//        this.users = users;
//        this.provider = provider;
//    }
//
//    @Override
//    @Transactional
//    public CreateSignalCheckoutRes createSignalCheckout(Long userId, String userContact, CreateSignalCheckoutReq req) {
//        // Ensure user exists (demo: create on the fly if missing)
//        var user = users.findById(userId).orElseGet(() -> {
//            var u = new User();
//            u.setFullName("Guest " + userContact);
//            u.setContact(userContact);
//            return users.save(u);
//        });
//
//        // Load signal
//        AdvisorySignal sig = signals.findById(req.signalId()).orElseThrow();
//
//        // Snapshot of what the user is buying (store in purchase row as JSON)
//        var snapshot = new java.util.LinkedHashMap<String, Object>();
//        snapshot.put("signalId", sig.getId());
//        snapshot.put("instrumentType", sig.getInstrumentType());
//        snapshot.put("instrumentId", sig.getInstrumentId());
//        snapshot.put("title", sig.getTitle());
//        snapshot.put("message", sig.getMessage());
//
//        // Create provider order
//        var receipt = "rcpt_" + user.getId() + "_" + sig.getId();
//        var order = provider.createOrder(priceCents, "INR", receipt);
//
//        // Save PENDING purchase
//        var sp = new SignalPurchase();
//        sp.setUserId(user.getId());
//        sp.setUserContact(user.getContact());
//        sp.setSignalId(sig.getId());
//        sp.setSelection(req.selection());
//        sp.setStatus(SignalPurchase.Status.PENDING);
//        sp.setAmountCents(priceCents);
//        sp.setCurrency("INR");
//        sp.setProvider("RAZORPAY");
//        sp.setProviderOrderId(order.orderId());
//        sp.setInstrumentSnapshotJson(writeJson(snapshot));
//        purchases.save(sp);
//
//        var msg = "Pay ₹" + (priceCents / 100) + ".00 to unlock " + req.selection() + " for signal #" + sig.getId();
//        return new CreateSignalCheckoutRes(sp.getId(), priceCents, "INR", "RAZORPAY", order.orderId(), msg);
//    }
//
//    @Override
//    @Transactional
//    public void handleWebhook(String signature, String payload) {
//        if (!provider.verifySignature(payload, signature)) {
//            throw new IllegalArgumentException("Invalid signature");
//        }
//        var parsed = provider.parseWebhookPayload(payload);
//
//        // log the raw event
//        var ev = new PaymentEvent();
//        ev.setProvider("RAZORPAY");
//        ev.setEvent(parsed.event());
//        ev.setOrderId(parsed.orderId());
//        ev.setPaymentId(parsed.paymentId());
//        ev.setSignature(signature);
//        ev.setPayload(payload);
//        events.save(ev);
//
//        // idempotent mark as PAID
//        purchases.findByProviderOrderId(parsed.orderId()).ifPresent(sp -> {
//            if (sp.getStatus() != SignalPurchase.Status.PAID) {
//                sp.setStatus(SignalPurchase.Status.PAID);
//                sp.setPaidAtUtc(Instant.now());
//                purchases.save(sp);
//            }
//        });
//    }
//
//    @Override
//    @Transactional(readOnly = true)
//    public PurchaseView view(Long purchaseId) {
//        var sp = purchases.findById(purchaseId).orElseThrow();
//        var message = "You purchased " + sp.getSelection().name() + " for signal #" + sp.getSignalId();
//        return new PurchaseView(
//            sp.getId(),
//            sp.getUserId(),
//            sp.getUserContact(),
//            sp.getSignalId(),
//            sp.getSelection().name(),
//            sp.getStatus().name(),
//            message
//        );
//    }
//
//    private String writeJson(Object o) {
//        try { return om.writeValueAsString(o); }
//        catch (Exception e) { return "{}"; }
//    }
//}
