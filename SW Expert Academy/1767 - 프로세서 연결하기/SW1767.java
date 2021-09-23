package hwalgo21_서울_8반_김혜림;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

public class SW1767 {

	public static int answer; // 최대한 많은 core에 전원을 연결했을 경우의 전선 길이의 합
	public static int core_cnt; // 최대한 많이 전원에 연결한 core 갯수
	
	public static int N; // cell 크기
	public static List<Pair> core = new LinkedList<>(); // core의 좌표 (이미 전원에 연결된 것은 넣지 않음)
	
	public static int[][] direction = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine().split(" ")[0]);
		
		for(int test_case=1; test_case<=T; test_case++) {
			answer = Integer.MAX_VALUE;
			core_cnt = 0;
			
			N = Integer.parseInt(br.readLine().split(" ")[0]);
			core.clear();
		
			// cell 상태 입력 및 core 좌표 입력
			int[][] cell = new int[N][N];
			for(int r=0; r<N; r++) {
				String[] info = br.readLine().split(" ");
				for(int c=0; c<N; c++) {
					cell[r][c] = Integer.parseInt(info[c]);
					
					// 빈 cell인 경우
					if(cell[r][c]==0)
						continue;
					
					// core가 벽에 붙어있는 경우
					if(r==0 || r==N-1 || c==0 || c==N-1)
						continue;
					
					// core인 경우 좌표를 리스트에 추가
					core.add(new Pair(r,c));
				}

			}
			
			// 전선 놓기
			putWire(0, 0, 0, cell);
			
			System.out.printf("#%d %d%n", test_case, answer);
		}
		
		
		
		
	}
	
	/* step1. 모든 core에 전선 놓기 시도 */
	// n : 현재까지 놓은 전선 수, len : 현재까지 놓은 전선 길이, cnt : 현재까지 연결한 core 갯수
	private static void putWire(int n, int len, int cnt, int[][] cell) {
		
		// 남은 core들을 전원에 연결해도, 현재까지 구한 전원에 연결한 최대 core수보다 적을 경우
		if(cnt+(core.size()-n) < core_cnt)
			return;
		
		// 모든 core을 탐색했을 때
		if(n==core.size()) {
			
			// 1. 답보다 전선을 연결한 core 갯수가 더 큰 것으로 답 갱신
			if(core_cnt < cnt) {
				core_cnt = cnt;
				answer  = len;
			}
			
			// 2. 답보다 전선을 연결한 core 갯수가 같다면, 전선의 길이가 더 짧은 것으로 답 갱신
			else if(core_cnt==cnt)
				answer = answer < len ? answer : len;
			
			return;
		}
		
		/* step2. 전선 놓기 */
		int[][] tmp = new int[N][N]; // 다음 변수로 넘겨줄 cell 상태
		Pair now = core.get(n); // 전선을 놓을 core의 위치
		
		// 4가지 방향으로 전선 놓기
		for(int d=0; d<4; d++) {
			copy(cell, tmp);
			
			int flag = 1; // 전원에 연결시킬 수 있으면 1, 아니면 0
			int tmp_len = 0; // 전원에 연결한 전선의 길이
			Pair next = new Pair(now.x, now.y); // 새로 전선을 놓을 위치 
			while(true) {
				
				// 다음 위치 선정
				next.x = next.x + direction[d][0];
				next.y = next.y + direction[d][1];
				
				// 다음 위치에 core가 있는 경우
				if(tmp[next.x][next.y]==1) {
					copy(cell, tmp);
					flag = 0;
					tmp_len = 0;
					break;
				}
				
				// 해당 위치에 전선 놓기
				tmp[next.x][next.y] = 1; 
				tmp_len++;
				
				// 다음 위치에 전선을 놓을 수 있는 경우
				if(next.x==0 || next.x==N-1 || next.y==0 || next.y==N-1)
					break;
			}
			
			putWire(n+1, len + tmp_len, cnt+flag, tmp);
		}
	}
	
	private static void copy(int[][] cell, int[][] tmp) {
		for(int r=0; r<N; r++)
			for(int c=0; c<N; c++)
				tmp[r][c] = cell[r][c];
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