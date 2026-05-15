package notification;

import model.Channel;
import model.User;

import java.time.LocalDateTime;

public class Notification {
    private String message;
    private LocalDateTime timestamp;
    private User recipient;
    private Channel channel;

    public Notification(String message, User recipient, Channel channel) {
        this.message = message;
        this.recipient = recipient;
        this.channel = channel;
        this.timestamp = LocalDateTime.now();
    }

    public void deliver() {
        switch (channel) {
            case EMAIL:
                System.out.println("E-Mail to " + recipient.getContactInfo() + " | " + message);
                break;
            case SMS:
                System.out.println("SMS to " + recipient.getContactInfo() + " | " + message);
                break;
            case WHATSAPP:
                System.out.println("Whatsapp to " + recipient.getContactInfo() + " | " + message);
                break;
            case DISCORD:
                System.out.println("Discord to " + recipient.getContactInfo() + " | " + message);
                break;
        }
    }

    public String getMessage() { return message; }
    public LocalDateTime getTimestamp() { return timestamp; }
}
