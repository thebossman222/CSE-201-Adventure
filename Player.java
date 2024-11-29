import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Player {
    private int credits;
    private int failedExams;
    private boolean canDrop;
    private boolean canRetake;
    private List<Course> courseList;
    private int courseNumber;

    // Constructor to initialize player with default values
    public Player() {
        this.credits = 0;
        this.failedExams = 0;
        this.canDrop = true; // Player can drop a course initially
        this.canRetake = true; // Player can retake an exam initially
        this.courseList = new ArrayList<>(); // Initialize the course list
    }

    // Registers the player for a new course and adds it to the course list
    public Course registerCourse() {
        this.courseNumber++;
        Course newCourse = new Course(courseNumber);
        courseList.add(newCourse); // Add the new course to the list
        System.out.println("Course registered: " + newCourse.getName() + "\n");
        return newCourse;
    }

    // Checks and returns the player's current credits
    public int getCredits() {
        System.out.println("You currently have " + credits + " credits.");
        return credits;
    }

    // Checks if the player can drop a course
    public boolean canDrop() {
        return canDrop;
    }

    // Allows the player to drop a course if possible
    public boolean dropCourse() {
        if (canDrop && !courseList.isEmpty()) {
            displayCourses();
            Scanner scanner = new Scanner(System.in);
            System.out
                    .print("Enter the number of the course you want to drop: ");
            int courseNumber = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            if (courseNumber > 0 && courseNumber <= courseList.size()) {
                Course droppedCourse = courseList.remove(courseNumber - 1);
                canDrop = false; // Set canDrop to false once used
                System.out
                        .println("Course dropped: " + droppedCourse.getName());
                return true;
            } else {
                System.out.println("Invalid course number.");
                return false;
            }
        } else {
            System.out.println(
                    "You have already used your drop option or have no courses to drop.");
            return false;
        }
    }

    // Allows the player to drop a specific course
    public boolean dropCourse(Course course) {
        if (canDrop && courseList.contains(course)) {
            courseList.remove(course);
            canDrop = false; // Set canDrop to false once used
            System.out.println("Course dropped: " + course.getName());
            return true;
        } else {
            System.out.println(
                    "You have already used your drop option or the course is not in your course list.");
            return false;
        }
    }

    public void checkCredits() {
        System.out.println("Total credits:" + this.credits);
    }

    // Allows the player to take an exam
    public void takeExam() {
        if (courseList.isEmpty()) {
            System.out.println("No courses available to take an exam.");
            return;
        }

        displayCourses();
        if (courseNumber > 0 && courseNumber <= courseList.size()) {
            Course currentCourse = courseList.get(courseNumber - 1);
            System.out.println(
                    "Starting exam for course: " + currentCourse.getName());
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

    // Allows the player to retake an exam if possible
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
            System.out.print(
                    "Enter the number of the course you want to retake the exam for: ");
            int courseNumber = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            if (courseNumber > 0 && courseNumber <= courseList.size()) {
                Course currentCourse = courseList.get(courseNumber - 1);
                if (currentCourse.isPassed()) {
                    System.out.println("You have already passed this course.");
                    return false;
                }

                System.out.println("Retaking the exam for course: "
                        + currentCourse.getName());
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

    // Increments credits (called when passing an exam)
    public void addCredits(int points) {
        credits += points;
    }

    // Increments failed exams (called when failing an exam)
    public void incrementFailedExams() {
        failedExams++;
    }

    // Getter for failed exams
    public int getFailedExams() {
        return failedExams;
    }

    // Getter for the course list
    public List<Course> getCourseList() {
        return this.courseList;
    }

    // Displays all registered courses
    public void displayCourses() {
        if (courseList.isEmpty()) {
            System.out.println("No courses registered yet.");
        } else {
            System.out.println("Registered courses:");
            for (int i = 0; i < courseList.size(); i++) {
                Course c = courseList.get(i);
                String status = c.isPassed() ? "Passed" : "Not Passed";
                System.out
                        .println((i + 1) + ". " + c.getName() + " - " + status);
            }
        }
    }

    // Checks if the player can graduate
    public boolean canGraduate() {
        int passedCourses = 0;

        for (Course c : courseList) {
            if (c.isPassed()) {
                passedCourses++;
            }
        }

        // Assuming each passed course gives 3 credits and 12 credits are needed
        // to graduate
        return credits >= 12 && passedCourses >= 4;
    }

    // Getter for player's ability to retake an exam
    public boolean canRetake() {
        return canRetake;
    }
}
