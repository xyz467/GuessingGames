/**
 * WheelOfFortunePlayer Interface is implemented to create Wheel of Fortune Players
 */
public interface WheelOfFortunePlayer {
    /**
     * nextGuess method determines how the player will decide what letter to guess next
     * @return char guess
     */
    char nextGuess();

    /**
     * playerId will return the playerId
     * @return String playerId
     */

    String playerId();

    /**
     * Method to reset the game before each new phrase.
     */
    void reset();

    /**
     * Method to pass in the currentPhrase so the player can appropriately analyze it.
     * @param currentPhrase
     */
    void setCurrentPhrase(String currentPhrase);




}