import java.util.LinkedList;

public class BalancedSymbols {
    public static String isBalanced(String s) {
        //Linked-list implementation of stack; stores opening symbols that have not been matched
        LinkedList<Character> stack = new LinkedList<>();
        //loop through string one character at time
        for (int i = 0; i < s.length(); i++) {
            //get current symbol from string
            char c = s.charAt(i);
            //if symbol is an opening symbol, push onto stack
            if (c == '(' || c == '{' || c == '<' || c == '¿' || c == '[') {
                stack.push(c);
            } //otherwise symbol must be closing symbol
            else {
                //if stack is empty, there is no opening symbol available to match
                if (stack.isEmpty()) return "NO";
                //look at symbol on top of stack
                char top = stack.peek();

                //define symbol matches
                boolean parenthesisMatch = (top == '(' && c == ')');
                boolean bracketMatch = (top == '[' && c == ']');
                boolean braceMatch = (top == '{' && c == '}');
                boolean angleMatch = (top == '<' && c == '>');
                boolean questionMatch = (top == '¿' && c == '?');

                //check if any pair matches
                if (parenthesisMatch || bracketMatch || braceMatch || angleMatch || questionMatch) {
                    //if they match, remove symbol from stack
                    stack.pop();
                } else {
                    //if they do not match, sequence is not balanced
                    return "NO";
                }
            }
        }

        //after searching through string, stack should be empty if all symbols matched
        if (stack.peek() == null) {
            return "YES";
        }
        //if symbols remain in stack, there were unmatched openings
        return "NO";
    }
}
