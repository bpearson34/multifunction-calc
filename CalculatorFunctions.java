import java.util.ArrayList;
import java.util.Scanner;

public class CalculatorFunctions {

    static ArrayList<Float> mainline = new ArrayList<>();

    static char[] userline;
    static float[] numberform;

    static double ans = 0;


    public static void main (String [] args){

    }


    public static void numberCollection(){
        boolean isnegative = false;
        boolean isdecimal = false;
        boolean didbreak = false;
        int heldposition = 0;
        String tofloat = "";

        float output;

        for (char a : userline) {
            if (a == '*' || a == '/') {
            }
        }

        //parsing loop
        for (int i = 0; i < userline.length; i++) {

            //check for negative symbol
            if (userline[i] == '-') {
                isnegative = true;
            }

            //check if number begins with decimal point
            else if (userline[i] == '.') {
                isdecimal = true;
            }

            else if (userline[i] == '*'){
            }
            //check to see if the input is a number
            else if (userline[i] == '0' || userline[i] == '1' || userline[i] == '2' || userline[i] == '3' || userline[i] == '4' || userline[i] == '5' || userline[i] == '6' || userline[i] == '7' || userline[i] == '8' || userline[i] == '9') {

                heldposition = i;
                //continue until the end of the number (operator symbol) or beginning of decimal, or to the end of the String
                while ((userline[heldposition] != '+' && userline[heldposition] != '-' && userline[heldposition] != '*' && userline[heldposition] != '/') || heldposition == userline.length - 1) {
                    tofloat = tofloat + userline[heldposition];
                    if (heldposition == userline.length - 1) {

                        if (isdecimal){
                            tofloat = "." + tofloat;
                        }

                        output = Float.parseFloat(tofloat);

                        //apply negative value if applicable
                        if (isnegative && output > 0) {
                            output = output * -1;
                            isnegative = false;
                        }

                        mainline.add(output);
                        didbreak = true;
                        break;
                    } else
                        heldposition++;
                }


                //reset i variable to char prior to the operator symbol
                if (heldposition != userline.length - 1)
                    i = heldposition - 1;
                else
                    i = heldposition;

                //convert string to float then place float in mainline for calculation if loop was not broken
                if (!didbreak) {
                    output = Float.parseFloat(tofloat);

                    //apply negative value if applicable
                    if (isnegative && output > 0) {
                        output = output * -1;
                        isnegative = false;
                    }

                    //add numerical representation of the number to mainline ArrayList for calculation (user input is originally in String form)
                    mainline.add(output);
                }
                //clear tofloat variable for next number
                tofloat = "";
            }
        }
    }

    public static void calculate(){

        String num = "";
        float[] main = new float[userline.length];
        float[] newmain = new float[userline.length];
        char currentsymbol;
        char lastsymbol = '|';
        int r = 0;

        //place numbers from mainline into array main
        for (float f : mainline){
            main[r] = f;
            r++;
        }

        //check for addition and subtraction symbols
        for (int i = 0; i < userline.length; i++){
            if (userline[i] == '+' || userline[i] == '-'){

                //if the '-' symbol is referring to a negative number EX. (5+-2 or 5--2 or -2+-5)
                if (lastsymbol == '+' || (userline[i] == '-' && i == 0)){
                    //do nothing
                }

                //if the '-' symbol is referring to subtraction EX. (5-2), then split the equation and handle operations up until that point
                else{
                    for (int j = 0; j < userline.length; j++){
                      //float number = mainline.get(j);
                      String compare = "";

                        for (int k = j; k < userline.length; k++) {
                            char a = userline[k];

                            //go through userline and build the string until it matches a number from mainline
                            if (a != '*' && a != '/') {
                                compare = compare + a;
                            } else {
                                currentsymbol = a;
                            }

                            //once a match is found, find the number after the operator symbol
                            if (compare.equals((String.valueOf(mainline.get(j))) + ".0") || compare.equals(String.valueOf(mainline.get(j))))
                            {
                                compare = "";

                            }


                        }
                    }
                }

                lastsymbol = userline[i];
            }
        }
        //split the equation into chunks based on the position of the addition and subtraction operator symbols
        //add decimals to string with no decimal for user input
        //calculate

        //loop through userline for operator symbols (in order of pemdas), find all of the numbers under similar operations

    }

