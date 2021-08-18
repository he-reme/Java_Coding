import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SW4012 {

	public static int answer;
	
	public static int N; // 식재료 수
	
	public static int[][] s; // 식재료들의 시너지 정보
	public static int[] isChoose; // 음식 A의 식재료로 선택됐을 때 1, 음식 B의 식재료로 선택됐을 때 0
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		for(int test_case=1; test_case<=T; test_case++) {
			answer = Integer.MAX_VALUE;
			
			// 식재료 수 입력
			N = Integer.parseInt(br.readLine());
			
			// 식재료 시너지 입력
			s = new int[N+1][N+1];
			isChoose = new int[N+1];
			
			for(int i=1; i<=N; i++) {
				String[] info = br.readLine().split(" ");
				for(int j=1; j<=N; j++)
					s[i][j] = Integer.parseInt(info[j-1]);
			}
			
			choose(1, 0);
			
			System.out.printf("#%d %d%n", test_case, answer);
		}
		
		br.close();

	}
	
	private static void choose(int start, int cnt) {
		
		// 음식 A의 식재료를 모두 다 골랐을 때 (N/2)
		if(cnt==N/2) {
			
			int A = 0, B = 0;
			
			for(int i=1; i<=N; i++) {
				for(int j=1; j<=N; j++) {
					if(isChoose[i]==1 && isChoose[j]==1)
						A += s[i][j];
					else if(isChoose[i]==0 && isChoose[j]==0)
						B += s[i][j];
				}
			}
			
			answer = answer < Math.abs(A-B) ? answer : Math.abs(A-B);
			return;
		}
		
		// 음식 A의 식재료 뽑기
		for(int i=start; i<=N; i++) {
			isChoose[i] = 1;
			choose(i+1, cnt+1);
			isChoose[i] = 0;
		}
		return;
	}

}
