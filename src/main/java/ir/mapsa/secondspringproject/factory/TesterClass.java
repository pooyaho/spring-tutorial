package ir.mapsa.secondspringproject.factory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TesterClass {

    @Autowired
    private NotificationFactory factory;

    public void testFactory() {
        Notification notification = factory.createNotification(NotificationType.SMS);
        notification.notifyUser();
    }
}
