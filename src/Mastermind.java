import java.util.Objects;
import java.util.Random;

/**
 * Class creates a Mastermind games that generates a secret code, does not show it, and then
 * provides feedback for each of the user's guesses. The secret code is randomly generated for
 * each new game. The game includes six colors (Red, Green, Blue, Yellow, Orange, and Purple).
 * The program also prints out the number of exact and partial matches as the feedback for each guess
 */
public class Mastermind extends GuessingGame{
    private static final int CODESIZE = 4;
    private static final char[] COLORS = {'R', 'G', 'B', 'Y', 'O', 'P'};
    private String secretCode;

    /**
     * Constructor, calls generate secret code
     */
    public Mastermind() {
        generateSecretCode();
    }

    /**
     * generates the secret code based on the codesize and colors available.
     */
    private void generateSecretCode() {
        Random rand = new Random();
        StringBuilder code = new StringBuilder(CODESIZE);
        for (int i = 0; i < CODESIZE; i++) {
            code.append(COLORS[rand.nextInt(COLORS.length)]);
        }
        secretCode = code.toString();
    }

    /**
     * method to check the exact correct guesses from the user
     * @param secret
     * @param guess
     * @return int exacts
     */
    public int checkExacts(StringBuilder secret, StringBuilder guess) {
        int exacts = 0;
        for (int i = 0; i < CODESIZE; i++) {
            if (secret.charAt(i) == guess.charAt(i)) {
                exacts++;
                secret.setCharAt(i, '-');
                guess.setCharAt(i, '*');
            }
        }
        return exacts;
    }

    /**
     * method to check the partial correct guesses from the user
     * @param secret
     * @param guess
     * @return int partials
     */
    public int checkPartials(StringBuilder secret, StringBuilder guess) {
        int partials = 0;
        for (int i = 0; i < CODESIZE; i++) {
            for (int j = 0; j < CODESIZE; j++) {
                if (secret.charAt(i) == guess.charAt(j)) {
                    partials++;
                    secret.setCharAt(i, '-');
                    guess.setCharAt(j, '*');
                }
            }
        }
        return partials;
    }

    /**
     * method to ensure that the users input was valid
     * @param input
     * @return boolean
     */
    @Override
    protected boolean isValidInput(String input){
        return super.isValidInput(input) && input.length() == CODESIZE;
    }

    /**
     * method plays a game of Mastermind
     * @return GameRecord
     */
    @Override
    public GameRecord play() {
        int score = 0;
        System.out.println("Welcome to Mastermind!");
        while (true) {
            System.out.println("Enter your four color guess (RGBYOP) or 'quit' to exit:");
            String guess = scanner.nextLine().toUpperCase();
            if(!isValidInput(guess)){
                System.out.println("Please enter a valid guess of length " + CODESIZE);
                continue;
            }
            if ("QUIT".equals(guess)) {
                System.out.println("Thanks for playing! Secret was: " + secretCode);
                break;
            }

            StringBuilder secretSB = new StringBuilder(secretCode);
            StringBuilder guessSB = new StringBuilder(guess);
            int exacts = checkExacts(guessSB, secretSB);
            int partials = checkPartials(guessSB, secretSB);

            if (exacts == CODESIZE) {
                System.out.println("Congratulations! You guessed the secret code: " + secretCode);
                break;
            } else {
                System.out.println(exacts + " exact, " + partials + " partial.");
                score++;
            }
        }
        return new GameRecord(100 - score, "MasterMind User");
    }

    /**
     * playNext method asks if the next game should be played
     * @return boolean
     */
    @Override
    public boolean playNext(){
        if(super.playNext()){
            generateSecretCode();
            return true;
        }
        return false;
    }

    /**
     * toString returns String with secretCode
     * @return String
     */
    @Override
    public String toString() {
        return "Mastermind{" +
                "secretCode='" + secretCode + '\'' +
                '}';
    }

    /**
     * equals method compares contents of two Mastermind games
     * @param o
     * @return boolean
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Mastermind that = (Mastermind) o;
        return Objects.equals(secretCode, that.secretCode);
    }


    public static void main(String[] args) {
        Mastermind game = new Mastermind();
        AllGamesRecord record = game.playAll();
        System.out.println(record);
    }
}
