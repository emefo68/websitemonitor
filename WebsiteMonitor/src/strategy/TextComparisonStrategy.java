package strategy;

public class TextComparisonStrategy implements ComparisonStrategy {
    @Override
    public boolean isIdentical(String oldContent, String newContent) {
        if (oldContent == null || newContent == null) {
            return oldContent == newContent;
        }
        String oldText = oldContent.replaceAll("<[^>]*>", "").trim();
        String newText = newContent.replaceAll("<[^>]*>", "").trim();

        return oldText.equals(newText);    }
}
