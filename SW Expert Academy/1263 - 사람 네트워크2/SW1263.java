import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SW1263 {

	public static int INF = 10001; // 최대 1000명의 사람이 주어지므로 무한대 값을 10001로 지정
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int answer;
		
		int T = Integer.parseInt(br.readLine());
		
		int N; // 사람 수
		int[][] D; // 정점 i에서 j까지의 최소 거리
		
		String[] info;
		for(int test_case=1; test_case<=T; test_case++) {
			
			answer = INF;
			
			info = br.readLine().split(" ");
			N = Integer.parseInt(info[0]);
			D = new int[N][N];
			
			// step1. 배열에 값 입력
			for(int r=0; r<N; r++) {
				for(int c=0; c<N; c++) {
					
					// r과 c가 직접 연결 됐을 때(1), r과 c가 직접 연결 되지 않았을 때(0)
					int connection = Integer.parseInt(info[r*N + c+1]);
					
					// 본인 - 본인인 경로인 경우
					if(r==c)
						D[r][c] = 0;
					
					else {
						// r과 c가 직접 연결이 아닌 경우
						if(connection==0)
							D[r][c] = INF;
						// r과 c가 직접 연결인 경우
						else
							D[r][c] = connection;
						
						D[c][r] = D[r][c];
					}
						 
				}
			}
			
			// step2. 플로이드-와샬 알고리즘
			for(int k=0; k<N; k++) {
				for(int i=0; i<N; i++) {
					if(k==i) continue;
					
					for(int j=0; j<N; j++) {
						if(k==j || i==j) continue;

						D[i][j] = Math.min(D[i][k] + D[k][j], D[i][j]);
					}
				}
			}
			
			// step3. 사람들의 CC 값들 중에서 최솟값  찾기
			for(int r=0; r<N; r++) {
				int tmp = 0;
				for(int c=0; c<N; c++) {
					if(D[r][c]==INF)
						continue;
					
					tmp += D[r][c];
				}
				answer = answer < tmp ? answer : tmp;
			}

			System.out.printf("#%d %d%n", test_case, answer);
			
		}
	}
}
