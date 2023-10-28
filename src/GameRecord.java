import java.util.Objects;

/**
 * Class keeps tracks of the score (integer) and player id(String) for a single play of a game. It implements
 * Comparable and provides a default implementation of compareTo which compares scores.
 */
public class GameRecord implements Comparable<GameRecord> {
    private int score;
    private String playerId;

    /**
     * Constructor, initializes score and player id with the parameters
     * @param score
     * @param playerId
     */
    public GameRecord(int score, String playerId) {
        this.score = score;
        this.playerId = playerId;
    }

    /**
     * returns the score
     * @return int score
     */
    public int getScore() {
        return score;
    }


    /**
     * returns the player id
     * @return String playerId
     */
    public String getPlayerID() {
        return playerId;
    }

    /**
     * toString method to print player id and associated score
     * @return String
     */
    @Override
    public String toString() {
        return playerId + ": " + score + " points";
    }

    /**
     * CompareTo method to compare to gameRecords by the score. Higher score is better.
     * @param other the object to be compared.
     * @return boolean
     */
    @Override
    public int compareTo(GameRecord other) {
        if (this.score > other.score) {
            return this.score - other.score;
        }
        else if (this.score < other.score) {
            return this.score - other.score;
        }
        else {
            return 0;
        }
    }

    /**
     * equals method to compare the score and playerId of two gameRecords
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GameRecord that = (GameRecord) o;
        return score == that.score && Objects.equals(playerId, that.playerId);
    }


}