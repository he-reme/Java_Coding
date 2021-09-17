import java.util.Scanner;

public class BJ1463 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt(); // 정수 N

		// dp[N] : 정수 N을 숫자 1로 만드는 최소 연산 횟수
		int[] dp = new int[N+1]; 
		dp[1] = 0;
		
		for(int n=2; n<=N; n++) {
			
			// 1로 뺴는 경우로 초기화
			dp[n] = dp[n-1] + 1;
			
			// 3으로 나눠지는 경우
			if(n%3==0)
				dp[n] = Math.min(dp[n], dp[n/3]+1);
			
			// 2로 나눠지는 경우
			if(n%2==0)
				dp[n] = Math.min(dp[n], dp[n/2]+1);

		}

		System.out.println(dp[N]);
	}
}