    public static void finalCalculate(){

        StringBuilder equation = new StringBuilder();
        boolean containsMultiplicationorDivison = false;
        boolean passeddecimal = false;

        StringBuilder first = new StringBuilder();
        StringBuilder second = new StringBuilder();
        boolean finishfirst = false;
        boolean finishsecond = false;
        boolean startconfirmed = false;
        boolean didbreak = false;
        boolean didfinish = false;
        boolean skipnum = false;
        boolean ismultiply = false;
        boolean isdivision = false;
        boolean calcfinished = false;
        boolean equationcomplete = false;
        int startposition = 0;
        int endposition = 0;
        float insert = 0F;

        //set equation to String
        for (char c : userline)
         equation.append(c);

        //check for multiplcation or divison symbols
        for (int b = 0; b < userline.length; b++){

            if (userline[b] == '*' || userline[b] == '/'){
                containsMultiplicationorDivison = true;
                break;
            }

        }


        //if no multiplication or division symbols are found, return answer
        if (!containsMultiplicationorDivison) {
            for (float f : mainline) {
                ans = ans + f;
            }
            System.out.println(equation.toString() + " = " + ans);
            ans = 0;
        }


        //otherwise complete multiplication and division
        else {

            //check if String representation (exact user input) of the equation contains decimals
            int pos = 0;

            //check to see if the current number possesses a decimal
            for (int i = 0; i < equation.length(); i++){
                if (equation.charAt(i) == '.'){
                    passeddecimal = true;
                }

                //if the symbol ahead of the parser is an operator symbol, and the current number does not have a decimal, append the decimal '.0'.
                if (i < equation.length() - 1 && !passeddecimal && (equation.charAt(i+1) == '+' || equation.charAt(i+1) == '*' || equation.charAt(i+1) == '/' || (equation.charAt(i+1) == '-' && equation.charAt(i) != '+'))){
                    equation.insert(i + 1, ".0");
                    passeddecimal = false;

                    //set i to next operator symbol in equation
                    while (i < equation.length() && (equation.charAt(i) != '+' && equation.charAt(i) != '*' && equation.charAt(i) != '/' && equation.charAt(i) != '-'))
                        i++;
                }
                //otherwise, if the loop has reached the end of the equation and the last number does not contain a decimal, append ".0" to the end of the last number
                else if (i == equation.length() - 1 && !passeddecimal){
                    equation.append(".0");
                    passeddecimal = false;

                    //set i to next operator symbol in equation
                    while (i < equation.length() && (equation.charAt(i) != '+' && equation.charAt(i) != '*' && equation.charAt(i) != '/' && equation.charAt(i) != '-'))
                        i++;
                }

            }

            //Check for errors and inform user if any software error is found
            int error = equation.lastIndexOf("+.0-");
            int errortwo = equation.lastIndexOf("+.0+");


            if (error == -1 && errortwo == -1)
                System.out.println("Equation: " + equation.toString());
                //formatted equation
            else
                System.err.println("Software Error: Unable to parse and format properly.");


            //this loop is meant to complete the order of operations
            //multiplication and divison
            //complete multiplication and division and insert the answers back into the equation accordingly
            //(code improvements are neccesary, there are some bugs that prevent this block of code from working 100% accurately)
            for (int j = 0; j < equation.length() && !equationcomplete && !didfinish; j++){

                //check for when to start addition and subtraction
                for (int k = 0; k < equation.length() && !equationcomplete & !didfinish; k++){
                    if (equation.charAt(k) == '*' || equation.charAt(k) == '/')
                        break;

                    //start addition and subtraction
                    else if (k == equation.length() - 1){
                        StringBuilder firstnumber = new StringBuilder();
                        StringBuilder secondnumber = new StringBuilder();
                        float firstnum = 0f;
                        float secondnum = 0f;
                        float finalnum = 0f;
                        int startpos = 0;
                        int endpos = 0;

                        for (int l = 0; l < equation.length(); l++){

                            while (((Character.isDigit(equation.charAt(l)) || (equation.charAt(l) == '.' && !firstnumber.toString().contains("."))) || (firstnumber.length() == 0 && equation.charAt(l) == '-')) && l < equation.length()){
                                firstnumber.append(equation.charAt(l));
                                l++;
                            }

                            startpos = l;

                            do {
                                if ((equation.charAt(l) == '+' && secondnumber.length() != 0)|| (equation.charAt(l) == '-' && secondnumber.length() != 0)) {
                                    endpos = l;
                                    break;
                                }
                                else if (Character.isDigit(equation.charAt(l)) || equation.charAt(l) == '-' || (equation.charAt(l) == '.' && !secondnumber.toString().contains("."))) {
                                        secondnumber.append(equation.charAt(l));
                                }
                                l++;
                            } while (l < equation.length());

                            firstnum = Float.parseFloat(firstnumber.toString());
                            secondnum = Float.parseFloat(secondnumber.toString());

                            finalnum = firstnum + secondnum;

                            firstnumber.delete(0, firstnumber.length());
                            secondnumber.delete(0, secondnumber.length());

                            //check if equation is complete by looking for the addition symbol or the minus symbol if it is referring to the subtraction operation
                            if ((countOperatorSymbol('+', equation) == 1 && countOperatorSymbol('-', equation) == 0) || (countOperatorSymbol('-', equation) == 1 && countOperatorSymbol('+', equation) == 0) ) {
                                equationcomplete = true;
                                break;
                            }
                            else if (endpos > startpos) {
                                adjustEquation(equation, startpos, endpos, finalnum);
                                l = -1;
                            }
                        }
                        System.out.println(finalnum);
                        didfinish = true;
                    }
                }
                skipnum = false;


                if (didbreak && !equationcomplete){

                    didbreak = false;

                    float firstnum = Float.parseFloat(String.valueOf(first));
                    float secondnum = Float.parseFloat(String.valueOf(second));

                    if (ismultiply){
                        insert = firstnum * secondnum;
                        adjustEquation(equation, startposition, endposition, insert);

                        ismultiply = false;
                        first.delete(0, first.length());
                        second.delete(0, second.length());

                        //check if calculations are complete
                        for (int i = 0; i < equation.length() && !calcfinished; i++){


                            if (equation.charAt(i) == '*' || equation.charAt(i) == '/' || equation.charAt(i) == '+'){
                                j = 0;
                                break;
                            }


                            //if there are no more operators in the equation, print the answer, otherwise continue to loop until the end.

                            else if (i == equation.length() - 1){
                                System.out.println(equation);
                                calcfinished = true;
                            }

                        }
                    }
                    else if (isdivision){
                        insert = firstnum / secondnum;
                        adjustEquation(equation, startposition, endposition, insert);

                        //check if calculations are complete
                        for (int i = 0; i < equation.length() && !calcfinished; i++){


                            if (equation.charAt(i) == '*' || equation.charAt(i) == '/' || equation.charAt(i) == '+'){
                                j = 0;
                                break;
                            }


                            //if there are no more operators in the equation, print the answer, otherwise continue to loop until the end.
                            else if (i == equation.length() - 1){
                                System.out.println(equation);
                                calcfinished = true;
                            }

                        }

                        isdivision = false;
                        first.delete(0, first.length() - 1);
                        second.delete(0, second.length() - 1);
                    }
                }


                if (calcfinished)
                    break;

                //track starting position
                if (!startconfirmed){
                    startposition = j;
                    startconfirmed = true;
                }

                //build first number
                if (((equation.charAt(j) != '*' && equation.charAt(j) != '/' && equation.charAt(j) != '+' && (Character.isDigit(equation.charAt(j)) || equation.charAt(j) == '.')) || (equation.charAt(j) == '-' && first.length() == 0)) && !equationcomplete) {
                    first.append(equation.charAt(j));


                    //once an operator symbol is reached (indicates the end of a number), start building the second number (UNFINISHED)
                    if (equation.charAt(j + 1) == '+' || equation.charAt(j + 1) == '*' || equation.charAt(j + 1) == '/' || equation.charAt(j + 1) == '-'){
                        finishfirst = true;

                        //if the upcoming symbol indicates addition, skip the saved number in variable 'first' since this is the multiplication/division loop
                        if (equation.charAt(j + 1) == '+' || equation.charAt(j + 1) == '-'){
                            finishfirst = false;
                            skipnum = true;
                            startconfirmed = false;
                            first.delete(0, first.length());
                        }

                        //if the upcoming symbol indicates multiplication, store that information for calculation
                        else if (equation.charAt(j + 1) == '*' ){
                            ismultiply = true;
                        }

                        //if the upcoming symbol indicates division, store that information for calculation
                        else if (equation.charAt(j + 1) == '/'){
                            isdivision = true;
                        }

                        for (int k = j + 2; k < equation.length() && !skipnum; k++){
                            if (equation.charAt(k) != '*' && equation.charAt(k) != '/' && equation.charAt(k) != '+' && equation.charAt(k) != '-')
                                second.append(equation.charAt(k));

                            //once an operator symbol is reached, save position and break loop
                            if (k == equation.length() - 1 || (equation.charAt(k + 1) == '+' || equation.charAt(k + 1) == '*' || equation.charAt(k + 1) == '/') || (equation.charAt(k + 1) == '-')){
                                finishsecond = true;
                                didbreak = true;
                                endposition = k;
                                startconfirmed = false;
                                break;
                            }
                        }

                    }
                }
            }


            //if the String representation of the equation does not contain decimals, compare auto-formatted equation to projected equation


        }
    }

