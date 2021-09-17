import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class BJ2636 {

	public static int hour, prev_cheeze;
	public static int cheeze;
	public static int N, M;
	public static int[][] map;
	
	public static int[][] direction = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		String[] info = br.readLine().split(" ");
		N = Integer.parseInt(info[0]);
		M = Integer.parseInt(info[1]);
		map = new int[N][M];
		
		for(int r=0; r<N; r++) {
			info = br.readLine().split(" ");
			for(int c=0; c<M; c++) {
				map[r][c] = Integer.parseInt(info[c]);
				if(map[r][c]==1)
					cheeze++;
			}
		}
		
		// step1. 시간당 치즈 녹이기
		hour = 0;
		while(cheeze>0) {
			meltingCheeze();
			hour++;		
		}
		
		sb.append(hour).append("\n").append(prev_cheeze);
		System.out.println(sb);
	}
	
	private static void meltingCheeze() {
		prev_cheeze = cheeze;
		
		Queue<Pair> queue = new LinkedList<>();
		Queue<Pair> melting_cheeze = new LinkedList<>();
		
		int[][] visited = new int[N][M];
		
		// step2. 녹을 치즈 탐색
		queue.offer(new Pair(0, 0));
		while(!queue.isEmpty()) {
			
			Pair now = queue.poll();
			
			for(int d=0; d<4; d++) {
				
				Pair next = new Pair(now.x+direction[d][0], now.y+direction[d][1]);
				
				if(next.x<0 || next.x>=N || next.y<0 || next.y>=M)
					continue;
				
				if(visited[next.x][next.y]==1)
					continue;
				
				// 해당 좌표가 치즈인 경우
				if(map[next.x][next.y]==1)
					melting_cheeze.offer(next);
				
				// 해당 좌표가 공기인 경우
				else
					queue.offer(next);
				
				visited[next.x][next.y] = 1;
			}
		}
		
		// step3. 치즈 녹이기
		while(!melting_cheeze.isEmpty()) {
			Pair now = melting_cheeze.poll();
			map[now.x][now.y] = 0;
			cheeze--;
		}
	}
}

class Pair {
	int x, y;

	public Pair(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}
}
