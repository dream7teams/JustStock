
package net.juststock.trading.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.juststock.trading.domain.notification.Notification;

public interface NotificationRepository extends JpaRepository<Notification, Long> { }
