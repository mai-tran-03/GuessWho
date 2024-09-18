public class Player {
    private String[] questions;

    /**
     * Constructs a Player object with an array of questions.
     */
    public Player() {
        questions = makeQuestions();
    }

    private String[] makeQuestions() {
        questions = new String[8];
        
        questions[0] = "";
        questions[1] = "Is your person a male?";
        questions[2] = "Is your person taller than 170 centimeters?";
        questions[3] = "Does your person have glasses?";
        questions[4] = "Does your person has hair longer than 30 centimeters?";
        questions[5] = "Does your person has piercings?";
        questions[6] = "Does your person has tattoos?";
        questions[7] = "Does your person has black hair?";

        return questions;
    }

    /**
     * Returns the questions
     */
    public String[] getQuestions() {
        return questions;
    }

    /**
     * Prints the questions.
     */
    public void printQuestions() {
        for (int i = 1; i < questions.length; i++) {
            System.out.println(i + ". " + questions[i]);
        }
    }

    /**
     * Returns a string indicating the player has selected a person.
     */
    public String selectPerson() {
        return "I have selected a person.";
    }

    /**
     * Gets a boolean value based on the input string.
     * 
     * @param inputString input to be evaluated, should represent
     *                    affirmative or negative values
     * @return true if input is valid, otherwise false
     * @throws IllegalArgumentException if the input string is null or does not
     *                                  represent a valid boolean value
     */
    public Boolean getBoolean(String inputString) {
        if (inputString != null) {
            String trimmedInput = inputString.trim().toLowerCase();
            if (trimmedInput.equals("yes") || trimmedInput.equals("y") || trimmedInput.equals("true")) {
                return true;
            } else if (trimmedInput.equals("no") || trimmedInput.equals("n") || trimmedInput.equals("false")) {
                return false;
            }
        }
        throw new IllegalArgumentException("You have entered an invalid input.");
    }

    /** Converts a boolean value into a corresponding 'Yes' or 'No'
     * 
     * @param inputBoolean boolean value to be converted to string
     * @return 'Yes' if the input is true, 'No' if the input is false
     */
    public String getYesNo(Boolean inputBoolean) {
        if (inputBoolean) {
            return "Yes";
        } else {
            return "No";
        }
    }
}