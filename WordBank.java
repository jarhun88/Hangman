import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class WordBank {

    private static WordBank instance;
    private List<String> words;
    private Random randomGenerator;

    /**
     * Constructs WordBank with empty collection of words
     */
    private WordBank(){
        this.words = new ArrayList<>();
        randomGenerator = new Random();
        addWord("hello");
        addWord("papyrus");
        addWord("frogurt");
        addWord("confession");
        addWord("baboon");
    }

    /**
     * gets one and only one instance of WordBank
     * @return instance of class
     */
    public static WordBank getInstance(){
        if (instance == null){
            instance = new WordBank();
        }
        return instance;
    }

    /**
     * add word to the list if it does not contain the word
     * @param word any word to be used in the game
     * @return word
     */
    public String addWord(String word) {
        if (!this.words.contains(word)){
            this.words.add(word);
        }
        return word;
    }

    /**
     * Randomly selects a word existing in WordBank
     * @return randomly chosen word existing from list
     */
    public String getRandomWord(){
        int index = randomGenerator.nextInt(this.words.size());
        return this.words.get(index);
    }




}
