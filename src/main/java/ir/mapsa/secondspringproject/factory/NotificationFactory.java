package ir.mapsa.secondspringproject.factory;

import org.springframework.stereotype.Service;

@Service
public class NotificationFactory {
    public Notification createNotification(NotificationType channel) {
        if (channel == null) {
            return null;
        }
        switch (channel) {
            case SMS:
                return new SMSNotification();
            case EMAIL:
                return new EmailNotification();
            case PUSH:
                return new PushNotification();
            default:
                throw new IllegalArgumentException("Unknown channel " + channel);
        }
    }
}
