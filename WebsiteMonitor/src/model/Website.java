package model;

public class Website {
    private String url;
    private String lastKnownContent;

    public Website(String url) {
        this.url = url;
        this.lastKnownContent = "";
    }

    public boolean checkForUpdates() {
        String currentContent = fetchContent();
        if (!currentContent.equals(lastKnownContent)) {
            lastKnownContent = currentContent;
            return true;
        }
        return false;
    }

    private String fetchContent() {
        return "MOCK-CONTENT";
    }

    public String getUrl() { return url; }
}
