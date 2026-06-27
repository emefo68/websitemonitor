package model;

import java.util.ArrayList;
import java.util.List;

public class User {
    private String name;
    private String contactInfo;
    private List<Subscription> subscriptions;

    public User(String name, String contactInfo) {
        this.name = name;
        this.contactInfo = contactInfo;
        this.subscriptions = new ArrayList<>();
    }

    public Subscription createSubscription(Website website, int frequencyMinutes, Channel channel) {
        Subscription subscription = new Subscription(website, this, frequencyMinutes, channel);
        subscriptions.add(subscription);
        return subscription;
    }

    public void removeSubscription(Subscription subscription) {
        subscriptions.remove(subscription);
    }

    public String getName() { return name; }
    public String getContactInfo() { return contactInfo; }
    public List<Subscription> getSubscriptions() { return subscriptions; }
}
