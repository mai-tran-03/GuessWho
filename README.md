## Overview
The `GuessWho.java` file uses the superclass `Player` and the subclasses `User` and `Computer` to execute a game of Guess Who. The file also uses instances of the `Person.java` file to create the possible characters in the game. There are two methods in `GuessWho`, each runs a specific mode of the game. The program works efficiently to lay down the rules for the user to play whichever mode they want by using inheritance and encapsulation.

### User mode
The first method `userTurn` allows you to guess the computer's mystery person. The method contains a while loop that will continue to let you ask questions until you choose to guess. When you feel ready to declare the mystery person, you can answer "No" when prompted. You can then enter your guess and the computer will confirm whether you were right or wrong.

### Computer mode
The second method `computerTurn` also uses a while loop, in which the computer continues to ask all questions until it has eliminate all unlikely persons, it will win by guessing the only possible person.

### Person class
`Person` creates instances of people that `GuessWho` uses as characters. By using a series of `HashMap`, the characters are created with corresponding boolean characteristics to a certain feature, for example, male is set to `true` and female is set to `false`.

### Player class
`Player` creates an instance of an array of questions that the `Player` can ask. It allows both the user and computer to access the questions. It also converts yes/no to true/false and vice versa.

### User class
The `User.java` file has the information and methods to efficiently run the user mode . It is a class extending the superclass `Player` that modifies and creates specific methods to properly create a `User` player.

### Computer class
The other file contains the subclass `Computer` which uses hashmaps and arrays to eliminate unlikely answers and ultimatly guess the correct person. By using methods like `makeGuessIfOnlyOnePersonRemains` (return `Boolean`) and `getPotentialPerson` (return `List` of potential persons), the program can guess who the user has selected without asking all the questions. 

## Discussion
* The aspects that make the project interesting and substantive are tied into the ability to play different modes where the user can challenge themselves against AI or be pleasantly shocked when their own person is printed. The purpose is essentially to be entertaining.
* The use of inheritance allows each subclass to have different behavior from each other and able to call the superclass and retain some of the same ideas. These subclasses can also alter and add methods, thus leading to different behaviors. The subclasses `User` and `Computer` use inheritance in the method `selectPerson` and the general constructor, but they also create other methods like `answerQuestion` or `confirmGuess`. 
* The program uses hashmaps allowing us to access the characteristic values of each individual without having specific variables for each person and their corresponding characteristics. Using hashmaps with the `potentialPerson` list was also best because we can use each person as a key and store whether they are a possibility for the guess as a boolean value.  
* Using a queue to ask the questions is an efficient method. The computer can peek at the top item of the queue and remove that question easily. The idea of first in first out keeps the questions organized as the computer only accesses one question at a time.

## Usage
With this program you can:
* Play two different game modes
* Select a version in which you guess a randomly selected person
* Play a game where an AI algorithm guesses the person you had in mind

Before running the program, remember to enter `javac *.java` to properly compile all the files.
```
$ javac *.java
$ java GuessWho
```
Once done, you may run the following. Which will look something like this:
```
Welcome to the game of Guess Who!
There are two modes you can play
1) the computer guesses the person you have in mind, or
2) you guess the person the computer has randomly selected.
Which mode would you like to play? (please enter 'user' or 'computer'):
```
As the user, you can enter either game mode. To demonstrate, let's run the 'user' version first:
```
    Here is a list of people you can guess from:
    Name: Carina
    Gender: Female
    Height: 173
    Has glasses? Yes
    Long hair: 61
    Has piercings? No
    Has tattoos? No
    Hair color: Black

    Name: Sam
    Gender: Male
    Height: 182
    Has glasses? Yes
    Long hair: 38
    Has piercings? No
    Has tattoos? Yes
    Hair color: Red

    Name: Miley
    Gender: Female
    Height: 173
    Has glasses? Yes
    Long hair: 36
    Has piercings? No
    Has tattoos? No
    Hair color: Brown

    Name: Florrie
    Gender: Female
    Height: 151
    Has glasses? No
    Long hair: 20
    Has piercings? Yes
    Has tattoos? No
    Hair color: Brown

    Name: Patrick
    Gender: Male
    Height: 182
    Has glasses? Yes
    Long hair: 31
    Has piercings? Yes
    Has tattoos? No
    Hair color: Black

    Computer: I have selected a person.
    Here is a list of questions you can ask:
    1. Is your person a male?
    2. Is your person taller than 170 centimeters?
    3. Does your person have glasses?
    4. Does your person has hair longer than 30 centimeters?
    5. Does your person has piercings?
    6. Does your person has tattoos?
    7. Does your person has black hair?
```
Let's suppose the user wants to ask question number 1, enter '1'
```
    You asked: Is your person a male?
    Computer answered: Yes
    Do you want to keep asking? 
```
As the user parses through the list, they find that there are two male persons so they should keep asking questions, enter 'Yes'
```
    Your question number: 
```
This time the user asks question number 5 because after comparing the two people ('Sam' and 'Patrick'), they find that one has piercings and one does not, enter '5'
```
    You asked: Does your person have piercings?
    Computer answered: No
    Do you want to keep asking? 
```
Now the user should have a good idea of the mysterious person, enter 'No'
```
    Your Guess: 
```
The user may then enter the name of the person, let's say the user entered 'Sam'
```
    You guessed right!
```
In the case the user did not guess correctly, the program would simply result in 
    ```
    You're wrong!
    Computer selected: Sam
    ```
If the user wishes to play the 'computer' version instead, the result would look like as follows:
```
    Here is the list of people you can pick from:
    Name: Patrick
    Gender: Male
    Height: 188
    Has glasses? No
    Long hair: 38
    Has piercings? No
    Has tattoos? No
    Hair color: Brown

    Name: Walter
    Gender: Male
    Height: 166
    Has glasses? Yes
    Long hair: 34
    Has piercings? Yes
    Has tattoos? Yes
    Hair color: Red

    Name: Tiana
    Gender: Female
    Height: 155
    Has glasses? No
    Long hair: 68
    Has piercings? No
    Has tattoos? No
    Hair color: Red

    Name: Rebecca
    Gender: Female
    Height: 168
    Has glasses? No
    Long hair: 24
    Has piercings? No
    Has tattoos? No
    Hair color: Brown

    Name: Alen
    Gender: Male
    Height: 179
    Has glasses? Yes
    Long hair: 27
    Has piercings? No
    Has tattoos? No
    Hair color: Black

    Pick a person in your head! Computer is going to guess them.
    Have you pick a person? 
```
This question gives the user time to look at the people and pick one. Let us suppose the user wants to pick 'Alen' as their person, they enter 'Yes'
```
    User: I have selected a person.
    Computer asks: Is your person a male?
    Your answer: 
```
The user should enter 'Yes', the computer will eliminate all female persons
```
    Computer asks: Is your person taller than 170?
    Your answer: 
```
The answer should be also 'Yes', the computer will also eliminate the one male person who is not taller than 170
```
    Computer asks: Does your person have glasses?
    You answer: Yes
```
Once again the answer should be 'Yes', the computer will guess the only person left
```
    Your person is Alen.
```