import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class BJ7576 {
	
	public static int[][] direction = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int answer = 0; // 모든 토마토가 익는 데 걸리는 시간
		
		// 상자 정보 입력
		String[] info = br.readLine().split(" ");
		int M = Integer.parseInt(info[0]); // 상자의 가로
		int N = Integer.parseInt(info[1]); // 상자의 세로
		
		// 토마토 상태 입력
		int[][] tomato = new int[N][M]; // 토마토 상태
		int new_tomato = 0; // 익지 않은 토마토 갯수
		Queue<Pair> queue = new LinkedList<>(); // bfs를 이용하여 토마토 익히기
		
		for(int r=0; r<N; r++) {
			info = br.readLine().split(" ");
			for(int c=0; c<M; c++) {
				tomato[r][c] = Integer.parseInt(info[c]);
				
				// 익은 토마토
				if(tomato[r][c]==1)
					queue.offer(new Pair(r, c));
				
				// 익지 않은 토마토
				if(tomato[r][c]==0)
					new_tomato++;
			}
		}
		
		// 이미 모든 토마토가 익어있는 경우
		if(new_tomato==0) {
			System.out.println(0);
			br.close();
			return;
		}
		
		// 익은 토마토를 기준으로 모든 토마토 익히기
		while(!queue.isEmpty() || new_tomato>0) {
			Pair now = queue.poll();
			
			for(int d=0; d<4; d++) {
				Pair next = new Pair(now.x+direction[d][0], now.y+direction[d][1]);
				
				// 상자 범위를 넘어선 경우
				if(next.x<0 || next.x>=N || next.y<0 || next.y>=M)
					continue;
				
				// 안익은 토마토가 아닌 경우
				if(tomato[next.x][next.y]!=0)
					continue;
				
				tomato[next.x][next.y] = tomato[now.x][now.y] + 1;
				answer = tomato[next.x][next.y];
				queue.offer(next);
				new_tomato--;
			}
		}
		
		if(new_tomato > 0)
			answer = 0;

		System.out.println(answer-1);
		br.close();
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

