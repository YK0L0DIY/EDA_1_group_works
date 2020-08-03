import java.util.Scanner;
import java.util.StringTokenizer;

public class EvalPostfix {

    private ArrayStack<Double> nums;
    private String input;
    private String output = "";
    private StringTokenizer stringTokenizer;
    private boolean verbose = false;

    public EvalPostfix() {
        Scanner in = new Scanner(System.in);
        System.out.print("digite uma expressão: ");
        this.input = in.nextLine();
        this.stringTokenizer = new StringTokenizer(input, " /*-+", true);
        nums = new ArrayStack<>(stringTokenizer.countTokens());
    }

    public EvalPostfix(String input) {
        this.input = input;
        this.stringTokenizer = new StringTokenizer(input, " /*-+", true);
        nums = new ArrayStack<>(stringTokenizer.countTokens());

    }

    public void eval() {
        while (stringTokenizer.hasMoreTokens()) {
            String temp = stringTokenizer.nextToken();

            if (isNumber(temp)) {
                nums.push(Double.parseDouble(temp));
                if (verbose)
                    System.out.println("PUSH: " + Double.parseDouble(temp));

            } else if (temp.equalsIgnoreCase(" ")) {
                continue;

            } else if (nums.size() < 2) {
                System.out.println("INVALID EXPRESSION!");
                break;

            } else {
                double top0 = nums.pop();
                if (verbose)
                    System.out.println("POP: " + top0);
                double top1 = nums.pop();
                if (verbose)
                    System.out.println("POP " + top1);

                if (temp.equalsIgnoreCase("+")) {
                    double tempDouble = top0 + top1;
                    nums.push(tempDouble);
                    if (verbose)
                        System.out.println("PUSH: " + tempDouble);
                }
                if (temp.equalsIgnoreCase("-")) {
                    double tempDouble = top1 - top0;
                    nums.push(tempDouble);
                    if (verbose)
                        System.out.println("PUSH: " + tempDouble);
                }
                if (temp.equalsIgnoreCase("*")) {
                    double tempDouble = top0 * top1;
                    nums.push(tempDouble);
                    if (verbose)
                        System.out.println("PUSH: " + tempDouble);
                }
                if (temp.equalsIgnoreCase("/")) {
                    double tempDouble = top1 / top0;
                    nums.push(tempDouble);
                    if (verbose)
                        System.out.println("PUSH: " + tempDouble);
                }
            }
        }
        output = "" + nums.top();
    }

    public String getOutput() {
        return output;
    }

    public void changeVerbose() {
        verbose = !verbose;
    }

    private boolean isNumber(String string) {
        try {
            Double.parseDouble(string);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    public String toString() {
        return input + " = " + nums.toString();
    }
}
