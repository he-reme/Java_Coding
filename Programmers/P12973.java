import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
public class P12973 {

	public static void main(String[] args) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		System.out.println(solution("baabaa"));
	}
	
	public static int solution(String s) {

		Stack<Character> stack = new Stack<>();
		char alpha; // 현재 알파벳
		char top_alpha; // 현재까지 넣은 알파벳 중 가장 맨 뒤에 있는 알파벳
		
		for(int i=0; i<s.length(); i++) {
			alpha = s.charAt(i);
		
			if(stack.isEmpty()) {
				stack.push(alpha);
				continue;
			}
			
			top_alpha = stack.peek();
			if(top_alpha==alpha)
				stack.pop();
			else
				stack.push(alpha);
		}
		
		if(stack.empty())
			return 1;
		else
			return 0;
	}

}
