import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SW1233 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		for(int test_case=1; test_case<=10; test_case++) {
			int answer = 1;
			
			int cnt = Integer.parseInt(br.readLine());
			
			// 이진트리는 짝수가 돼서는 안된다
			if(cnt%2==0)
				answer = 0;
			
			for(int n=1; n<=cnt; n++) {
				
				String[] node = br.readLine().split(" ");
				if(answer==0)
					continue;;
				
				// [노드가 연산자인 경우]
				// 자식노드가 2개 있어야 한다
				if(node[1].equals("+") || node[1].equals("-") || node[1].equals("*") || node[1].equals("/")) {
					if(node.length!=4) answer=0;
				}

				// [노드가 숫자인 경우]
				// 자식 노드가 없어야 한다.
				else {
					if(node.length!=2) answer = 0;
				}
			}
			System.out.printf("#%d %d%n", test_case, answer);
		}

	}

}
