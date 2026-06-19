import model.Website;

import javax.print.URIException;
import java.net.URISyntaxException;

public class Main {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Usage: java Main <website-url>");
            System.exit(1);
        }

        try {
            Website website = new Website(args[0]);
            while (true) {
                boolean hasChanged = website.checkForUpdates();
                System.out.println("Website '" + args[0] + "' updated: " + hasChanged);

                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    System.out.println("Monitoring stopped");
                    break;
                }
            }
        } catch (URISyntaxException e) {
            System.out.println("Invalid URL: " + e.getMessage());
            System.exit(1);
        }
    }
}