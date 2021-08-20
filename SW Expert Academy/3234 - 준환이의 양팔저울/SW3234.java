import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

public class SW3234 {

	public static int answer;
	public static int N; // 추 개수
	public static int total;
	
	public static int[] weight; // 추 리스트
	public static int[] isPut; // 놓여진 추 인지 체크
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());
		
		for(int test_case=1; test_case<=T; test_case++) {
			
			answer = 0;
			
			N = Integer.parseInt(br.readLine());
			total = 0;
			isPut = new int[N];
			weight = new int[N];
			
			String[] info = br.readLine().split(" ");
			for(int w=0; w<N; w++) {
				weight[w] = Integer.parseInt(info[w]);
				total = total + weight[w];
			}
			putWeight(0, 0, 0);
			
			System.out.printf("#%d %d%n", test_case, answer);
		}
		br.close();
	}
	
	private static void putWeight(int left, int right, int cnt) {
		
		// 나머지를 다 오른쪽에 넣어도 괜찮은 경우. 수학적 계산 ~
		if(left > total-left+right){
			int n1 = 1;
			for(int i=0; i<N-cnt; i++)
				n1 = n1 * 2;
			
			int n2 = 1;
			for(int i=1; i<=N-cnt; i++)
				n2 = n2 * i;
			
			answer = answer + n1 * n2;
			return;
		}
		
		if(left<right)
			return;
		
		if(cnt==N) {
			answer++;
			return;
		}
		
		for(int w=0; w<N; w++) {
		
			if(isPut[w]==1)
				continue;
			
			isPut[w] = 1;
			
			// 해당 추를 left에 포함
			putWeight(left+weight[w], right, cnt+1);
			
			// 해당 추를 right에 포함
			putWeight(left, right+weight[w], cnt+1);
			
			isPut[w] = 0;
		}
	}
}
