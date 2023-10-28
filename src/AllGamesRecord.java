import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;

/**
 * Class is a wrapper around a list, contains gameRecords in the list.
 */
public class AllGamesRecord {
    private List<GameRecord> records;
 //   private int numPhrases;

    /**
     * Constructor, initializes the array list and creates a new game object to determine the number of phrases
     */
    public AllGamesRecord() {
        records = new ArrayList<>();
    }
    /**
     * adds a GameRecord to the AllGamesRecord
     * @param record
     */
    public void add(GameRecord record){
        records.add(record);
    }

    /**
     * returns the average score for all games added to the record
     * @return double average
     */
    public double average(){
        if(records.isEmpty()){
            return 0;
        }
        double total = 0;
        for (GameRecord record: records){
            total += record.getScore();
        }
        return total/ records.size();
    }

    /**
     * returns the average score for all games of a particular player
     * @param playerId
     * @return double average
     */
    public double average(String playerId){
        if(records.isEmpty()){
            return 0;
        }
        double total = 0;
        int count = 0;
        for(GameRecord record: records){
            if(record.getPlayerID().equals(playerId)){
                total += record.getScore();
                count++;
            }
        }
        if (count ==0){
            return 0;
        }
        return total/count;
    }

    /**
     * returns a sorted list of the top n scores including player and score.
     * @param numScores
     * @return List<GameRecord>
     */
    public List<GameRecord> highGameList(int numScores){
        List<GameRecord> sortedList = new ArrayList<>(records);
        Collections.sort(sortedList, Collections.reverseOrder());

        if(sortedList.size() < numScores){
            return sortedList;
        }
        return sortedList.subList(0,numScores);
    }

    /**
     * returns a sorted list of the top n scores for the specified player.
     * @param playerId
     * @param numScores
     * @return List<GameRecord>
     */
    public List<GameRecord> highGameList(String playerId, int numScores){
        List<GameRecord> playerRecords = new ArrayList<>();
        for(GameRecord record: records){
            if(record.getPlayerID().equals(playerId)){
                playerRecords.add(record);
            }
        }
        Collections.sort(playerRecords, Collections.reverseOrder());
        if(playerRecords.size() < numScores){
            return playerRecords;
        }
        return playerRecords.subList(0,numScores);
    }

    /**
     * toString method to print the average score, top two scores, and all scores and player ids of the records
     * in the allGamesRecordList.
     * @return String
     */
    @Override
    public String toString() {
        System.out.println("Average Score: " + average());
        System.out.println("Top two scores: " + highGameList(2));
        return records.toString();
    }

    /**
     * equals method to compare to allGamesRecord lists, which compares the score and playerId of
     * each gameRecord
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AllGamesRecord that = (AllGamesRecord) o;
        return Objects.equals(records, that.records);
    }

}