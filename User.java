import java.util.*;

public class User extends Player {
    private String name;
    private Scanner scanner;

    /**
     * Constructs a User object.
     * 
     * @param name name of the user
     */
    public User(String name) {
        super();
        this.name = name;
        this.scanner = new Scanner(System.in);
    }

    /**
     * Returns a string indicating the user has selected a person.
     */
    public String selectPerson() {
        return name + ": " + super.selectPerson();
    }

    /**
     * Gets a valid input corrosponding to the question number to ask the computer
     * 
     * @return the question number
     */
    public int askQuestion() {
        String[] questions = getQuestions();
        int questionLength = questions.length - 1;
        int questionNumber;
        while (true) {
            try {
                System.out.print("Your question number: ");
                questionNumber = scanner.nextInt();
                if (questionNumber < 1 || questionNumber > questionLength) {
                    System.out.println("Invalid question number.");
                    System.out.println("Please enter a number between 1 and " + questionLength + ".");
                    continue; // ask again for valid input
                }
                break; // valid input, exit loop
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid integer.");
                scanner.next(); // clear invalid input
            }
        }
        System.out.println("You asked: " + getQuestions()[questionNumber]);
        return questionNumber;
    }
}