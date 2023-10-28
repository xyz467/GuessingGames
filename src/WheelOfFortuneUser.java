import java.util.Objects;
import java.util.Scanner;

/**
 * WheelOfFortuneUser class is a real player that will play the WheelOfFortuneGame by entering a letter guess
 * through the keyboard after being prompted. The user will have a predetermined number of guesses and will
 * be asked if the user wants to play the next phrase.
 */
public class WheelOfFortuneUser implements WheelOfFortunePlayer{
    protected StringBuilder previousGuesses;
    String currentPhrase;
    public WheelOfFortuneUser(){
        previousGuesses = new StringBuilder();
    }
    /**
     * setCurrentPhrase method passes in the currentPhrase as a paramater.
     * @param currentPhrase
     */
    @Override
    public void setCurrentPhrase(String currentPhrase){
        this.currentPhrase = currentPhrase;
    }

    /**
     * Prompts the user to enter the guess into the keyboard. Will ensure that the guess is a letter that has been
     * not guessed before.
     * @return char guess
     */
    @Override
    public char nextGuess(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Previous guesses: ");
        System.out.println(previousGuesses);
        System.out.println("What does the phrase say? Please input a letter: (Only the first letter will be read)");
        String input = scanner.nextLine();
        while(input == null || input.isEmpty()){
            System.out.println("Null entry, please type a letter");
            input = scanner.nextLine();

        }
        char guess = Character.toLowerCase(input.charAt(0)); //converting the input to a lowercase char
        int charFrequency = 0; //variable to see if a letter has been guessed before.
        for(int i =0; i < this.previousGuesses.length(); i++) {
            if (this.previousGuesses.charAt(i) == guess)
                charFrequency++;
        }
        if (charFrequency == 0) {
            this.previousGuesses.append(guess);
        }

        while(!Character.isLetter(guess) || charFrequency>0) { //loop forcing the user to input a letter that has not been guessed yet
            if(!Character.isLetter(guess)) {
                System.out.println("Invalid entry! Please input a letter: ");
                input = scanner.nextLine();
                guess = Character.toLowerCase(input.charAt(0)); //converting the input to a lowercase char
                charFrequency = 0; ////resetting the frequency of the new guess to 0
                for(int i =0; i < this.previousGuesses.length(); i++) {
                    if (this.previousGuesses.charAt(i) == guess)
                        charFrequency++;
                }
            }
            if(charFrequency > 0) {
                System.out.println("Duplicate entry! Please input a new letter: ");
                input = scanner.nextLine();
                guess = Character.toLowerCase(input.charAt(0)); //converting the input to a lowercase char
                charFrequency = 0; //resetting the frequency of the new guess to 0
                for (int i = 0; i < this.previousGuesses.length(); i++) {
                    if (this.previousGuesses.charAt(i) == guess)
                        charFrequency++;
                }
            }
            if (charFrequency == 0) {
                this.previousGuesses.append(guess);
            }

        }
        return guess;
    }

    /**
     * playerId method returns player Id
     * @return String playerId
     */
    @Override
    public String playerId(){
        return "Manual Player";
    }

    /**
     * reset method resets the previousGuesses before each new phrase.
     */
    @Override
    public void reset(){
        previousGuesses = new StringBuilder();
    }

    /**
     * toString method returns string
     * @return
     */
    @Override
    public String toString() {
        return "WheelOfFortune Manual User";
    }

    /**
     * equals method compares the contents of two wheelOfFortuneUsers
     * @param o
     * @return boolean
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WheelOfFortuneUser that = (WheelOfFortuneUser) o;
        return Objects.equals(previousGuesses, that.previousGuesses) && Objects.equals(currentPhrase, that.currentPhrase);
    }


}