package model;

public class Subscription {
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
