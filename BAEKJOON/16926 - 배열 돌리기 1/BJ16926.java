import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ16926 {

	static int N, M, R; // 배열 크기(N, M), 회전 수(R)
	static int[][] A;
	static int[][] check;
	static int[][] direction = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		String[] info = br.readLine().split(" ");
		N = Integer.parseInt(info[0]);
		M = Integer.parseInt(info[1]);
		R = Integer.parseInt(info[2]);
		A = new int[N][M];
		
		for(int i=0; i<N; i++) {
			String[] line = br.readLine().split(" ");
			for(int j=0; j<M; j++)
				A[i][j] = Integer.parseInt(line[j]);
		}

		// 회전
		for(int r=0; r<R; r++)
			rotate();

		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++){
				sb.append(A[i][j]);
				sb.append(" ");
			}
			sb.append("\n");
		}
		
		System.out.println(sb.toString());
		
		
	}

	private static void rotate() {
		check = new int[N][M]; // 회전을 한 좌표인지 체크
		
		int startX=0, startY=0; // 회전의 시작점 좌표 : (0,0), (1,1), ...
		
		int x=0, y=0; // 이동할 좌표
		int d = 0; // 이동 방향
		
		int now = A[x][y]; // 현재 이동할 좌표의 숫자
		int next = 0; // 다음 이동할 좌표의 숫자
		
		while(true) {
			
			System.out.println();
			for(int i=0; i<N; i++) {
				for(int j=0; j<M; j++)
					System.out.printf("%d ", A[i][j]);
				System.out.println();
			}
			
			// [ 회전의 시작점 좌표에 도달한 경우 ] 
			if(d==4) {
				x = ++startX;
				y = ++startY;
				d = 0;
				now = A[x][y];
				
				// 다음 회전의 시작점이 이미 회전했다면, 종료
				if(check[x][y]==1)
					break;
			}
			
			// [ 이동 ]
			x = x + direction[d][0];
			y = y + direction[d][1];
			
			// 범위를 벗어나거나 이미 회전한 좌표인 경우
			if(x<0 || x>=N || y<0 || y>=M || check[x][y]==1) {
				x = x - direction[d][0];
				y = y - direction[d][1];
				d = d+1;
				continue;
			}
			
			// 실제 이동
			next = A[x][y];
			A[x][y] = now;
			now = next;
			check[x][y] = 1;
		}
		
	}
}
