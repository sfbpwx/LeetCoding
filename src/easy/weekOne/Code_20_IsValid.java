package easy.weekOne;

import java.util.HashMap;
import java.util.Stack;

public class Code_20_IsValid {
	private static final String TEST_STRING = "((){}[]{}[{}()()[]{{}{}({)}}])";
    

    public static void main(String[] args) {
    	Code_20_IsValid a = new Code_20_IsValid();
        boolean result = a.isVaild(TEST_STRING);
        System.out.println("string match is=" + result);
    }

    private boolean isVaild(String s) {
    	HashMap<Character, Character> map = new HashMap<Character, Character>();
    	map.put('[', ']');
        map.put('{', '}');
        map.put('(', ')');
        map.put('<', '>');
        Stack<Character> stack = new Stack<Character>();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            System.out.println("input:---->    "+ch);
            if (map.containsKey(ch)) {
                stack.push(ch);
                System.out.println("result:---->     ��ջ");
            } else if (map.containsValue(ch)) {
                if (!stack.isEmpty() && map.get(stack.peek()) == ch) {
                    stack.pop();
                    System.out.println("result:---->     ��ջ");
                } else {
                	System.out.println("result:---->     δƥ��");
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }
}
