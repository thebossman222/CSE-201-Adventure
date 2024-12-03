/**
 * @Class: Player
 * @Authors: Caleb Krainman, Corbin Fulton, Andy Roberts, Mohamed Lemine E, Marissa Ellis, Ethan Jones
 * @Written: 11/30/2024
 * @Course: CSE 201B: Intro to Software Engineering
 * @Purpose: The Player class represents the player in the MiamiQuest game.
 * It manages the player's credits, failed exams, ability to drop or retake courses,
 * and handles course registration, exam taking, and graduation eligibility.
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Player {
    private int credits;           // The player's total credits
    private int failedExams;       // The number of exams the player has failed
    private boolean canDrop;       // Indicates if the player can drop a course
    private boolean canRetake;     // Indicates if the player can retake an exam
    private List<Course> courseList; // List of courses the player is registered in
    private int courseNumber;      // Counter for the number of courses registered
    private boolean gameWon = false;   

    /**
     * Constructor to initialize the player with default values.
     */
    public Player() {
        this.credits = 0;
        this.failedExams = 0;
        this.canDrop = true; // Player can drop a course initially
        this.canRetake = true; // Player can retake an exam initially
        this.courseList = new ArrayList<>(); // Initialize the course list
        this.courseNumber = 0; // Initialize course count
    }

    /**
     * Registers the player for a new course and adds it to the course list.
     * @return The newly registered Course object.
     */
    public Course registerCourse() {
        this.courseNumber++;
        int courseType = ((courseNumber - 1) % 4) + 1;
        Course newCourse = new Course(courseNumber);
        courseList.add(newCourse); // Add the new course to the list
        System.out.println("Course registered: " + newCourse.getName() + "\n");
        return newCourse;
    }

    /**
     * Checks and returns the player's current credits.
     * @return The player's total credits.
     */
    public int getCredits() {
        System.out.println("You currently have " + credits + " credits.");
        return credits;
    }

    /**
     * Checks if the player can drop a course.
     * @return True if the player can drop a course, false otherwise.
     */
    public boolean canDrop() {
        return canDrop;
    }

    /**
     * Allows the player to drop a course if possible.
     * @return True if the course was successfully dropped, false otherwise.
     */
    public boolean dropCourse() {
        if (canDrop && !courseList.isEmpty()) {
            courseNumber--;
            displayCourses();
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter the number of the course you want to drop: ");
            int courseNumber = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            if (courseNumber > 0 && courseNumber <= courseList.size()) {
                Course droppedCourse = courseList.remove(courseNumber - 1);
                canDrop = false; // Set canDrop to false once used
                System.out.println("Course dropped: " + droppedCourse.getName());
                return true;
            } else {
                System.out.println("Invalid course number.");
                return false;
            }
        } else {
            System.out.println("You have already used your drop option or have no courses to drop.");
            return false;
        }
    }

    /**
     * Allows the player to drop a specific course.
     * @param course The Course object to be dropped.
     * @return True if the course was successfully dropped, false otherwise.
     */
    public boolean dropCourse(Course course) {
        if (canDrop && courseList.contains(course)) {
            courseList.remove(course);
            canDrop = false; // Set canDrop to false once used
            System.out.println("Course dropped: " + course.getName());
            return true;
        } else {
            System.out.println("You have already used your drop option or the course is not in your course list.");
            return false;
        }
    }

    /**
     * Displays the player's total credits.
     */
    public void checkCredits() {
        System.out.println("Total credits: " + this.credits);
    }

    /**
     * Allows the player to take an exam for a course.
     */
    public void takeExam() {
        if (courseList.isEmpty()) {
            System.out.println("No courses available to take an exam.");
            return;
        }

        displayCourses();
        if (courseNumber > 0 && courseNumber <= courseList.size()) {
            Course currentCourse = courseList.get(courseNumber - 1);
            System.out.println("Starting exam for course: " + currentCourse.getName());
            Exam exam = new Exam();
            boolean passed = exam.startExam(currentCourse, this);

            if (passed) {
                System.out.println("Exam passed!");
                addCredits(3); // Add credits for passing an exam
                currentCourse.setPassed(true); // Mark the course as passed
            } else {
                System.out.println("Exam failed.");
                incrementFailedExams();
            }
        } else {
            System.out.println("Invalid course number.");
        }
    }

    /**
     * Allows the player to retake an exam if possible.
     * @return True if the exam was successfully retaken, false otherwise.
     */
    public boolean retakeExam() {
        if (canRetake) {
            canRetake = false; // Set canRetake to false once used
            System.out.println("Retaking an exam...");
            if (courseList.isEmpty()) {
                System.out.println("No courses available to retake an exam.");
                return false;
            }

            // Allow the player to choose which exam to retake
            displayCourses();
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter the number of the course you want to retake the exam for: ");
            int courseNumber = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            if (courseNumber > 0 && courseNumber <= courseList.size()) {
                Course currentCourse = courseList.get(courseNumber - 1);
                if (currentCourse.isPassed()) {
                    System.out.println("You have already passed this course.");
                    return false;
                }

                System.out.println("Retaking the exam for course: " + currentCourse.getName());
                Exam exam = new Exam();
                boolean passed = exam.startExam(currentCourse, this);

                if (passed) {
                    System.out.println("Exam passed!");
                    addCredits(3); // Add credits for passing an exam
                    currentCourse.setPassed(true); // Mark the course as passed
                } else {
                    System.out.println("Exam failed.");
                    incrementFailedExams();
                }
                return true;
            } else {
                System.out.println("Invalid course number.");
                return false;
            }
        } else {
            System.out.println("You have already used your retake option.");
            return false;
        }
    }

    /**
     * Increments the player's credits by the specified amount.
     * @param points The number of credits to add.
     */
    public void addCredits(int points) {
        credits += points;
    }

    /**
     * Increments the number of failed exams.
     */
    public void incrementFailedExams() {
        failedExams++;
    }

    /**
     * Gets the number of exams the player has failed.
     * @return The number of failed exams.
     */
    public int getFailedExams() {
        return failedExams;
    }

    /**
     * Gets the list of courses the player is registered in.
     * @return The list of courses.
     */
    public List<Course> getCourseList() {
        return this.courseList;
    }

    /**
     * Displays all registered courses along with their status (Passed/Not Passed).
     */
    public void displayCourses() {
        if (courseList.isEmpty()) {
            System.out.println("No courses registered yet.");
        } else {
            System.out.println("Registered courses:");
            for (int i = 0; i < courseList.size(); i++) {
                Course c = courseList.get(i);
                String status = c.isPassed() ? "Passed" : "Not Passed";
                System.out.println((i + 1) + ". " + c.getName() + " - " + status);
            }
        }
    }

    /**
     * Checks if the player is eligible to graduate.
     * @return True if the player can graduate, false otherwise.
     */
    public boolean canGraduate() {
        int passedCourses = 0;

        for (Course c : courseList) {
            if (c.isPassed()) {
                passedCourses++;
            }
        }

        // Assuming each passed course gives 3 credits and 12 credits are needed to graduate
        return credits >= 12 && passedCourses >= 4;
    }

    /**
     * Checks if the player can retake an exam.
     * @return True if the player can retake an exam, false otherwise.
     */
    public boolean canRetake() {
        return canRetake;
    }
    
    /**
     * returns if the game has been won or not
     * @return gameWon, a boolean, which is false while the game hasn't been won.
     */
    public boolean getGameWon() {
        return gameWon;
    }
    /**
     * Setter method to set win status
     * @param Won, true if game is won, false if not yet.
     */
    public void setGameWon(boolean Won) {
        gameWon = Won;
    }
}
