import java.util.Scanner;

public class GameController {

    //　default construction
    public GameController() {

    }

    public void print() {
        Question question = new Question();
        Scanner in = new Scanner(System.in);
        int count = 0;
        String input;
        char inputChar = 0;
        // print message
        System.out.println("Here’s the question. ");
        System.out.println(question.getUnderscores());

        // for test
        System.out.println(question.getCityName());

        // loop until count 10
        while (question.getCounts() < Properties.COUNTLIMIT){
            System.out.print("Guess a letter: ");
            input = in.nextLine();
            // check length
            if (input.length() > 1 || input.isEmpty()) {
                question.printMsg(inputChar);
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
            // cheack if exist underscores
            if (question.fillLetters(inputChar).indexOf(Properties.UNDERSCORE) == -1){
                System.out.printf("You win! \nYou have guessed '%s' correctly!", question.getCityName());
                break;
            }
        }
        if (count >= Properties.COUNTLIMIT){
            // over limit
            System.out.printf("You lose! \nThe correct word was '%s'!", question.getCityName());
        }

    }
}
