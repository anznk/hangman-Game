import java.util.Scanner;

public class GameController {

    public void print() {
        Question question = new Question();
        Scanner in = new Scanner(System.in);
        String input;
        char inputChar = 0;

        // print message
        System.out.println("Here’s the question. ");
        // print underscores
        System.out.println(question.replaceUnderscores());

        // for test
        System.out.println("Correct Answer(For test): " + question.getCityName());

        // loop until count 10
        while (question.getCounts() < Properties.COUNTLIMIT){
            System.out.print("Guess a letter: ");
            // user input
            input = in.nextLine();
            // check length
            if (input.length() > 1 || input.isEmpty()) {
                continue;
            }
            // check if input is contained char of the city name
            if (!question.checkChar(input)){
                // if doesn't have the char
                question.printMsg(inputChar);
                continue;
            } else {
                inputChar = input.charAt(0);
                question.printMsg(inputChar);
            }
            // check if exist underscores
            if (question.fillLetters(inputChar).indexOf(Properties.UNDERSCORE) == -1){
                System.out.printf("You win! \nYou have guessed '%s' correctly!", question.getCityName());
                break;
            }
        }

        // check if input wrong letter 10 times
        if (question.getCounts() >= Properties.COUNTLIMIT){
            // over limit
            System.out.printf("You lose! \nThe correct word was '%s'!", question.getCityName());
        }

    }
}
