import java.io.*;
import java.util.Stack;

public class SW1218 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		for(int test_case=1; test_case<=10; test_case++) {
			int answer = 1;
			int num = Integer.parseInt(br.readLine());
			
			Stack<Character> stack = new Stack<Character>();
			String line = br.readLine();

			for(int i=0; i<num; i++) {
				char pl = line.charAt(i);
				if(pl=='(' || pl=='{' || pl=='[' || pl=='<') {
					stack.push(pl);
					continue;
				}

				char top = stack.peek();
				if((top=='(' && pl==')') || (top=='{' && pl=='}') || (top=='[' && pl==']') || (top=='<' && pl=='>'))
						stack.pop();
				else {
					answer = 0;
					break;
				}
			}
			
			if(!stack.isEmpty())
				answer = 0;
			System.out.printf("#%d %d%n", test_case, answer);
			
		}

	}

}
