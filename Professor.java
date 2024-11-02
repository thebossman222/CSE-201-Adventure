import java.util.Random;

public class Professor {

    private String difficulty;
    private int extraCredit;

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

    public int assignExtraCredit() {
        if (randomizeDifficulty().equalsIgnoreCase("Easy")) {
            this.extraCredit = 2;
        } else {
            this.extraCredit = -2;
        }
        return this.extraCredit;
    }
}
