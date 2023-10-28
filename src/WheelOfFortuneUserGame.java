import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * WheelOfFortuneUserGame class that plays a Wheel of Fortune game. The user will be prompted to guess letters
 * until the user correctly guesses a hidden phrase, or incorrectly guesses a predetermined number of times.
 * This class only has a main function.
 */
public class WheelOfFortuneUserGame extends WheelOfFortune{

    protected WheelOfFortuneUser wheelOfFortuneUser;

    /**
     * Constructor, calls the parent constructor and creates a new player object to play the game.
     */
    public WheelOfFortuneUserGame(){
        super();
        wheelOfFortuneUser = new WheelOfFortuneUser();
    }
    /**
     * Method to call the algorithm for how the user will input a guess
     * @return char guess
     */
    @Override
    public char getGuess(){
        char guess = wheelOfFortuneUser.nextGuess();
        return guess;
    }


    /**
     * Method on how the Wheel of Fortune game will be played. It will allow the user to play a designated number
     * of times and will let the user know when he/she was correct or incorrect.
     * @return GameRecord(score, playerId)
     */
    @Override
    public GameRecord play(){
        int misses = 0;
        int allowedMisses = 5; //allowed misses
        System.out.println("Welcome to the Wheel of Fortune Game!");
        System.out.println("Below will be a hidden phrase with letters represented with an *");
        System.out.print("The game will continue until you guess the wrong letter ");
        System.out.print(allowedMisses);
        System.out.println(" times");
        randomPhrase();
        generateHiddenPhrase();
        int unguessedLettersRemaining = hiddenPhrase.length(); //initializing to above 0.
        while (misses < allowedMisses && unguessedLettersRemaining != 0) {
            unguessedLettersRemaining = 0;
            char guess = getGuess();
            boolean match = processGuess(guess);
            if (!match) {
                misses++;
            }
            for (int i = 0; i < currentPhrase.length(); i++) {
                if (hiddenPhrase.charAt(i) == '*') {
                    unguessedLettersRemaining++;
                }
            }
            System.out.print("Hidden Phrase: ");
            System.out.println(hiddenPhrase);
            if (unguessedLettersRemaining == 0) {
                System.out.println("Congrats, you guessed the correct phrase!");
            }
            else if (misses == allowedMisses) {
                System.out.println("Game over, you ran out of attempts");
            }
            else {
                System.out.println("You have " + (allowedMisses - misses) + " misses remaining");
            }
        }
        return new GameRecord(allowedMisses - misses, wheelOfFortuneUser.playerId());
    }

    /**
     * Will ask the player if he/she wants to play another game as long as there are remaining unplayed phrases.
     * Will reset the previous guesses tracker before each new phrase
     * @return boolean
     */
    @Override
    public boolean playNext(){
        if(!phrases.isEmpty()) {
            if(super.playNext()) {
                wheelOfFortuneUser.reset(); //resets previous guesses for the next phrase
                return true;
            }
        }
        return false;
    }

    /**
     * toString method to return the string including the wheelOfFortuneUser player id
     * @return
     */
    @Override
    public String toString() {
        return "WheelOfFortuneUserGame{" +
                "wheelOfFortuneUser=" + wheelOfFortuneUser.playerId() +
                '}';
    }

    /**
     * equals method to compare to WheelOfFortune games
     * @param o
     * @return boolean
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WheelOfFortuneUserGame that = (WheelOfFortuneUserGame) o;
        return Objects.equals(wheelOfFortuneUser, that.wheelOfFortuneUser);
    }



    public static void main(String [] args){
        WheelOfFortuneUserGame game = new WheelOfFortuneUserGame();
        AllGamesRecord record = game.playAll();
        System.out.println(record);
    }
}