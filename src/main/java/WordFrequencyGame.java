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
        if (sentence.split(REGEX_WHITE_SPACE).length == 1) {
            return sentence + " 1";
        } else {
            try {
                //split the input string with 1 to n pieces of spaces
                String[] words = sentence.split(REGEX_WHITE_SPACE);

                List<WordFrequency> wordFrequencyList = new ArrayList<>();
                for (String word : words) {
                    WordFrequency wordFrequency = new WordFrequency(word, 1);
                    wordFrequencyList.add(wordFrequency);
                }

                //get the map for the next step of sizing the same word
                Map<String, List<WordFrequency>> wordCountMap = getListMap(wordFrequencyList);

                List<WordFrequency> wordCountList = new ArrayList<>();
                for (Map.Entry<String, List<WordFrequency>> entry : wordCountMap.entrySet()) {
                    WordFrequency wordFrequency = new WordFrequency(entry.getKey(), entry.getValue().size());
                    wordCountList.add(wordFrequency);
                }
                wordFrequencyList = wordCountList;

                wordFrequencyList.sort((word1, word2) -> word2.getWordCount() - word1.getWordCount());

                StringJoiner wordFrequencyResult = new StringJoiner(LINE_ESCAPER);
                for (WordFrequency wordFrequency : wordFrequencyList) {
                    String wordFrequencyLine = wordFrequency.getWord() + " " + wordFrequency.getWordCount();
                    wordFrequencyResult.add(wordFrequencyLine);
                }
                return wordFrequencyResult.toString();
            } catch (Exception exception) {
                return EXCEPTION_CALCULATE_ERROR;
            }
        }
    }

    private Map<String, List<WordFrequency>> getListMap(List<WordFrequency> wordFrequencyList) {
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
