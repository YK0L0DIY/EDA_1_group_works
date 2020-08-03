import java.util.Scanner;
import java.util.StringTokenizer;

public class InfixToPost {

    private ArrayStack<String> myStack;
    private String input;
    private String output = "";
    private StringTokenizer myTokens;
    boolean verbose = false;

    public InfixToPost(String input, boolean overbose) {

        this.input = input;
        this.verbose = overbose;

        myTokens = new StringTokenizer(input, " (/*+)-", true);
        myStack = new ArrayStack<>(myTokens.countTokens());

    }

    public InfixToPost(boolean verbose) {
        this.verbose = verbose;
        Scanner scaner = new Scanner(System.in);
        System.out.print("digite uma expressão: ");
        input = scaner.nextLine();

        myTokens = new StringTokenizer(input, " (/*+)-", true);
        myStack = new ArrayStack<>(myTokens.countTokens());
    }

    public String getOutput() {
        return output;
    }

    public void convert() {
        while (myTokens.hasMoreTokens()) {
            String elemnet = myTokens.nextToken();


            if (elemnet.equals(" ")) {
                continue;
            } else if (this.isNumber(elemnet)) {
                output += " " + elemnet;

            } else if (elemnet.equals("(")) {
                if (verbose) {
                    System.out.println("push('(')");
                }
                myStack.push("(");

            } else if (elemnet.equals("*") || elemnet.equals("/")) {

                this.priority2(elemnet);


            } else if (elemnet.equals("+") || elemnet.equals("-")) {

                this.priority1(elemnet);


            } else if (elemnet.equals(")")) {

                while (!myStack.top().equals("(")) {
                    if (verbose) {
                        System.out.println("pop(" + myStack.top() + ")");
                    }
                    output += " " + myStack.pop();
                }
                myStack.pop();


            }

        }
        while (!myStack.empty()) {
            if (verbose) {
                System.out.println("pop(" + myStack.top() + ")");
            }
            output += " " + myStack.pop();
        }

    }

    private boolean isNumber(String str) {
        try {
            int number = Integer.parseInt(str);

        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public void changeVerbose() {
        verbose=!verbose;
    }

    private void priority2(String operator) {

        if (myStack.empty()) {
            if (verbose) {
                System.out.println("push(" + operator + ")");
            }
            myStack.push(operator);
        } else if (myStack.top().equals("+") || myStack.top().equals("-") || myStack.top().equals("(")) {
            if (verbose) {
                System.out.println("push(" + operator + ")");
            }
            myStack.push(operator);
        } else {
            while (!myStack.empty() || myStack.top().equals("+") || myStack.top().equals("-") || myStack.top().equals("(")) {
                if (verbose) {
                    System.out.println("pop(" + myStack.top() + ")");
                }
                output += " " + myStack.pop();
            }
            myStack.push(operator);
        }
        System.out.println(myStack.toString());


    }

    private void priority1(String operator) {
        if (myStack.empty()) {
            if (verbose) {
                System.out.println("push(" + operator + ")");
            }
            myStack.push(operator);
        } else if (myStack.top().equals("(")) {
            if (verbose) {
                System.out.println("push(" + operator + ")");
            }
            myStack.push(operator);
        } else {
            while (myStack.size() == 0 || myStack.top().equals("(")) {

                if (verbose) {
                    System.out.println("pop(" + myStack.top() + ")");
                }

                output += " " + myStack.pop();
            }
            myStack.push(operator);
        }
    }


}
