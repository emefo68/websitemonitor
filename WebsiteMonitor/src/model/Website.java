package model;

import observer.Observer;
import observer.Subject;

import java.util.ArrayList;
import java.util.List;

public class Website implements Subject {
    private String url;
    private String lastKnownContent;
    private List<Observer> observers;

    public Website(String url) {
        this.url = url;
        this.lastKnownContent = "";
        this.observers = new ArrayList<>();
    }

    public boolean checkForUpdates() {
        String currentContent = fetchContent();
        if (!currentContent.equals(lastKnownContent)) {
            lastKnownContent = currentContent;
            notifyObservers();
            return true;
        }
        return false;
    }

    private String fetchContent() {
        return "MOCK-CONTENT";
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