    private static StringBuilder adjustEquation(StringBuilder equation, int startposition, int endposition, float insert) {
        //check for software error and adjust
        while (equation.charAt(startposition) == '+' || equation.charAt(startposition) == '-' || equation.charAt(startposition) == '*' || equation.charAt(startposition) == '/')
            startposition++;

        //check if this is the last operation to be completed in the equation, which should be addition
        if ((countOperatorSymbol('+', equation) == 1 && countOperatorSymbol('*', equation) == 0) && (countOperatorSymbol('/', equation) == 0)){

            //self correct if endposition marker is on an operator symbol
            if (equation.charAt(endposition) == '-' || equation.charAt(endposition) == '+')
                endposition--;

            equation.delete(0, endposition + 1);
            equation.insert(0, insert);
        }
        //otherwise, insert number where appropriate and adjust
        else {
            if (equation.charAt(endposition) == '+' || equation.charAt(endposition) == '-' || equation.charAt(endposition) == '*' || equation.charAt(endposition) == '/')
                endposition--;

            equation.delete(startposition, endposition);
            equation.insert(startposition, String.valueOf(insert));
        }

        //check for double minus symbols '--' and a pair of adjacent plus and minus symbols '+-' and translates them into their one-character representation '+-' becomes '-' and '--' becomes '+'

        for (int i = 0; i < equation.length() - 1; i++){
            if (equation.charAt(i) == '-' && equation.charAt(i + 1) == '-'){
                equation.delete(i, i + 2);
                equation.insert(i, '-');
            }

            else if (equation.charAt(i) == '+' && equation.charAt(i + 1) == '-'){
                equation.delete(i, i + 2);
                equation.insert(i, '-');
            }
        }
        return equation;
    }

