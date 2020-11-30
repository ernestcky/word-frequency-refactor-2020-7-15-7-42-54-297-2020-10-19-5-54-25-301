public class WordFrequency {
    private String word;
    private int frequency;

    public WordFrequency(String word, int frequency) {
        this.word = word;
        this.frequency = frequency;
    }

    public String getWord() {
        return this.word;
    }

    public int getWordCount() {
        return this.frequency;
    }
}
