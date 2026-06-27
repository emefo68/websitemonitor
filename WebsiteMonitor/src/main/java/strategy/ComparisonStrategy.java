package strategy;

public interface ComparisonStrategy {
    boolean isIdentical(String oldContent, String newContent);
}
