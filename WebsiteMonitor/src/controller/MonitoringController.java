package controller;

import model.Subscription;
import model.User;
import notification.Notification;

import java.util.List;

public class MonitoringController {

    public void monitor(List<Subscription> subscriptions) {
        for (Subscription sub : subscriptions) {
            boolean updated = sub.getWebsite().checkForUpdates();
            if (updated) {
                notifyUser(sub);
            }
        }
    }

    private void notifyUser(Subscription subscription) {
        User user = subscription.getUser();
        String message = "Update detected on: " + subscription.getWebsite().getUrl();
        Notification notification = new Notification(message, user, subscription.getChannel());
        notification.deliver();
    }
}