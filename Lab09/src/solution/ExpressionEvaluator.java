package solution;

import java.util.Scanner;
import java.util.Stack;
import java.util.regex.Pattern;

/**
 * 
 * @author ???
 * @version ???
 * 
 */
public class ExpressionEvaluator
{

    public static final Pattern UNSIGNED_DOUBLE =
        Pattern.compile("((\\d+\\.?\\d*)|(\\.\\d+))([Ee][-+]?\\d+)?.*?");
    public static final Pattern CHARACTER = Pattern.compile("\\S.*?");

    /**
     * Takes an infix expression and converts it to postfix.
     * 
     * @param expression
     *            The infix expression.
     * @return the postfix expression.
     */
    public boolean postHelp(char x, char y)
    {
        if (x == '*' || x == '/')
        {
            if (y == '+' || y == '-')
            {
                return false;
            }
        }

        return true;

    }

    public String toPostfix(String expression)
    {
        // ... other local variables
        Scanner input = new Scanner(expression);
        String next;
        char symbol;
        char stuff[] = expression.toCharArray();
        Stack<Character> ch = new Stack<Character>();

        String postfixExpression = "";

        while (input.hasNext())
        {
            if (input.hasNext(UNSIGNED_DOUBLE))
            {
                next = input.findInLine(UNSIGNED_DOUBLE);
                // TODO: do what you want to with a String that
                // holds a number
                postfixExpression += next + " ";

            }
            else
            {
                next = input.findInLine(CHARACTER);
                symbol = next.charAt(0);
                if (symbol == '(')
                {
                    ch.push(symbol);
                }
                else if (symbol == '+' || symbol == '-' || symbol == '*'
                    || symbol == '/')
                {
                    while (!ch.empty() && ch.peek() != '('
                        && postHelp(symbol, ch.peek()))// TODO is equal or
                    // higher
                    // precedance, add it
                    // into conditions to
                    // check for
                    {
                        // ch.pop();
                        // write the popped char to the output
                        postfixExpression += ch.pop() + " ";
                    }
                    ch.push(symbol);
                }
                else if (symbol == ')')
                {
                    while (!ch.empty() && ch.peek() != '(')//add ! to ch.empty(
                    {
                        // TODO pop char out of stack and write it to the ouput
                        postfixExpression += ch.pop() + " ";
                    }
                    // pop the stack and disregard the )
                    ch.pop();

                }

                // TODO: do what you want to with a symbol
                // such as (, ), *, /, +, -
            }

        }
        while (!ch.isEmpty())
        {
            postfixExpression += ch.pop() + " ";

        }

        return postfixExpression;
    }

    /**
     * Evaluates a postfix expression and returns the result.
     * 
     * @param postfixExpression
     *            The postfix expression.
     * @return The result of the expression.
     * @throws Exception
     */
    public double evaluate(String postfixExpression)
    {
        // other local variables you may need
        Scanner input = new Scanner(postfixExpression);
        String next;
        char operator;
        double answer = Double.NaN;

        Stack<Double> ch = new Stack<Double>();

        while (input.hasNext())
        {
            if (input.hasNext(UNSIGNED_DOUBLE))
            {
                next = input.findInLine(UNSIGNED_DOUBLE);
                // TODO: do what you want to with a String that
                // holds a number
                ch.push(Double.parseDouble(next));

            }
            else
            {
                next = input.findInLine(CHARACTER);
                operator = next.charAt(0);

                if (ch.size() < 2)
                {
                    return Double.NaN;
                }

                double x = ch.pop();
                double y = ch.pop();

                switch (operator)
                {
                    case '+':
                        ch.push(x + y);
                        break;
                    case '-':
                        ch.push(y - x);
                        break;
                    case '*':
                        ch.push(x * y);
                        break;
                    case '/':
                        ch.push(y / x);
                        break;
                }

                // TODO: do what you want to with an operator
                // such as *, /, +, -
            }

        }
        answer = ch.pop();
        if (!ch.isEmpty())
        {
            // throw new Exception("doesnt work");
            return Double.NaN;
        }
        return answer;

    }

}
