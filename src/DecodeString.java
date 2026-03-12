import java.util.LinkedList;

public class DecodeString {
    public static String decodeString(String s) {
        //stack that stores repeat counts
        LinkedList<Integer> countStack = new LinkedList<Integer>();
        //stack that stores previously built strings
        LinkedList<String> stringStack = new LinkedList<String>();
        //repeat stores the repeat count currently being build
        int repeat = 0;
        //current stores the substring currently being constructed
        String current = "";
        //loop through each character in the input string
        for (int i = 0; i < s.length(); i++) {
            //get current character from the string
            char c = s.charAt(i);
            //if character is a digit, build repeat number
            if (c >= '0' && c <= '9') {
                repeat = repeat * 10 + (c - '0');
            } //if character is an opening bracket
            else if (c == '[') {
                //push repeat number onto count stack
                countStack.push(repeat);
                //push current string onto string stack
                stringStack.push(current);
                //reset number for next repeat count
                repeat = 0;
                //reset current ro start building substring inside brackets
                current = "";
            } //if character is a closing bracket
            else if (c == ']') {
                //pop most recent repeat count
                int count = countStack.pop();
                //pop previously built string
                String previous = stringStack.pop();
                //create new string to hold repeated substring
                String repeated = "";
                //repeat current substring "count" times
                for (int j = 0; j < count; j++) {
                    repeated += current;
                }
                //combine previous string with repeated string
                current = previous + repeated;
            }
            //otherwise character must be letter
            else {
                //add letter to current substring
                current = current + c;
            }
        }
        //after processing input, current holds decoded string
        return current;
    }
}