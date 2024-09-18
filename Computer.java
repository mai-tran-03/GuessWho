import java.util.*;

public class Computer extends Player {
    private String name;
    private List<RandomPerson> peopleList;
    private HashMap<Person, Boolean> peopleMap;
    private Person selectedPerson;
    
    private int questionNumber;
    private int initialQueueSize;
    private Queue<String> questionQueue;

    /**
     * Constructs a Computer object.
     * 
     * @param name       name of the computer
     * @param peopleList list of randomly generated people
     */
    public Computer(String name, List<RandomPerson> peopleList) {
        super();
        this.name = name;
        this.peopleList = peopleList;
        this.selectedPerson = computerSelectPerson();
        this.peopleMap = listToMap();

        this.initialQueueSize = getQuestions().length - 1;
        this.questionNumber = 0;
        this.questionQueue = arrayToQueue();
    }

    /**
     * Returns a randomly selected person from a list of people.
     */
    private Person computerSelectPerson() {
        Random random = new Random();
        return peopleList.get(random.nextInt(peopleList.size()));
    }

    /**
     * Creates a new HashMap to store a person with corresponding boolean value,
     * to keep track of whether the person is a valid option.
     * 
     * @return a hashmap of a person and boolean
     */
    private HashMap<Person, Boolean> listToMap() {
        peopleMap = new HashMap<>();
        for (Person person : peopleList) {
            peopleMap.put(person, true);
        }
        return peopleMap;
    }

    /**
     * Converts an array of questions to a linked list.
     * 
     * @return a queue of questions.
     */
    private Queue<String> arrayToQueue() {
        questionQueue = new LinkedList<>();
        String[] questions = getQuestions();
        for (int i = 1; i < questions.length; i++) {
            questionQueue.add(questions[i]);
        }
        return questionQueue;
    }

    /**
     * Increases the question number.
     */
    private void incrementNumber() {
        questionNumber += 1;
    }

    /**
     * Returns the question number.
     */
    private int getNumber() {
        return this.questionNumber;
    }

    /**
     * Returns the computer's selected person.
     */
    public Person getSelectedPerson() {
        return selectedPerson;
    }

    /**
     * Returns a string indicating the computer has selected a person.
     */
    public String selectPerson() {
        return name + ": " + super.selectPerson();
    }

    /**
     * Determines whether the selected person has a corresponding attribute
     * according to the question.
     * 
     * @param questionNumber the question number
     * @return true if the person has the attribute, false otherwise
     */
    public Boolean answerQuestion(int questionNumber) {
        String attribute = selectedPerson.getAttributeName(questionNumber);
        if (selectedPerson.getAttributeValue(attribute) == true) {
            return true;
        }
        return false;
    }

    /**
     * Determines whether the user guesses the selected person correctly.
     * 
     * @param userGuess user's guess of the perso's name
     * @return true if the user's guess is correct, false otherwise
     */
    public Boolean confirmGuess(String userGuess) {
        if (selectedPerson.getPersonName().equals(userGuess)) {
            return true;
        }
        return false;
    }

    /**
     * Continues to print the questions until the queue of questions is empty.
     * 
     * @return true if queue is not empty, false otherwise
     */
    public Boolean askQuestion() {
        if (getNumber() == initialQueueSize) {
            System.out.println("No such person exists. Or there are multiple people matching the descriptions.");
            return false;
        }
        System.out.println("Computer asks: " + questionQueue.peek());
        questionQueue.poll();
        incrementNumber();
        return true;
    }

    /**
     * Computer guesses the only person left in the map.
     */
    public void makeGuessIfOnlyOnePersonRemains() {
        if (peopleMap.size() == 1) {
            String name = peopleMap.keySet().iterator().next().getPersonName();
            System.out.println("Your person is " + name + ".");
        }
    }

    /**
     * Iterates over the map of people to eliminate or keep a person in the map.
     * 
     * @param answer user's answer to a question
     * @return a hashmap of valid people
     */
    public HashMap<Person, Boolean> getPotentialPerson(Boolean answer) {
        int questionNumber = getNumber();
        Iterator<Map.Entry<Person, Boolean>> iterator = peopleMap.entrySet().iterator();

        while (iterator.hasNext()) {
            Map.Entry<Person, Boolean> entry = iterator.next();
            Person person = entry.getKey();
            String key = person.getAttributeName(questionNumber);

            if (answer) {
                if (!person.getAttributeValue(key)) {
                    peopleMap.put(person, false);
                    iterator.remove();
                }
            } else {
                if (person.getAttributeValue(key)) {
                    peopleMap.put(person, false);
                    iterator.remove();
                }
            }
        }
        return peopleMap;
    }
}