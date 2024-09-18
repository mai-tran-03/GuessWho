import java.util.*;

/**
 * The GuessWho class implements a simple game where a user and a computer
 * can take turns guessing a randomly selected person from a list.
 */
public class GuessWho {
    private Scanner scanner;
    private static List<RandomPerson> randomPeople;

    /**
     * Constructs a GuessWho object and initializes the scanner and
     * list of random people.
     */
    public GuessWho() {
        scanner = new Scanner(System.in);
        randomPeople = makePeopleList();
    }

    /**
     * Generates a list of random people for the game.
     * 
     * @return the list of random people
     */
    private List<RandomPerson> makePeopleList() {
        randomPeople = RandomPerson.generateRandomPeople();
        Collections.shuffle(randomPeople);
        List<RandomPerson> selectRandom = randomPeople.subList(0, 5);
        return selectRandom;
    }

    /**
     * Prints the list of random people.
     */
    public void printPeopleList() {
        for (RandomPerson person : randomPeople) {
            System.out.println(person);
        }
    }

    /**
     * Prompts the user to select a game mode.
     */
    public void selectGameMode() {
        System.out.println("Welcome to the game of Guess Who!");
        System.out.println("There are two modes you can play");
        System.out.println("1) the computer guesses the person you have in mind, or");
        System.out.println("2) you guess the person the computer has randomly selected.");
    }

    /**
     * Plays user mode where the user guesses the computer's randomly selected person.
     * 
     * @param userPlayer     the player called user
     * @param computerPlayer the player called computer
     */
    public void playUserMode(User userPlayer, Computer computerPlayer) {
        System.out.println("Here is a list of people you can guess from:");
        printPeopleList();
        System.out.println(computerPlayer.selectPerson());
        System.out.println("Here is a list of questions you can ask:");
        userPlayer.printQuestions();
        userTurn(userPlayer, computerPlayer);
    }

    /**
     * Plays computer mode where the computer guesses the user's selectedperson.
     * 
     * @param userPlayer     the player called user
     * @param computerPlayer the player called computer
     */
    public void playComputerMode(User userPlayer, Computer computerPlayer) {
        System.out.println("Here is the list of people you can pick from:");
        printPeopleList();
        System.out.println("Pick a person in your head! Computer is going to guess them.");
        computerTurn(userPlayer, computerPlayer);
    }

    /**
     * Starts the game as long as the user enters valid mode.
     * 
     * @param userPlayer     the player called user
     * @param computerPlayer the player called computer
     */
    public void mainGame(User userPlayer, Computer computerPlayer) {
        String mode;
        boolean validMode = false;

        while (!validMode) {
            System.out.print("Which mode would you like to play? (please enter 'user' or 'computer') ");
            mode = scanner.nextLine();

            switch (mode) {
                case "user":
                    playUserMode(userPlayer, computerPlayer);
                    validMode = true;
                    break;
                case "computer":
                    playComputerMode(userPlayer, computerPlayer);
                    validMode = true;
                    break;
                default:
                    System.out.println("Invalid mode.");
            }
        }
    }

    /**
     * Represents a user's turn in the game.
     * The user asks the computer questions and makes a guess based on the answers.
     * 
     * @param userPlayer     the player called user
     * @param computerPlayer the player called computer
     */
    public void userTurn(User userPlayer, Computer computerPlayer) {
        Person computerSelectedPerson = computerPlayer.getSelectedPerson();
        boolean continueAsking = true;

        while (continueAsking) {
            int userQuestion = userPlayer.askQuestion();
            Boolean computerAnswer = computerPlayer.answerQuestion(userQuestion);
            System.out.println("Computer answers: " + computerPlayer.getYesNo(computerAnswer));
            System.out.print("Do you want to keep asking? ");

            Boolean userAnswer;
            try {
                userAnswer = userPlayer.getBoolean(scanner.nextLine());
                if (userAnswer) {
                    continueAsking = true;
                } else {
                    System.out.print("You guess: ");
                    Boolean computerConfirm = computerPlayer.confirmGuess(scanner.nextLine());

                    if (computerConfirm) {
                        System.out.println("You guessed right!");
                        continueAsking = false;
                    } else {
                        System.out.println("You're wrong!");
                        System.out.println("Computer selected: " + computerSelectedPerson.getPersonName());
                        continueAsking = false;
                    }
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    /**
     * Represents a computer's turn in the game.
     * The computer asks the user questions and makes a guess based on the answers.
     * 
     * @param userPlayer     the player called user
     * @param computerPlayer the player called computer
     */
    public void computerTurn(User userPlayer, Computer computerPlayer) {
        System.out.print("Have you pick a person? ");
        if (userPlayer.getBoolean(scanner.nextLine())) {
            System.out.println(userPlayer.selectPerson());
        }
        boolean computerWin = false;

        while (!computerWin) {
            Boolean validAnswer = computerPlayer.askQuestion();
            if (!validAnswer) {
                break;
            }
            System.out.print("You answer: ");
            Boolean userAnswer = userPlayer.getBoolean(scanner.nextLine());
            HashMap<Person, Boolean> potentialPerson = computerPlayer.getPotentialPerson(userAnswer);

            if (potentialPerson.size() == 1) {
                computerPlayer.makeGuessIfOnlyOnePersonRemains();
                computerWin = true;
            }
        }
    }

    /**
     * Runs the game of Guess Who with two playes called 'User' and 'Computer'.
     * 
     * @param args none
     */
    public static void main(String[] args) {
        GuessWho game = new GuessWho();
        User userPlayer = new User("User");
        Computer computerPlayer = new Computer("Computer", randomPeople);

        game.selectGameMode();
        game.mainGame(userPlayer, computerPlayer);

        game.scanner.close();
    }
}
