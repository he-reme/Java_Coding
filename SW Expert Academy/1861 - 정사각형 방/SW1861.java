import java.io.BufferedReader;
import java.io.InputStreamReader;


public class SW1861 {

	static int[][] direction = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
	static int N;
	static int[][] A;
	static int[][] check;
	static int ansRoom;
	static int ansCnt;
	static int nowX, nowY;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
	
		for(int test_case=1; test_case<=T; test_case++) {
			ansRoom = 0; // 방번호
			ansCnt = 0; // 최대 이동방 갯수
			N = Integer.parseInt(br.readLine());
			
			// A 입력 받기
			A = new int[N][N];
			for(int x=0; x<N; x++) {
				String[] line = br.readLine().split(" ");
				for(int y=0; y<N; y++) {
					A[x][y] = Integer.parseInt(line[y]);
				}
			}

			check = new int[N][N];
			for(int x=0; x<N; x++) {
				for(int y=0; y<N; y++) {
					
					if(check[x][y]!=0)
						continue;
					
					nowX = x;
					nowY = y;
					search(x, y, 1);
					
					if(ansCnt < check[nowX][nowY]) {
						ansCnt = check[nowX][nowY];
						ansRoom = A[nowX][nowY];
					}
					else if(ansCnt == check[nowX][nowY]) {
						if(ansRoom > A[nowX][nowY])
							ansRoom = A[nowX][nowY];
					}
				}
			}
		
			System.out.printf("#%d %d %d%n", test_case, ansRoom, ansCnt);
		}

		
	}
	
	public static void search(int x, int y, int cnt) {
		
		for(int d=0; d<4; d++) {
			int nx = x + direction[d][0];
			int ny = y + direction[d][1];
			
			if(nx<0 || nx>=N || ny<0 || ny>=N) // 배열 범위를 넘어선 경우
				continue;
			if(A[x][y]+1 != A[nx][ny]) // 숫자가 1개 큰 방이 아닌 경우
				continue;
			
			// 이미 방문을 완료한 방인 경우
			if(check[nx][ny]!=0) {
				check[nowX][nowY] = check[nx][ny] + cnt;
				return;
			}

			search(nx, ny, cnt+1);
			return;
		}
		
		// 더이상 트래킹 할 방이 없으면
		check[nowX][nowY] = cnt;
		return;
	}
}