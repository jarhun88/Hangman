import java.util.Scanner;


/**
 * Each Hangman game will choose a random word from the WordBank with each letter represented as a _
 */
public class Hangman {
    // do something about capital letters

    private String word;
    private String status;
    private int attemptsLeft;
    private Scanner guessMaker;


    /**
     * Constructor
     */
    public Hangman() {
        guessMaker = new Scanner(System.in);
        attemptsLeft = 5;
        this.word = WordBank.getInstance().getRandomWord();

        System.out.println("Welcome to the Hangman Trial, if you don't want to die, you better guess what our word is!");
        System.out.println("Your word has " + getNumChars(this.word) + "characters! Good Luck!");

        while (!outOfAttempts() && !correct()){
            guess();
        }
        if (outOfAttempts()){
            System.out.println("You are out of attempts! GAME OVER YOU DIED.");
            System.out.println("The word was: " + this.word);
        }
        if (correct()){
            System.out.println("Congratulations you win!!!");
        }
    }

    /**
     *
     * @param word is the word that is trying to be solved
     * @return a concatenation of _ for each letter in word
     */
    public String getNumChars(String word){
        int length = word.length();
        String numChars = "";
        for (int i = 0; i < length; i++){
           numChars = numChars + "_ ";
        }
        this.status = numChars;
        return numChars;
    }

    /**
     * Will only accept a 1 char string from system input
     * @return the 1 char string
     */
    public String makeGuess(){
        Boolean guesser = true;
        String guess = "";
        while (guesser) {
            System.out.println("Make a guess: ");
            guess = guessMaker.next();
            guess = guess.toLowerCase();
            if (guess.length() == 1){
                guesser = false;
            }
        }
        System.out.println(guess + " is your guess!");
        return guess;
    }

    /**
     *  If word contains guess, then print newly status of word
     *  Else, print incorrect and number of attempts left
     */
   public void guess(){
       String guess = makeGuess();
       int count = this.word.length() - this.word.replaceAll(guess, "").length();
       if (count != 0){
           for (;count > -1; count--) {
               if (this.word.contains(guess)) {
                   int position = this.word.indexOf(guess);
                   position = position * 2;
                   changeStatus(position, guess);
               }
            }
       }
       else {
           this.attemptsLeft--;
           System.out.println("That letter is not in the word, you have " + Integer.toString(getAttemptsLeft()) + " attempts remaining!");
           }

       System.out.println("Your new status is " + this.status);
   }

    /**
     *
     * @param position index of this.status which will be replaced by guess
     * @param guess String
     * @return this.status updated with guess
     */
    public String changeStatus(int position, String guess){
       this.status = this.status.substring(0, position) + guess + this.status.substring(position + 1, this.status.length());
       this.word = this.word.substring(0, position / 2) + "$" + this.word.substring((position / 2) + 1, this.word.length());
       return this.status;
    }

    /**
     * Determines if game is out of attempts
     * @return true if no attempts left, else return false
     */
    public Boolean outOfAttempts(){
        return this.attemptsLeft <= 0;
    }

    public int getAttemptsLeft(){
        return this.attemptsLeft;
    }

    public String getStatus(){
        return this.status;
    }

    /**
     * Determines if current status has been solved
     * @return true if status contains any "_", else false
     */
    public Boolean correct(){
        return !getStatus().contains("_");
    }
}
