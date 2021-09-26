import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class BJ17144 {

	public static int R, C; // 방의 크기
	public static int T; // 시간
	public static int[][] room; // 방 상태
	public static Pair[] air_cleaner;
	public static Queue<Pair> dust = new LinkedList<>();
	
	// 위, 우, 아래, 좌
	public static int[][] direction = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// 방 정보 입력
		String[] info = br.readLine().split(" ");
		R = Integer.parseInt(info[0]);
		C = Integer.parseInt(info[1]);
		T = Integer.parseInt(info[2]);
	
		// 방 상태, 공기청정기 입력
		room = new int[R][C];
		air_cleaner = new Pair[2];
		int ac = 0; // 공기청정기 번호
		for(int r=0; r<R; r++) {
			info = br.readLine().split(" ");
			for(int c=0; c<C; c++) {
				room[r][c] = Integer.parseInt(info[c]);
				
				// 공기청정기
				if(room[r][c]==-1)
					air_cleaner[ac++] = new Pair(r, c);
				
				// 미세먼지
				else if(room[r][c]>0)
					dust.offer(new Pair(r, c));
			}
		}
		
		
		/* 시간 T 만큼 미세먼지 확산, 공기청정기 작동하기 */
		int time = 0; // 현재까지 진행된 시간

		while(time<T) {

			// step1. 미세먼지 확산
			spreadDust();
			
			// step2. 공기청정기 작동
			operateAirCleaner();
			
			// step3. 시간의 경과
			time++;
		}
		
		int answer = 0;
		for(int r=0; r<R; r++)
			for(int c=0; c<C; c++)
				if(room[r][c]>0)
					answer += room[r][c];
		
		System.out.println(answer);
		br.close();
	}
	
	/* 미세먼지 확산 */
	private static void spreadDust() {
		
		int[][] tmp = new int[R][C]; // 임시 방 정보
		
		for(int r=0; r<R; r++) {
			for(int c=0; c<C; c++) {
				
				// 해당 칸에 미세먼지가 없을 경우
				if(room[r][c]<=0)
					continue;
				
				Pair now = new Pair(r, c); // 확산될 미세먼지 좌표
				int cnt_direction = 0; // 확산될 공간 갯수
				
				// 미세먼지 네 방향으로 확산시키기
				for(int d=0; d<4; d++) {
					Pair next = new Pair(now.x+direction[d][0], now.y+direction[d][1]);
					
					// 방 범위를 벗어나거나 공기청정기가 있을 경우
					if(next.x<0 || next.x>=R || next.y<0 || next.y>=C || room[next.x][next.y]==-1)
						continue;
					
					tmp[next.x][next.y] += room[now.x][now.y]/5;
					cnt_direction++;
				}
				
				tmp[now.x][now.y] += room[now.x][now.y] - (room[now.x][now.y]/5 * cnt_direction);
			}
		}
		
		// 최종 방 상태를 원본에 저장
		copy(tmp);
	}
	
	/* 공기청정기 작동 */
	private static void operateAirCleaner() {
		
		// [위쪽] 반시계방향으로 순환
		Pair now = new Pair(air_cleaner[0].x, air_cleaner[0].y); // 미세먼지 현재 위치
		Pair next = new Pair(air_cleaner[0].x, air_cleaner[0].y); // 미세먼지 다음 위치 

		for(int d=0; d<4; d++) {
			now.x = next.x;
			now.y = next.y;
			while(true) {
				now.x += direction[d][0];
				now.y += direction[d][1];
				
				// 공기청정기 순환 경로를 벗어나거나 해당 위치에 공기청정기가 있는 경우
				if(now.x<0 || now.x>air_cleaner[0].x || now.y<0 || now.y>=C || room[now.x][now.y]==-1)
					break;
				
				// 미세먼지가 다음 이동할 위치에 공기청정기가 없다면
				if(room[next.x][next.y]!=-1)
					room[next.x][next.y] = room[now.x][now.y];
					
				room[now.x][now.y] = 0;
				
				next.x = now.x;
				next.y = now.y;
			}
		}

		// [아래쪽] 시계방향으로 순환
		now = new Pair(air_cleaner[1].x, air_cleaner[1].y); // 이동할 dust의 위치
		next = new Pair(air_cleaner[1].x, air_cleaner[1].y); // dust의 다음 위치 
		
		for(int d=0; d<4; d++) {
			now.x = next.x;
			now.y = next.y;
			while(true) {
				
				if(d%2==0) {
					now.x += direction[d][0] * (-1);
					now.y += direction[d][1] * (-1);
				}
				else {
					now.x += direction[d][0];
					now.y += direction[d][1];
				}
				
				
				// 공기청정기 순환 경로를 벗어나거나 해당 위치에 공기청정기가 있는 경우
				if(now.x<air_cleaner[1].x || now.x>=R || now.y<0 || now.y>=C || room[now.x][now.y]==-1)
					break;
				
				// 미세먼지가 다음 이동할 위치에 공기청정기가 없다면
				if(room[next.x][next.y]!=-1)
					room[next.x][next.y] = room[now.x][now.y];
				
				room[now.x][now.y] = 0;
				
				next.x = now.x;
				next.y = now.y;
			}
		}

	}
	
	// 복사본을 원본에 저장
	private static void copy(int[][] tmp) {
		for(int r=0; r<R; r++)
			for(int c=0; c<C; c++)
				room[r][c] = tmp[r][c];

		room[air_cleaner[0].x][air_cleaner[0].y] = -1;
		room[air_cleaner[1].x][air_cleaner[1].y] = -1;
	}
	static class Pair {
		int x, y;

		public Pair(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}
}
