import java.util.Scanner;

/**
 * Abstract class game contains the code to loop through a set of guessing games and recording the results.
 */
public abstract class GuessingGame {
    protected Scanner scanner = new Scanner(System.in);

    /**
     * Play method plays a game and returns a GameRecord
     * @return GameRecord
     */
    public abstract GameRecord play();

    /**
     * playNext method asks if the next game should be played
     * @return boolean
     */
    public abstract boolean playNext();

    /**
     * playAll method plays a set of games and records and returns an AllGamesRecord object for the set.
     * @return AllGamesRecord object for the set
     */
    public AllGamesRecord playAll(){
        AllGamesRecord allGamesRecord = new AllGamesRecord();
        while(playNext()){
            GameRecord record = play();
            allGamesRecord.add(record);
        }
        return allGamesRecord;
    }

    /**
     * determines if the users input is valid or not
     * @param input
     * @return
     */
    protected  boolean isValidInput(String input){
        return input != null && !input.isEmpty();
    }

}
