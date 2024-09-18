import java.util.*;

/**
 * Represents a person with attributes such as name, gender, height, and
 * glasses.
 */
public class Person {
    // HashMap to store attribute names with corresponding integer keys
    private HashMap<Integer, String> attributeName;

    // HashMap to store attribute values with corresponding attribute names
    private HashMap<String, Boolean> attributeValue;

    private String personName;

    /**
     * Constructs a Person object with specified attributes.
     * 
     * @param personName the name of the person
     * @param gender     true if the person is male, false otherwise
     * @param height     true if the person is taller than 170 centimeters, false
     *                   otherwise
     * @param glasses    true if the person wears glasses, false otherwise
     * @param longHair   true if the person has hair longer than 30 centimeters,
     *                   false otherwise
     * @param piercings  true if the person has piercings, false otherwise
     * @param tattoos    true if the person has tattoos, false otherwise
     * @param hairColor  true if the person has black hair, false otherwise.
     */
    public Person(String personName, Boolean gender, Boolean height, Boolean glasses, Boolean longHair,
            Boolean piercings, Boolean tattoos, Boolean hairColor) {
        attributeName = new HashMap<>();
        attributeValue = new HashMap<>();

        // Populate attribute name map with integer keys and corresponding attribute
        // names
        attributeName.put(1, "Gender");
        attributeName.put(2, "Height");
        attributeName.put(3, "Glasses");
        attributeName.put(4, "Hair length");
        attributeName.put(5, "Piercings");
        attributeName.put(6, "Tattoos");
        attributeName.put(7, "Hair color");

        // Populate attribute value map with attribute names and corresponding boolean
        // values
        attributeValue.put(getAttributeName(1), gender);
        attributeValue.put(getAttributeName(2), height);
        attributeValue.put(getAttributeName(3), glasses);
        attributeValue.put(getAttributeName(4), longHair);
        attributeValue.put(getAttributeName(5), piercings);
        attributeValue.put(getAttributeName(6), tattoos);
        attributeValue.put(getAttributeName(7), hairColor);

        this.personName = personName;
    }

    /**
     * Returns the attribute name corresponding to the specified integer key.
     * 
     * @param number the integer key corresponding to the attribute name
     * @return the attribute name assosciated with the integer key
     */
    public String getAttributeName(Integer number) {
        return attributeName.get(number);
    }

    /**
     * Gets the attribute value associated with the specified attribute name.
     * 
     * @param key the attribute name used to retrieve the value
     * @return the boolean value associated with the attribute name
     */
    public Boolean getAttributeValue(String key) {
        return attributeValue.get(key);
    }

    /**
     * Returns the name of the person.
     */
    public String getPersonName() {
        return this.personName;
    }

    /**
     * Returns a string representation of the person, including their name, gender,
     * height, and glasses.
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Name: " + getPersonName() + "\n");
        sb.append(getAttributeName(1) + ": " + getAttributeValue("Gender") + "\n");
        sb.append(getAttributeName(2) + ": " + getAttributeValue("Height") + "\n");
        sb.append(getAttributeName(3) + ": " + getAttributeValue("Glasses") + "\n");
        sb.append(getAttributeName(4) + ": " + getAttributeValue("Hair length") + "\n");
        sb.append(getAttributeName(5) + ": " + getAttributeValue("Piercings") + "\n");
        sb.append(getAttributeName(6) + ": " + getAttributeValue("Tattoos"));
        sb.append(getAttributeName(7) + ": " + getAttributeValue("Hair color") + "\n");
        return sb.toString();
    }
}