import java.util.InputMismatchException;
import java.util.Scanner;

public class Calculator extends CalculatorFunctions{

    public static void main (String [] args){
        int i = 0;
        boolean run = true;
        Scanner input = new Scanner(System.in);
        int menuSelection = menuStart();

        while (i == 0) {

            if (mainline.size() > 0) {
                mainline.subList(0, mainline.size()).clear();
            }

            readUserInput();
            numberCollection();

            if (mainline.size() == 0){
                System.out.print("Would you like to close the program? (Type yes or no)");
                String ans = input.nextLine();

                if (ans.equalsIgnoreCase("yes"))
                    i = -1;
                else if (!ans.equalsIgnoreCase("no"))
                    System.out.println("Invalid answer. Please type either yes or no.");
            }

            if (i != -1)
                finalCalculate();
        }
    }

    public static int menuStart(){
        Scanner input = new Scanner(System.in);
        int user = 0;
        int loop = 0;

        //opening screen with menu to select other options
        while (loop == 0) {
            System.out.println("Calculator Software (BETA)");
            System.out.println("Type the corresponding number to select the option.");
            System.out.println("1. Basic Calculations\n2. Geometry");
            try {
                user = input.nextInt();
            } catch (InputMismatchException e) {
                System.err.println("Invalid input. Please type a number that corresponds to a menu option.");
                input.nextLine();
            }

            if (user == 1 || user == 2)
                loop++;
        }
        return user;
    }

    public static void basicCalculator(){

    }


}
