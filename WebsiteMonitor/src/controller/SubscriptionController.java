package controller;

import model.Channel;
import model.Subscription;
import model.User;
import model.Website;

import java.net.URISyntaxException;

public class SubscriptionController {

    public Subscription subscribe(User user, String url, int frequencyMinutes, Channel channel) {
        try {
            Website website = new Website(url);
            Subscription subscription = user.createSubscription(website, frequencyMinutes, channel);
            website.attach(subscription);
            return subscription;
        } catch (URISyntaxException e) {
            System.out.println("Invalid URL: " + e.getMessage());
            return null;
        }
    }

    public void modify(Subscription subscription, int newFrequency, Channel newChannel) {
        subscription.setFrequencyMinutes(newFrequency);
        subscription.setChannel(newChannel);
    }

    public void cancel(User user, Subscription subscription) {
        subscription.getWebsite().detach(subscription);
        user.removeSubscription(subscription);
    }
}
