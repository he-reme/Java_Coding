import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class SW1238 {

	public static int answer;
	public static int last_num; // 현재까지 맨 마지막으로 연락받은 순서
	
	public static int[][] ec; // 비상 연락망
	public static int[] isContact; // 몇번째로 연락받았는지 체크

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N, start; // 입력받는 데이터의 길이, 시작점
		int from, to;
		for(int test_case=1; test_case<=1; test_case++) {
			
			// 비상 연락망, 관련 배열 초기화
			ec = new int[101][101];
			isContact = new int[101];
			
			// 입력받는 데이터의 길이, 시작점 입력
			String info[] = br.readLine().split(" ");
			N = Integer.parseInt(info[0]);
			start = Integer.parseInt(info[1]);
			
			// 비상 연락망 입력
			info = br.readLine().split(" ");
			for(int i=0; i<N; i=i+2) {
				from = Integer.parseInt(info[i]);
				to = Integer.parseInt(info[i+1]);
				ec[from][to] = 1;
			}
			
			answer = start;
			last_num = 0;
			
			ec_contact(start);
			System.out.println(answer);
		}
		

	}

	// 연락 돌리기 (bfs)
	private static void ec_contact(int start) {
		Queue<Integer> queue = new LinkedList<>();
		
		queue.offer(start);
		isContact[start] = 1;
		
		while(!queue.isEmpty()) {
			int now = queue.poll();
			
			for(int i=1; i<=100; i++) {
				
				// 비상연락망이 연결되어있지 않거나, 이미 연락된 경우
				if(ec[now][i]==0 || isContact[i]!=0)
					continue;
				
				queue.offer(i);
				isContact[i] = isContact[now]+1;
				
				if(last_num < isContact[now]) {
					last_num = isContact[now];
					answer = i;
				}
				else if(last_num==isContact[now]) {
					answer = answer > i ? answer : i;
				}
			}
		}
	}
}
