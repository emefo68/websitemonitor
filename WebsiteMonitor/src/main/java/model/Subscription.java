package model;

import notification.Notification;
import observer.Observer;

public class Subscription implements Observer {
    private Website website;
    private User user;
    private int frequencyMinutes;
    private Channel channel;

    public Subscription(Website website, User user, int frequencyMinutes, Channel channel) {
        this.website = website;
        this.user = user;
        this.frequencyMinutes = frequencyMinutes;
        this.channel = channel;
    }

    @Override
    public void update(Website updatedWebsite) {
        String message = "Update on: " + updatedWebsite.getUrl();
        Notification notification = new Notification(message, user, channel);
        notification.deliver();
    }

    public Website getWebsite() {
        return website;
    }

    public void setWebsite(Website website) {
        this.website = website;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getFrequencyMinutes() {
        return frequencyMinutes;
    }

    public void setFrequencyMinutes(int frequencyMinutes) {
        this.frequencyMinutes = frequencyMinutes;
    }

    public Channel getChannel() {
        return channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }
}
