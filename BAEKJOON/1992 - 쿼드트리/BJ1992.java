import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ1992 {

	public static int N;
	public static int[][] map;
	public static StringBuilder answer = new StringBuilder();
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		
		for(int i=0; i<N; i++) {
			String[] line = br.readLine().split("");
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(line[j]);
			}
		}

		press(0, 0, N);
		
		System.out.println(answer.toString());
		
	}

	private static void press(int x, int y, int n) {
		
		int flag = map[x][y]; // 해당 범위의 좌표의 숫자가 모두 같은지 확인하는데 사용
		int same = 1; // 해당 범위의 좌표의 숫자들이 모두 같으면 1, 아니면 0
		
		for(int i=x; i<x+n; i++) {
			for(int j=y; j<y+n; j++) {
				if(flag!=map[i][j]) {
					same = 0;
					break;
				}	
			}
			if(same==0)
				break;
		}
		
		// 해당 범위의 좌표의 숫자들이 모두 같은 경우
		if(same==1) {
			answer.append(flag);
		}
		// 해당 범위의 좌표의 숫자들이 모두 다른 경우
		else {
			answer.append("(");
			press(x, y, n/2); // 1사분면
			press(x, y+n/2, n/2); // 2사분면
			press(x+n/2, y, n/2); // 3사분면
			press(x+n/2, y+n/2, n/2); // 4사분면
			answer.append(")");
		}
	}
}

