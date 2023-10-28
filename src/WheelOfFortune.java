import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Random;

/**
 * Abstract class WheelOfFortune reads phrases from a file, picks a random phrase, encrypts the phrase, has
 * an abstract method get guess, then processes the guess
 */
public abstract class WheelOfFortune extends GuessingGame{

    protected List<String> phrases;
    protected String currentPhrase;
    protected StringBuilder hiddenPhrase;


    /**
     * Constructor calls reads phrases
     */
    public WheelOfFortune(){
        readPhrases();
    }

    /**
     * Method to reads the phrases from a text file and assign them to a list.
     */
    public void readPhrases(){
        try{
            phrases = Files.readAllLines(Paths.get("phrases.txt"));
        } catch (IOException e){
            System.out.println(e);
        }
    }

    /**
     * Method to return the current phrase
     * @return String currentPhrase
     */
    public String getCurrentPhrase(){
        return currentPhrase;
    }

    /**
     * Method to randomly pick the phrase that will be played and removes the phrase
     * from the list so it isn't played again
     */
    public void randomPhrase(){
        Random rand = new Random();
        int r = rand.nextInt(phrases.size());
        currentPhrase = phrases.get(r);
        phrases.remove(r);
    }

    /**
     * Encrypts the current phrase so the user can't see the letters
     */
    public void generateHiddenPhrase(){
        this.hiddenPhrase = new StringBuilder();
        for (int i = 0; i < this.currentPhrase.length(); i++) {
            if (this.currentPhrase.charAt(i) != ' ') {
                this.hiddenPhrase.append('*');
            }
            else {
                this.hiddenPhrase.append(' ');
            }
        }
        System.out.print("Hidden Phrase: ");
        System.out.println(this.hiddenPhrase);
    }

    /**
     * Abstract method that game has to implement on how the user will input a guess
     * @return
     */
    public abstract char getGuess();

    /**
     * Method to process the guess to determine if the guess was correct or not. If correct, the correct letter will
     * be visible in the hidden phrase
     * @param guess
     * @return boolean
     */
    public boolean processGuess(char guess){
        int phraseLoopCount = 0;
        for (int i = 0; i < this.currentPhrase.length(); i++) {
            if (Character.isUpperCase(this.currentPhrase.charAt(i))){
                if(Character.toLowerCase(this.currentPhrase.charAt(i))== guess){
                    this.hiddenPhrase.setCharAt(i, Character.toUpperCase(guess));
                }
                else {
                    phraseLoopCount++;
                }
            }
            else if (this.currentPhrase.charAt(i) == guess) {
                this.hiddenPhrase.setCharAt(i, guess);
            }
            else {
                phraseLoopCount++; //count to see if the letter didn't exist in the phrase
            }
        }
        if(phraseLoopCount != this.currentPhrase.length()){
            System.out.print("Correct. There ");
            if(currentPhrase.length() - phraseLoopCount > 1){
                System.out.print("were ");
                System.out.print(currentPhrase.length() - phraseLoopCount);
                System.out.print(" ");
                System.out.print(guess);
                System.out.println("'s");
            }
            else{
                System.out.print("was ");
                System.out.print(currentPhrase.length() - phraseLoopCount);
                System.out.print(" ");
                System.out.println(guess);
            }
        }
        else{
            System.out.println("Incorrect. ");
        }
        return phraseLoopCount != this.currentPhrase.length(); //The guessed character didn't match a single character in the phrase
    }
}
