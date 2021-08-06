import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SW2850 {

	static int[][] map = new int[49][49];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for(int test_case=1; test_case<=T; test_case++) {
			int answer = 0;
			int N = Integer.parseInt(br.readLine());
			
			int index = N/2; // 해당 행에서 농작물 수확 시작 열 부분
			int cnt = 1; // 해당 행에서 수확할 수 있는 농작물 갯수
			
			for(int i=0; i<N; i++) {
				String line = br.readLine();
				
				for(int j=0; j<N; j++) {
					map[i][j] = line.charAt(j)-'0';
					if(i!=N/2) {
						if(j<index)
							continue;
						if(j>=index+cnt)
							break;
					}
					answer += map[i][j];
				}
				// 마름모 윗부분
				if(i<N/2) {
					cnt = cnt + 2;
					index--;
				}
				// 마름모 중간~아랫부분
				else if(i>=N/2) {
					cnt = cnt - 2;
					index++;
				}
			}
			
			System.out.printf("#%d %d%n", test_case, answer);
		}
		

	}

}
