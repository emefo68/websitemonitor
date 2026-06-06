package strategy;

public class SizeComparisonStrategy implements ComparisonStrategy {

    @Override
    public boolean isIdentical(String oldContent, String newContent) {
        if (oldContent == null || newContent == null) {
            return oldContent == newContent;
        }
        return oldContent.length() == newContent.length();
    }
}
