import java.util.*;
import java.util.stream.Collectors;

public class WordFrequencyGame {

    public static final String REGEX_WHITE_SPACE = "\\s+";
    public static final String LINE_ESCAPER = "\n";
    public static final String EXCEPTION_CALCULATE_ERROR = "Calculate Error";

    public String getResult(String sentence) {
        try {
            List<WordFrequency> wordFrequencyList = this.calculateWordFrequency(sentence);

            wordFrequencyList.sort((word1, word2) -> word2.getWordCount() - word1.getWordCount());

            StringJoiner wordFrequencyResult = new StringJoiner(LINE_ESCAPER);
            for (WordFrequency wordFrequency : wordFrequencyList) {
                wordFrequencyResult.add(this.generateWordFrequencyLine(wordFrequency));
            }
            return wordFrequencyResult.toString();
        } catch (Exception exception) {
            return EXCEPTION_CALCULATE_ERROR;
        }
    }

    private List<WordFrequency> calculateWordFrequency(String sentence) {
        //split the input string with 1 to n pieces of spaces
        List<String> words = Arrays.asList(sentence.split(REGEX_WHITE_SPACE));

        HashSet<String> distintctWords = new HashSet<>(words);

        return distintctWords.stream()
                .map(word -> new WordFrequency(word, Collections.frequency(words, word)))
                .collect(Collectors.toList());
    }

    private String generateWordFrequencyLine(WordFrequency wordFrequency) {
        return String.format("%s %d", wordFrequency.getWord(), wordFrequency.getWordCount());
    }

}
