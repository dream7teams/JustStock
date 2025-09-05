//package net.juststock.trading.controller;
//
//import net.juststock.trading.domain.AdvisorySignal;
//import org.springframework.data.domain.*;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("/api/admin/signals")
//public class AdvisorySignalController {
//
//  private final AdvisorySignalRepo repo;
//  public AdvisorySignalController(AdvisorySignalRepo repo) { this.repo = repo; }
//
//  @GetMapping
//  public Page<AdvisorySignal> list(@RequestParam(defaultValue = "0") int page,
//                                   @RequestParam(defaultValue = "20") int size) {
//    return repo.findAll(PageRequest.of(page, size, Sort.by("createdAtUtc").descending()));
//  }
//
//  @GetMapping("/{id}")
//  public ResponseEntity<AdvisorySignal> get(@PathVariable Long id) {
//    return repo.findById(id).map(ResponseEntity::ok)
//              .orElseGet(() -> ResponseEntity.notFound().build());
//  }
//
//  @PostMapping
//  public ResponseEntity<AdvisorySignal> create(@RequestBody AdvisorySignal s) {
//    return ResponseEntity.ok(repo.save(s));
//  }
//
//  @PutMapping("/{id}")
//  public ResponseEntity<AdvisorySignal> update(@PathVariable Long id, @RequestBody AdvisorySignal body) {
//    return repo.findById(id).map(s -> {
//      s.setInstrumentType(body.getInstrumentType());
//      s.setInstrumentId(body.getInstrumentId());
//      s.setTitle(body.getTitle());
//      s.setMessage(body.getMessage());
//      return ResponseEntity.ok(repo.save(s));
//    }).orElseGet(() -> ResponseEntity.notFound().build());
//  }
//
//  @DeleteMapping("/{id}")
//  public ResponseEntity<Void> delete(@PathVariable Long id) {
//    if (!repo.existsById(id)) return ResponseEntity.notFound().build();
//    repo.deleteById(id);
//    return ResponseEntity.noContent().build();
//  }
//}
