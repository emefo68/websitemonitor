package controller;

import model.Channel;
import model.Subscription;
import model.User;
import model.Website;

public class SubscriptionController {

    public Subscription subscribe(User user, String url, int frequencyMinutes, Channel channel) {
        Website website = new Website(url);
        return user.createSubscription(website, frequencyMinutes, channel);
    }

    public void modify(Subscription subscription, int newFrequency, Channel newChannel) {
        subscription.setFrequencyMinutes(newFrequency);
        subscription.setChannel(newChannel);
    }

    public void cancel(User user, Subscription subscription) {
        user.removeSubscription(subscription);
    }
}
