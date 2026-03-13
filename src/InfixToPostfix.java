import java.util.LinkedList;

public class InfixToPostfix {
    /**
     * Helper method to determine precedence of operators
     * @param c character from string to evaluate
     */
    public static int precedence(char c) {
        //highest precedence
        if (c == '^') {
            return 3;
        }
        //* and / have same precedence
        else if (c == '*' || c == '/') {
            return 2;
        }
        //+ and - have some precedence
        else if (c == '+' || c == '-') {
            return 1;
        }
        //anything else has no precedence
        return 0;
    }

    public static String infixToPostfix(String input) {
        //stack used to store operators and parenthesis
        LinkedList<Character> stack = new LinkedList<Character>();
        //string that stores the postfix result
        String output = "";
        //loop through string one character at a time
        for (int i = 0; i < input.length(); i++) {
            //get current character from string
            char c = input.charAt(i);

            //boolean definitions
            boolean lowerCase = (c >= 'a' && c <= 'z');
            boolean upperCase = (c >= 'A' && c <= 'Z');
            boolean number = (c >= '0' && c <= '9');
            //push to stack if character is letter or digit
            if (lowerCase || upperCase || number) {
                output += c;
            }
            //if character is opening parenthesis, push onto stack
            else if (c == '(') {
                stack.push(c);
            }
            //if character is closing parenthesis
            else if (c == ')') {
                //pop and add operators until opening parenthesis is found
                while (!stack.isEmpty() && stack.peek() != '(') {
                    output += stack.pop();
                }
                //remove the opening parenthesis from stack
                stack.pop();
            }
            //otherwise character must be an operator
            else {
                //while stack is not empty, top operator is not '(', and top operator has precedence, pop to output
                while (stack.peek() != null && stack.peek() != '(' && precedence(stack.peek()) >= precedence(c)) {
                    output += stack.pop();
                }
                //push current operator onto stack
                stack.push(c);
            }
        }
        //pop any remaining operators from stack
        while (!stack.isEmpty()) {
            output += stack.pop();
        }
        //return completed postfix operation
        return output;
    }
}
