import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class BJ1600 {

	public static int[][] direction = {{-2, -1}, {-1, -2}, {-2, 1}, {-1, 2}, 
									{1, -2}, {2, -1}, {2, 1}, {1, 2},
									{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
	
	public static int K;
	public static int W, H;
	public static int[][] map;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		K = Integer.parseInt(br.readLine());
		
		String[] info = br.readLine().split(" ");
		W = Integer.parseInt(info[0]);
		H = Integer.parseInt(info[1]);
		map = new int[H][W];
		
		for(int r=0; r<H; r++) {
			info = br.readLine().split(" ");
			for (int c=0; c<W; c++) {
				map[r][c] = Integer.parseInt(info[c]);
			}
		}
		
		int[][] visited = new int[H][W];
		Queue<Monkey> queue = new LinkedList<>();
		queue.offer(new Monkey(0, 0, K));
		visited[0][0] = 1;
		
		Monkey now, next;
		while(!queue.isEmpty()) {
			now = queue.poll();
			
			// 목표 좌표에 도달했을 때
			if(visited[H-1][W-1]!=0)
				break;
			
			System.out.println(now.x + " " + now.y);
			for(int d=0; d<12; d++) {
				next = new Monkey(now.x+direction[d][0], now.y+direction[d][1], now.k);
				
				
				// 말처럼 움직일 수 있는 횟수를 다 쓴 경우
				if(d<8 && next.k==0) {
					d = 8;
					continue;
				}
				
				// 범위를 벗어난 경우
				if(next.x<0 || next.x>=H || next.y<0 || next.y>=W)
					continue;
			
				// 장애물이 있는 경우
				if(map[next.x][next.y]==1)
					continue;
				
			
				if(visited[next.x][next.y]!=0) {
					if(next.x < now.x || next.y < now.y)
						continue;
					
					if(visited[next.x][next.y]<visited[now.x][now.y]+1)
						continue;
				}
						
				visited[next.x][next.y] = visited[now.x][now.y] + 1;
				
				if(d<8)
					queue.offer(new Monkey(next.x, next.y, next.k-1));
				else
					queue.offer(next);
				
				for(int r=0; r<H; r++) {
					for (int c=0; c<W; c++) {
						System.out.print(visited[r][c] + " ");
					}
					System.out.println();
				}
				System.out.println();
				
				// 목표 좌표에 도달했을 때
				if(visited[H-1][W-1]!=0)
					break;
			}
		}
		
		
		System.out.println(visited[H-1][W-1]-1);
	}
}

class Monkey {
	int x, y;
	int k;

	public Monkey(int x, int y, int k) {
		super();
		this.x = x;
		this.y = y;
		this.k = k;
	}
}
