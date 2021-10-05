package hwalgo27_서울_8반_김혜림;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class SW1953 {

	public static List<List<Pair>> tunnel_types = new LinkedList<>(); 
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
	
		// 터널 타입 입력
		for(int kind = 0; kind<=7; kind++) 
			tunnel_types.add(new LinkedList<>());
		
		tunnel_types.get(1).add(new Pair(0, 1));
		tunnel_types.get(1).add(new Pair(0, -1));
		tunnel_types.get(1).add(new Pair(1, 0));
		tunnel_types.get(1).add(new Pair(-1, 0));
		
		tunnel_types.get(2).add(new Pair(1, 0));
		tunnel_types.get(2).add(new Pair(-1, 0));
		
		tunnel_types.get(3).add(new Pair(0, 1));
		tunnel_types.get(3).add(new Pair(0, -1));
		
		tunnel_types.get(4).add(new Pair(-1, 0));
		tunnel_types.get(4).add(new Pair(0, 1));
		
		tunnel_types.get(5).add(new Pair(0, 1));
		tunnel_types.get(5).add(new Pair(1, 0));
		
		tunnel_types.get(6).add(new Pair(1, 0));
		tunnel_types.get(6).add(new Pair(0, -1));
		
		tunnel_types.get(7).add(new Pair(0, -1));
		tunnel_types.get(7).add(new Pair(-1, 0));
		
		for(int test_case=1; test_case<=T; test_case++) {
			String[] info = br.readLine().split(" ");
			int N = Integer.parseInt(info[0]); // 터널 지도의 세로 크기
			int M = Integer.parseInt(info[1]); // 터널 지도의 가로 크기
			int R = Integer.parseInt(info[2]); // 맨홀 뚜껑이 위치한 장소의 세로 위치
			int C = Integer.parseInt(info[3]); // 맨홀 뚜껑이 위치한 장소의 가로 위치
			int L = Integer.parseInt(info[4]); // 탈출 후 소요된 시간 
	
			int[][] map = new int[N][M];
			int[][] visited = new int[N][M];
			for(int r=0; r<N; r++) {
				info = br.readLine().split(" ");
				for(int c=0; c<M; c++)
					map[r][c] = Integer.parseInt(info[c]);
			}
	
			/* bfs로 탈주범이 위치할 수 있는 장소 탐색 */
			int answer = 0; // 탈주범이 위치할 수 있는 장소의 개수
			int hour = 1; // 탈주한 지 지난 시간
			
			Queue<Pair> queue = new LinkedList<>();
			queue.offer(new Pair(R, C));
			visited[R][C] = 1;
			answer++;
			
			while(hour<L) {
				int len = queue.size(); // 해당 시간에 갈 수 있는 장소 후보 갯수
				
				while(len>0) {
					Pair now = queue.poll(); // 현재 위치의 파이프
					int type = map[now.x][now.y]; // 현재 위차한 파이프 타입
					len--;
					
					// 파이프 타입에 맞게 주변 탐색
					for(Pair direction : tunnel_types.get(type)) {
						// 다음 파이프 위치
						int x = now.x + direction.x;
						int y = now.y + direction.y;
						
						// 장소 범위 넘어설 경우
						if(x<0 || x>=N || y<0 || y>=M)
							continue;
						// 이미 방문한 곳인 경우
						if(visited[x][y]==1)
							continue;
						// 파이프가 없는 곳인 경우
						if(map[x][y]==0)
							continue;
						
						Pair next = new Pair(now.x + direction.x, now.y + direction.y); // 다음 위치의 파이프
						int next_type = map[next.x][next.y]; // 다음 위치의 파이프 타입
						int flag = 0; // 다음 위치로 이동할 수 있으면 1, 아니면 0
						
						for(Pair d : tunnel_types.get(next_type)) {
							// 현재 파이프와 다음 파이프가 연결되어 있는 경우
							if( (direction.x==d.x*(-1)) && (direction.y==d.y*(-1))) {
								flag = 1;
								break;
							}
						}
						
						if(flag==0)
							continue;
						
						queue.offer(next);
						visited[next.x][next.y] = 1;
						answer++;
					}
				}
				hour++;
			}
				
			System.out.printf("#%d %d%n", test_case, answer);
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