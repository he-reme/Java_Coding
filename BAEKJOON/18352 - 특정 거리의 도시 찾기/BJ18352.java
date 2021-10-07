import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class BJ18352 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		List<Integer> answer = new LinkedList<>();
		
		String[] info = br.readLine().split(" ");
		int N = Integer.parseInt(info[0]); // ������ ����
		int M = Integer.parseInt(info[1]); // ������ ����
		int K = Integer.parseInt(info[2]); // �Ÿ� ����
		int X = Integer.parseInt(info[3]); // ��� ���� ��ȣ
		
		List<List<Integer>> edges = new ArrayList<>(); // ���� ��Ȳ
		for(int n=0; n<=N; n++)
			edges.add(new ArrayList<>());
		
		int start, end;
		for(int e=0; e<M; e++) {
			info = br.readLine().split(" ");
			start = Integer.parseInt(info[0]);
			end = Integer.parseInt(info[1]);
			edges.get(start).add(end);
		}
		
		// bfs�� �̿��� Ž��
		int[] visited = new int[N+1]; // �� ��°�� �湮�ߴ��� üũ
		Queue<Integer> queue = new LinkedList<>();
		queue.offer(X);
		visited[X] = 1;
		int now;
		while(!queue.isEmpty()) {
			now = queue.poll();
				
			for(int city : edges.get(now)) {
				
				// �̹� �湮�� ������ ���
				if(visited[city]!=0)
					continue;
				
				visited[city] = visited[now]+1;
				
				// �������� �־��� �ִ� �Ÿ��� �������� ���
				if(visited[city]==K+1)
					answer.add(city);
				// ���� �������� ������ ���
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
