import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class AuthorshipDetection {
    public static double calculateAverageWordLength(String text) {
        String[] words = text.split("\\s+");
        int totalLength = 0;
        for (String word : words) {
            word = word.replaceAll("[.,?!]", "");
            totalLength += word.length();
        }
        double averageLength = (double) totalLength / words.length;
        return averageLength;
    }
    public static double calculateTypeTokenRatio(String text) {
        String[] words = text.split("\\s+");
        Map<String, Integer> wordCounts = new HashMap<>();
        for (String word : words) {
            word = word.replaceAll("[.,?!]", "");
            wordCounts.put(word, wordCounts.getOrDefault(word, 0) + 1);
        }
        double uniqueWordCount = wordCounts.size();
        double ratio = uniqueWordCount / words.length;

        return ratio;
    }

    public static double calculateHapaxLegomenaRatio(String text) {
        String[] words = text.split("\\s+");
        Map<String, Integer> wordCounts = new HashMap<>();
        for (String word : words) {
            word = word.replaceAll("[.,?!]", "");
            wordCounts.put(word, wordCounts.getOrDefault(word, 0) + 1);
        }
        long hapaxLegomenaCount = wordCounts.values().stream().filter(count -> count == 1).count();
        double ratio = (double) hapaxLegomenaCount / words.length;
        return ratio;
    }

    public static double calculateAverageWordsPerSentence(String text) {
        String[] sentences = text.split("[.!?]");
        int totalWords = 0;
        for (String sentence : sentences) {
            String[] words = sentence.trim().split("\\s+");
            totalWords += words.length;
        }
        double averageWords = (double) totalWords / sentences.length;
        return averageWords;
    }

    public static double calculateSimilarity(String text1, String text2, double weightF1, double weightF2, double weightF3, double weightF4) {
        double f1T1 = calculateAverageWordLength(text1);
        double f2T1 = calculateTypeTokenRatio(text1);
        double f3T1 = calculateHapaxLegomenaRatio(text1);
        double f4T1 = calculateAverageWordsPerSentence(text1);

        double f1T2 = calculateAverageWordLength(text2);
        double f2T2 = calculateTypeTokenRatio(text2);
        double f3T2 = calculateHapaxLegomenaRatio(text2);
        double f4T2 = calculateAverageWordsPerSentence(text2);

        double similarity = Math.abs(f1T1 - f1T2) * weightF1 +
                Math.abs(f2T1 - f2T2) * weightF2 +
                Math.abs(f3T1 - f3T2) * weightF3 +
                Math.abs(f4T1 - f4T2) * weightF4;

        return similarity;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter the first text:");
        String text1 = sc.nextLine();
        System.out.println("Please enter the second text:");
        String text2 = sc.nextLine();

        double weightF1 = 11.0;
        double weightF2 = 33.0;
        double weightF3 = 50.0;
        double weightF4 = 0.4;

        double similarityScore = calculateSimilarity(text1, text2, weightF1, weightF2, weightF3, weightF4);
        System.out.println("Similarity score: " + similarityScore);
    }
}