import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class BJ1260 {


	public static int N, M; // 정점의 개수, 간선의 개수
	public static int V; // 탐색을 시작할 정점의 번호
	
	public static int[][] map; // 맵 상태
	public static int[] check; // 방문한 정점 체크
	
	public static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// N, M, V 입력
		String[] info = br.readLine().split(" ");
		N = Integer.parseInt(info[0]);
		M = Integer.parseInt(info[1]);
		V = Integer.parseInt(info[2]);
		
		// 그래프 상태 입력
		map = new int[N+1][N+1];
		int v1, v2;
		for(int m=0; m<M; m++) {
			info = br.readLine().split(" ");
			v1 = Integer.parseInt(info[0]);
			v2 = Integer.parseInt(info[1]);
			map[v1][v2] = map[v2][v1] = 1;
		}
		
		// dfs
		check = new int[N+1];
		dfs(V);
		
		sb.append("\n");
		
		// bfs
		check = new int[N+1];
		bfs();
		
		System.out.println(sb);
	}
	
	private static void dfs(int start) {
		sb.append(start).append(" ");
		check[start] = 1;
		
		for(int v=1; v<=N; v++) {			
			// 인접 노드가 아니거나 이미 방문한 정점인 경우
			if(map[start][v]==0 || check[v]==1)
				continue;

			dfs(v);
		}
	}

	private static void bfs() {
		Queue<Integer> queue = new LinkedList<>();
		queue.offer(V);
		check[V] = 1;
		
		int now; // 인접 탐색할 현재 정점
		while(!queue.isEmpty()) {
			now = queue.poll();
			sb.append(now).append(" ");
			
			for(int v=1; v<=N; v++) {
				
				// 인접 노드가 아니거나 이미 방문한 정점인 경우
				if(map[now][v]==0 || check[v]==1)
					continue;
				
				queue.offer(v);
				check[v] = 1;
			}
		}
		
	}
}
