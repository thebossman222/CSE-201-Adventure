import java.util.Random;

/**
 * Represents a Professor with a difficulty level and extra credit 
 * attributes that affect the gameplay in the MiamiQuest game.
 */
public class Professor {

    private String difficulty;
    private int extraCredit;

    /**
     * Randomizes the difficulty of the professor.
     * 
     * @return The difficulty level as a string, either "Easy" or "Hard".
     */
    public String randomizeDifficulty() {
        Random ran = new Random();
        int diff = ran.nextInt(2); // Generates 0 or 1
        if (diff == 0) {
            this.difficulty = "Easy";
        } else {
            this.difficulty = "Hard";
        }
        return this.difficulty;
    }

    /**
     * Assigns extra credit or deduction points based on the difficulty level of the professor.
     * 
     * @return The amount of extra credit, which is 2 for "Easy" and -2 for "Hard".
     */
    public int assignExtraCredit() {
        if (randomizeDifficulty().equalsIgnoreCase("Easy")) {
            this.extraCredit = 2; // Positive extra credit for easy difficulty
        } else {
            this.extraCredit = -2; // Negative extra credit for hard difficulty
        }
        return this.extraCredit;
    }
}
