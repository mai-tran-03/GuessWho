import java.io.*;
import java.util.*;

/**
 * Represents a randomly generated person with attributes such as name, gender, height, and glasses.
 */
public class RandomPerson extends Person {
    // Constants for defining height ranges for males and females
    private static final int MALE_HEIGHT_MIN = 163;
    private static final int MALE_HEIGHT_MAX = 193;
    private static final int FEMALE_HEIGHT_MIN = 150;
    private static final int FEMALE_HEIGHT_MAX = 178;

    // Constants for defining hair length
    private static final int MALE_LONG_HAIR_MAX = 40;
    private static final int FEMALE_LONG_HAIR_MAX = 76;

    private static Random random = new Random();
    private String gender;
    private int height;
    private String glasses;
    private int longHair;
    private String piercings;
    private String tattoos;
    private String hairColor;

    /**
     * Constructs a RandomPerson object.
     * 
     * @param personName name of the person
     * @param gender     the gender of the person (true for male, false for female)
     * @param height     the height of the person in centimeters
     * @param glasses    true if the person has glasses, false otherwise
     * @param longHair   true if the person has hair longer than 30 centimeters, false otherwise
     * @param piercings  true if the person has piercings, false otherwise
     * @param tattoos    true if the person has tattoos, false otherwise
     * @param hairColor  true if the person has black hair, false otherwise.
     */
    public RandomPerson(String name, Boolean gender, int height, Boolean glasses, int longHair, Boolean piercings, Boolean tattoos, String hairColor) {
        super(name, gender, height >= 170, glasses, longHair >= 30, piercings, tattoos, hairColor.equals("Black"));
        this.height = height;
        this.gender = gender ? "Male" : "Female";
        this.glasses = getYesNo(glasses);
        this.longHair = longHair;
        this.piercings = getYesNo(piercings);
        this.tattoos = getYesNo(tattoos);
        this.hairColor = hairColor;
    }

    /**
     * Generates a list of RandomPerson objects by reading a CSV file containing
     * randomly generated names and genders.
     * 
     * @return a list of randomly generated people
     */
    public static List<RandomPerson> generateRandomPeople() {
        File file = new File("person-names-gender.csv");
        List<RandomPerson> randomPeople = new ArrayList<>();
        try (Scanner scanner = new Scanner(file)) {
            scanner.nextLine(); // skip header
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                RandomPerson randomPerson = getPerson(line);
                randomPeople.add(randomPerson);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        }
        return randomPeople;
    }

    /**
     * Creates a RandomPerson object from a line of CSV data.
     * 
     * @param line a line of CSV data containing first name, last name, and gender
     * @return a randomly generated person with attributes
     */
    public static RandomPerson getPerson(String line) {
        String[] splitLine = line.split(",");
        String name = splitLine[0];
        Boolean isMale = splitLine[2].equals("Male");
        int height = isMale ? generateRandomValue(MALE_HEIGHT_MIN, MALE_HEIGHT_MAX)
                : generateRandomValue(FEMALE_HEIGHT_MIN, FEMALE_HEIGHT_MAX);
        Boolean glasses = random.nextBoolean();
        int hairLength = isMale ? generateRandomValue(0, MALE_LONG_HAIR_MAX)
                : generateRandomValue(0, FEMALE_LONG_HAIR_MAX);
        Boolean piercings = random.nextBoolean();
        Boolean tattoos = random.nextBoolean();
        String hairColor = generateRandomColor();
        return new RandomPerson(name, isMale, height, glasses, hairLength, piercings, tattoos, hairColor);
    }

    /**
     * Generates a random number between a specified minimum and maximum value.
     * 
     * @param minVal the minimum value in centimeters
     * @param maxVal the maximum value in centimeters
     * @return a random value between the specified range
     */
    private static int generateRandomValue(int minVal, int maxVal) {
        return random.nextInt(maxVal - minVal) + minVal;
    }

    /**
     * Generates a random hair color from an array of hair colors.
     * 
     * @return hair color
     */
    private static String generateRandomColor() {
        String[] hairColors = {"Black", "Blonde", "Brown", "Red"};
        return hairColors[random.nextInt(hairColors.length)];
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

    @Override
    /**
     * Returns a string representation of the RandomPerson object, including
     * attributes such as name, gender, height, and glasses.
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Name: " + getPersonName() + "\n");
        sb.append("Gender: " + getGender() + "\n");
        sb.append("Height: " + getHeight() + "\n");
        sb.append("Has glasses? " + getGlasses() + "\n");
        sb.append("Long hair: " + getHairLength() + "\n");
        sb.append("Has piercings? " + getPiercings() + "\n");
        sb.append("Has tattoos? " + getTattoos() + "\n");
        sb.append("Hair color: " + getHairColor() + "\n");
        return sb.toString();
    }

    /**
     * Returns the gender of the person.
     */
    public String getGender() {
        return gender;
    }

    /**
     * Returns the height of the person.
     */
    public int getHeight() {
        return height;
    }

    /**
     * Returns a string indicating whether the person wears glasses.
     */
    public String getGlasses() {
        return glasses;
    }

    /**
     * Returns the hair length of the person.
     */
    public int getHairLength() {
        return longHair;
    }

    /**
     * Returns a string indicating whether the person has piercings.
     */
    public String getPiercings() {
        return piercings;
    }

    /**
     * Returns a string indicating whether the person has tattoos.
     */
    public String getTattoos() {
        return tattoos;
    }

    /**
     * Returns the hair color of the person.
     */
    public String getHairColor() {
        return hairColor;
    }
}
