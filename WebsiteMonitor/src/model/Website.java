package model;

import observer.Observer;
import observer.Subject;
import strategy.ComparisonStrategy;
import strategy.HtmlComparisonStrategy;

import java.util.ArrayList;
import java.util.List;

public class Website implements Subject {
    private String url;
    private String lastKnownContent;
    private List<Observer> observers;
    private ComparisonStrategy comparisonStrategy;

    public Website(String url) {
        this.url = url;
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
        return "<html><body><h1>MOCK-CONTENT</h1></body></html>";
    }

    public String getUrl() { return url; }

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
