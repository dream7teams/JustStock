package net.juststock.trading.service.impl;

import net.juststock.trading.domain.common.InstrumentType;
import net.juststock.trading.domain.common.SignalType;
import net.juststock.trading.domain.notification.Notification;
import net.juststock.trading.domain.user.UserProfile;
import net.juststock.trading.repository.NotificationRepository;
import net.juststock.trading.service.interfaces.NotificationService;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;

@Service
public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepository notificationRepository;

    public NotificationServiceImpl(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    @Override
    public void sendNotification(UserProfile user, InstrumentType instrumentType, SignalType signalType, String message) {
        Notification notification = new Notification();
        notification.setUserProfile(user);
        notification.setMessage(
            String.format("[%s - %s] %s", instrumentType.name(), signalType.name(), message)
        );
        notification.setCreatedAt(ZonedDateTime.now());
        notificationRepository.save(notification);

        // 🔹 Here you can also integrate with:
        // - Email service
        // - SMS gateway
        // - Push notification service
    }
}
