package net.juststock.trading.controller;

import net.juststock.trading.domain.admin.AdminSignalMessage;
import net.juststock.trading.domain.common.InstrumentType;
import net.juststock.trading.domain.common.SignalType;
import net.juststock.trading.domain.user.UserProfile;
import net.juststock.trading.service.interfaces.AdminSignalMessageService;
import net.juststock.trading.service.interfaces.NotificationService;
import net.juststock.trading.service.interfaces.UserProfileService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/signals")
public class AdminSignalMessageController {

    private final AdminSignalMessageService adminService;
    private final UserProfileService profileService;
    private final NotificationService notificationService;

    public AdminSignalMessageController(AdminSignalMessageService adminService,
                                        UserProfileService profileService,
                                        NotificationService notificationService) {
        this.adminService = adminService;
        this.notificationService = notificationService;
        this.profileService = profileService;
    }

   
    @PostMapping
    public ResponseEntity<AdminSignalMessage> createMessage(@RequestBody AdminSignalMessage message) {
        // Save the admin message
        AdminSignalMessage saved = adminService.save(message);

        // Fetch all users (filter by subscription/payment if needed)
        List<UserProfile> users = profileService.getAllUsers();

        // Send notifications with instrument name + signal type
        for (UserProfile user : users) {
            notificationService.sendNotification(
                user,
                saved.getInstrumentType(),
                saved.getSignalType(),
                saved.getMessage()
            );
        }

        return ResponseEntity.status(201).body(saved);
    }

    /**
     * Get all messages for a given instrument type.
     */
    
    // GET /api/admin/signals/by-instrument?instrumentType=NIFTY

    @GetMapping("/by-instrument")
    public ResponseEntity<List<AdminSignalMessage>> byInstrument(@RequestParam InstrumentType instrumentType) {
        return ResponseEntity.ok(adminService.getByInstrument(instrumentType));
    }

    /**
     * Get a single message by signal type and instrument type.
     */

    //    GET /api/admin/signals/by-type?signalType=CALL&instrumentType=NIFTY

    @GetMapping("/by-type")
    public ResponseEntity<AdminSignalMessage> one(@RequestParam SignalType signalType,
                                                  @RequestParam InstrumentType instrumentType) {
        return adminService.getOne(signalType, instrumentType)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
