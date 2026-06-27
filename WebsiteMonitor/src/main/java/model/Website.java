package model;

import observer.Observer;
import observer.Subject;
import strategy.ComparisonStrategy;
import strategy.HtmlComparisonStrategy;
import strategy.TextComparisonStrategy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

public class Website implements Subject {
    private URI url;
    private String lastKnownContent;
    private List<Observer> observers;
    private ComparisonStrategy comparisonStrategy;

    public Website(String url) throws URISyntaxException {
        this.url = new URI(url);
        this.lastKnownContent = "";
        this.observers = new ArrayList<>();
        this.comparisonStrategy = new HtmlComparisonStrategy();
    }

    public boolean checkForUpdates() {
        String currentContent = fetchContent();
        if (!comparisonStrategy.isIdentical(lastKnownContent, currentContent)) {
            lastKnownContent = currentContent;
            notifyObservers();
            return true;
        }
        return false;
    }

    private String fetchContent() {
        try {
            URLConnection connection = url.toURL().openConnection();
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder content = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
            reader.close();
            return content.toString();
        } catch (Exception e) {
            System.out.println("Error fetching content: " + e.getMessage());
            return "";
        }
    }

    public String getUrl() { return url.toString(); }

    @Override
    public void attach(Observer observer) {
        if (!observers.contains(observer)) {
            observers.add(observer);
        }
    }

    @Override
    public void detach(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(this);
        }
    }
}
