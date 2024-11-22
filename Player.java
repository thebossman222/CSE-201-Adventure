import java.util.ArrayList;
import java.util.List;

public class Player {
    private int credits;
    private int failedExams;
    private boolean canDrop;
    private boolean canRetake;
    private List<Class> classList; // List to store registered classes

    
    /**
    * Constructor to initialize player with defaut values for credits, failedExams, 
    * canDrop, canRetake, and classList. 
    */
    public Player() {
        this.credits = 0;
        this.failedExams = 0;
        this.canDrop = true; // Player can drop a class initially
        this.canRetake = true; // Player can retake an exam initially
        this.classList = new ArrayList<>(); // Initialize the class list
    }
    
    /**
    * Registers the player object for a new in game class and adds the new class
    * to the classList
    *
    * @return Class the registered class 
    */
    public Class registerClass() {
        Class newClass = new Class();
        classList.add(newClass); // Add the new class to the list
        System.out.println("Class registered.");
        return newClass;
    }

    /**
    * Checks and returns the player current credits
    *
    * @return int the player's current credits
    */
    public int checkCredits() {
        System.out.println("You currently have " + credits + " credits.");
        return credits;
    }

    /**
    * Checks whether or not a player can drop a class
    *
    * @return boolean true if player can drop class and false otherwise.
    */
    public boolean canDropClass() {
        return canDrop;
    }

    /**
    * Allows player to drop a class and checks if possible
    *
    * @return boolean true if player dropped class successfully and false otherwise
    */
    public boolean dropClass() {
        if (canDrop && !classList.isEmpty()) {
            classList.remove(classList.size() - 1); // Remove the most recent class
            canDrop = false; // Set canDrop to false once used
            System.out.println("Class dropped.");
            return true;
        } else {
            System.out.println("You have already used your drop option or have no classes to drop.");
            return false;
        }
    }

    /**
    * Allows the player to take an exam
    * 
    * @param currentClass the class whose exam the user is taking
    * @return Exam the exam to be taken.
    */
    public Exam takeExam(Class currentClass) {
        System.out.println("Starting exam for class: " + currentClass.displayClassInfo());
        Exam exam = new Exam(currentClass.getQuestions(), 60); // Use class-specific questions
        exam.startExam();
        return exam;
    }

    /**
    * Allows the player to retake an exam and checks if possible
    *
    * @return boolean true if exam retake is available and false otherwise
    */
    public boolean retakeExam() {
        if (canRetake) {
            canRetake = false; // Set canRetake to false once used
            System.out.println("Retaking the exam...");
            Exam exam = new Exam();
            exam.startExam();
            return true;
        } else {
            System.out.println("You have already used your retake option.");
            return false;
        }
    }

    // (could be called when passing an exam)
    /**
    * Increment player's credits 
    *
    * @param points the points to be added to the total player credits
    */
    public void addCredits(int points) {
        credits += points;
    }

    // (could be called when failing an exam)
    /**
    * Increases the failedExams variable
    */
    public void incrementFailedExams() {
        failedExams++;
    }
    
    /**
    * Simple getter method for failed exams instance variable
    * 
    * @return int the number of failed exams
    */
    public int getFailedExams() {
        return failedExams;
    }

    /**
    * Displays all registred classes for user in organized way.
    */
    public void displayClasses() {
        if (classList.isEmpty()) {
            System.out.println("No classes registered yet.");
        } else {
            System.out.println("Registered classes:");
            for (int i = 0; i < classList.size(); i++) {
                System.out.println("Class " + (i + 1) + ": " + classList.get(i).displayClassInfo());
            }
        }
    }

    /**
    * Checks to see if player has satisfied conditions to win game (graduate)
    *
    * @return boolean true if player can graduate and false otherwise
    */
    public boolean canGraduate() {
        int hardClasses = 0;
        int totalClassesPassed = classList.size();

        for (Class c : classList) {
            if (c.getProfessorDifficulty().equalsIgnoreCase("Hard")) {
                hardClasses++;
            }
        }

        int totalExamsRequired = hardClasses * 2 + (totalClassesPassed - hardClasses);
        return totalClassesPassed >= 4 && totalExamsRequired >= 4;
    }
}
