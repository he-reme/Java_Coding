import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BJ16236 {

	public static int answer;
	
	public static int N; // 공간의 크기
	public static int[][] map; // 공간 상태
	
	public static Fish shark; // 상어 위치
	
	public static int ateFish; // 먹은 물고기 수
	public static int lastFish; // 남은 물고기 수
	
	public static int[][] direction = {{-1, 0}, {0, -1}, {0, 1}, {1, 0}};
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// 공간 크기 입력
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		
		// 공간 상태 입력
		lastFish = 0;
		for(int r=0; r<N; r++) {
			String info[] = br.readLine().split(" ");
			
			for(int c=0; c<N; c++) {
				
				map[r][c] = Integer.parseInt(info[c]);
				
				// 상어
				if(map[r][c]==9) {
					shark = new Fish(r, c, 2);
					map[r][c] = 0;
				}
				
				// 물고기
				else if(map[r][c]!=0)
					lastFish++;
				
			}
		}
		
		// 먹을 수 있는 물고기가 있을 때 까지 반복
		while(lastFish>0) {
			Fish fish = searchFish();
			
			// 먹을 수 있는 물고기가 없으면, 엄마 상어 호출
			if(fish==null)
				break;

			/* 먹을 수 있는 물고기가 있을 때 */
			// 1. 상어가 물고기 위치로 감
			map[fish.x][fish.y] = 0;
			shark.x = fish.x;
			shark.y = fish.y;
			
			// 2. 물고기를 잡아 먹기
			map[fish.x][fish.y] = 0;
			ateFish++;
			lastFish--;
			
			// 3. 먹은 물고기 수와 상어 크기가 같으면 크기 커짐
			if(ateFish==shark.size) {
				shark.size++;
				ateFish = 0;
			}
		}
		
		System.out.println(answer);
		

	}
	
	// bfs
	private static Fish searchFish() {
		Queue<Fish> queue = new LinkedList<>();
		int[][] check = new int[N][N]; // 탐색을 한 위치를 표시
		
		// 상어 위치부터 시작
		check[shark.x][shark.y] = 1;
		queue.offer(shark);
		
		// 탐색을 위한 변수들
		Fish now, next; // 현재 좌표 위치, 다음 좌표 위치
		Fish eat = null; // 먹을 예정인 물고기
		int time = Integer.MAX_VALUE; // 먹을 예정인 물고기를 먹기 위해 걸리는 시간
		
		while(!queue.isEmpty() && lastFish>0) {
			
			now = queue.poll();
			
			if(check[now.x][now.y]>time) {
				answer += time;
				return eat;
			}
			
			for(int d=0; d<4; d++) {
				
				next = new Fish(now.x + direction[d][0], now.y + direction[d][1], 0);
				
				// 공간의 범위를 넘어서는 경우
				if(next.x<0 || next.x>=N || next.y<0 || next.y>=N)
					continue;
				
				// 이미 방문한 좌표인 경우
				if(check[next.x][next.y]!=0)
					continue;
			
				// 1. 해당 좌표에 물고기가 있을 경우
				if(map[next.x][next.y]>0) {
					
					// 크기가 상어보다 클 때
					if(map[next.x][next.y] > shark.size)
						continue;
				
					// 크기가 상어보다 작을 때
					if(map[next.x][next.y] < shark.size) {
						
						// 아직 먹을 예정인 물고기가 없을 때
						if(eat==null) {
							eat = next;
							time = check[now.x][now.y];
						}
						// 먹을 예정인 물고기가 있을 때
						else {
							if(eat.x > next.x)
								eat = next;
							else if(eat.x == next.x && eat.y > next.y )
								eat = next;
						}
					}
				}
				
				// 2. 해당 좌표에 물고기가 없거나, 있어도 크기가 같을 때
				queue.offer(next);
				check[next.x][next.y] = check[now.x][now.y] + 1;
			}
		}
		
		return null;
	}
}

class Fish {
	int x, y;
	int size;

	public Fish(int x, int y, int size) {
		super();
		this.x = x;
		this.y = y;
		this.size = size;
	}	
}