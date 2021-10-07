import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class BJ18352 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		List<Integer> answer = new LinkedList<>();
		
		String[] info = br.readLine().split(" ");
		int N = Integer.parseInt(info[0]); // 도시의 개수
		int M = Integer.parseInt(info[1]); // 도로의 개수
		int K = Integer.parseInt(info[2]); // 거리 정보
		int X = Integer.parseInt(info[3]); // 출발 도시 번호
		
		List<List<Integer>> edges = new ArrayList<>(); // 도로 상황
		for(int n=0; n<=N; n++)
			edges.add(new ArrayList<>());
		
		int start, end;
		for(int e=0; e<M; e++) {
			info = br.readLine().split(" ");
			start = Integer.parseInt(info[0]);
			end = Integer.parseInt(info[1]);
			edges.get(start).add(end);
		}
		
		// bfs를 이용해 탐색
		int[] visited = new int[N+1]; // 몇 번째로 방문했는지 체크
		Queue<Integer> queue = new LinkedList<>();
		queue.offer(X);
		visited[X] = 1;
		int now;
		while(!queue.isEmpty()) {
			now = queue.poll();
				
			for(int city : edges.get(now)) {
				
				// 이미 방문한 도시인 경우
				if(visited[city]!=0)
					continue;
				
				visited[city] = visited[now]+1;
				
				// 문제에서 주어진 최단 거리를 만족했을 경우
				if(visited[city]==K+1)
					answer.add(city);
				// 아직 도달하지 못했을 경우
				else
					queue.offer(city);
			}
		}
		
		if(answer.size()==0)
			sb.append(-1);
		else {
			Collections.sort(answer);
			for(int i=0; i<answer.size(); i++)
				sb.append(answer.get(i)).append("\n");
		}
		
		System.out.println(sb);
	}

}