    public static int countOperatorSymbol (char operatorsymbol, StringBuilder equationone){
        int count = 0;
        for (int i = 0; i < equationone.length() && operatorsymbol != '-'; i++){
            if (operatorsymbol == equationone.charAt(i)){
                count++;
            }
        }

        //seperate loop required for counting minus symbols when they refer to subtraction (and not a negative number)
        for (int i = 0; i < equationone.length() - 1 && operatorsymbol == '-'; i++){
            if (equationone.charAt(i) != '+' && equationone.charAt(i + 1) == '-')
                count++;
        }
        return count;
    }

    //takes user input and organizes it into a readable function for the calculator
    public static void readUserInput(){
        Scanner input = new Scanner(System.in);
        System.out.print("Equation: ");
        String userinput = input.nextLine();
        userinput.replaceAll("\\s", "");
        userline = userinput.toCharArray();
    }

    //takes char[] allnumbers and converts each cell into the double data type, then places each double in double[] numberform
    public static void getAllNumbers(){
        for (int num : userline) {
            if (userline[num] == '0' || userline[num] == '1' || userline[num] == '2' || userline[num] == '3' || userline[num] == '4' || userline[num] == '5' || userline[num] == '6' || userline[num] == '7' || userline[num] == '8' || userline[num] == '9') {
                mainline.add((float) userline[num]);
            }
        }
    }
}
