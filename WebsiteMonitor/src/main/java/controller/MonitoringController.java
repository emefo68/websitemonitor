package controller;

import model.Subscription;
import model.User;
import model.Website;
import notification.Notification;

import java.util.List;

public class MonitoringController {

    public void monitor(List<Website> websites) {
        for (Website website : websites) {
            website.checkForUpdates();
        }
    }
}