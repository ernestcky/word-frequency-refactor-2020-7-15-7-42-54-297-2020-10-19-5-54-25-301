import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

public class WordFrequencyGame {

    public static final String REGEX_WHITE_SPACE = "\\s+";
    public static final String LINE_ESCAPER = "\n";
    public static final String EXCEPTION_CALCULATE_ERROR = "Calculate Error";

    public String getResult(String sentence) {
        try {
            //split the input string with 1 to n pieces of spaces
            String[] words = sentence.split(REGEX_WHITE_SPACE);

            List<WordFrequency> wordFrequencyList = new ArrayList<>();
            for (String word : words) {
                wordFrequencyList.add(new WordFrequency(word, 1));
            }

            //get the map for the next step of sizing the same word
            Map<String, List<WordFrequency>> wordCountMap = getWordCountMap(wordFrequencyList);

            List<WordFrequency> wordCountList = new ArrayList<>();
            for (Map.Entry<String, List<WordFrequency>> entry : wordCountMap.entrySet()) {
                wordCountList.add(new WordFrequency(entry.getKey(), entry.getValue().size()));
            }
            wordFrequencyList = wordCountList;

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

    private String generateWordFrequencyLine(WordFrequency wordFrequency) {
        return String.format("%s %d", wordFrequency.getWord(), wordFrequency.getWordCount());
    }

    private Map<String, List<WordFrequency>> getWordCountMap(List<WordFrequency> wordFrequencyList) {
        Map<String, List<WordFrequency>> wordCountMap = new HashMap<>();
        for (WordFrequency wordFrequency : wordFrequencyList) {
            if (!wordCountMap.containsKey(wordFrequency.getWord())) {
                ArrayList wordCountList = new ArrayList<>();
                wordCountList.add(wordFrequency);
                wordCountMap.put(wordFrequency.getWord(), wordCountList);
            } else {
                wordCountMap.get(wordFrequency.getWord()).add(wordFrequency);
            }
        }
        return wordCountMap;
    }
}
