import controller.SubscriptionController;
import model.Channel;
import model.Subscription;
import model.User;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import strategy.HtmlComparisonStrategy;
import strategy.SizeComparisonStrategy;
import strategy.TextComparisonStrategy;

import static org.junit.jupiter.api.Assertions.*;

public class UnitTests {
    private SubscriptionController subscriptionController;
    private User user;

    @BeforeEach
    void setup() {
        subscriptionController = new SubscriptionController();
        user = new User("Muhammed", "muhammed@example.com");
    }

    @Test
    void testSubscribeValidUrlReturnSubscription() {
        Subscription subscription = subscriptionController.subscribe(user, "https://example.com", 30, Channel.EMAIL);
        assertNotNull(subscription);
        assertEquals(1, user.getSubscriptions().size());
    }

    @Test
    void testSubscribeMalformedUrlReturnsNull() {
        Subscription subscription = subscriptionController.subscribe(user, "blablabla :::", 30, Channel.EMAIL);
        assertNull(subscription);
        assertEquals(0, user.getSubscriptions().size());
    }

    @Test
    void testSubscribePositiveFrequencyIsSet() {
        Subscription subscription = subscriptionController.subscribe(user, "https://example.com", 60, Channel.SMS);
        assertNotNull(subscription);
        assertEquals(60, subscription.getFrequencyMinutes());
    }

    @Test
    void testSubscribeZeroFrequencyIsAccepted() {
        Subscription subscription = subscriptionController.subscribe(user, "https://example.com", 0, Channel.SMS);
        assertNotNull(subscription);
        assertEquals(0, subscription.getFrequencyMinutes());
    }

    @Test
    void testSubscribeNegativeFrequencyIsAccepted() {
        Subscription subscription = subscriptionController.subscribe(user, "https://example.com", -10, Channel.SMS);
        assertNotNull(subscription);
        assertEquals(-10, subscription.getFrequencyMinutes());
    }

    @Test
    void testCancelSubscription() {
        Subscription subscription = subscriptionController.subscribe(user, "https://example.com", 30, Channel.EMAIL);
        assertEquals(1, user.getSubscriptions().size());
        subscriptionController.cancel(user, subscription);
        assertEquals(0, user.getSubscriptions().size());
    }

   @Test
   void testModifySubscription() {
       Subscription subscription = subscriptionController.subscribe(user, "https://example.com", 30, Channel.EMAIL);
       subscriptionController.modify(subscription, 120, Channel.DISCORD);
       assertEquals(120, subscription.getFrequencyMinutes());
       assertEquals(Channel.DISCORD, subscription.getChannel());
   }

   @Test
   void testHtmlComparisonTrue() {
       HtmlComparisonStrategy htmlComparisonStrategy = new HtmlComparisonStrategy();
       assertTrue(htmlComparisonStrategy.isIdentical("<p>Hello<p>", "<p>Hello<p>"));
   }

    @Test
    void testHtmlComparisonFalse() {
        HtmlComparisonStrategy htmlComparisonStrategy = new HtmlComparisonStrategy();
        assertFalse(htmlComparisonStrategy.isIdentical("<p>Hello<p>", "<p>World<p>"));
    }

    @Test
    void testTextComparisonTrue() {
        TextComparisonStrategy textComparisonStrategy = new TextComparisonStrategy();
        assertTrue(textComparisonStrategy.isIdentical("<p>Hello<p>", "<h1>Hello<h1>"));
    }

    @Test
    void testTextComparisonFalse() {
        TextComparisonStrategy textComparisonStrategy = new TextComparisonStrategy();
        assertFalse(textComparisonStrategy.isIdentical("<p>Hello<p>", "<h1>World<h1>"));
    }

   @Test
   void testSizeComparisonTrue() {
       SizeComparisonStrategy sizeComparisonStrategy = new SizeComparisonStrategy();
       assertTrue(sizeComparisonStrategy.isIdentical("Hello", "Hello"));
   }

    @Test
    void testSizeComparisonFalse() {
        SizeComparisonStrategy sizeComparisonStrategy = new SizeComparisonStrategy();
        assertFalse(sizeComparisonStrategy.isIdentical("Hello", "Helloo"));
    }

    @Test
    void testComparisonNullReturnsFalse() {
        HtmlComparisonStrategy htmlComparisonStrategy = new HtmlComparisonStrategy();
        assertFalse(htmlComparisonStrategy.isIdentical(null, "<p>Hello<p>"));
    }

}
