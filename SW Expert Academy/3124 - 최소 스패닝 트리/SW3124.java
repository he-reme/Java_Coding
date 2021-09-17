import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class SW3124 {

	public static int V, E; // 정점의 개수, 간선의 개수
	public static int[] roots;
	public static Edge[] edgeList;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		String[] info;
		for(int test_case=1; test_case<=T; test_case++) {
			info = br.readLine().split(" ");
			V = Integer.parseInt(info[0]);
			E = Integer.parseInt(info[1]);
			edgeList = new Edge[E];
			
			for(int i=0; i<E; i++) {
				info = br.readLine().split(" ");
				
				int a = Integer.parseInt(info[0]);
				int b = Integer.parseInt(info[1]);
				int weight = Integer.parseInt(info[2]);
				
				edgeList[i] = new Edge(a, b, weight);
			}
			
			// step1. 오름차순 정렬
			Arrays.sort(edgeList);
			
			// step2. 모든 정점을 각각의 집합으로 만들고 출발
			make();
			
			// step3. 간선 하나씩 시도하며 트리 만들어 감
			long cnt = 0, answer = 0;
			for(Edge edge : edgeList) {
				if(union(edge.a, edge.b)) {
					answer += edge.weight;
					if(++cnt == V-1) break;
				}
			}
			
			System.out.printf("#%d %d%n", test_case, answer);
			
		}

	}
	
	// 모든 원소 자신을 대표자로 초기화
	private static void make() {
		roots = new int[V+1];
		for(int i=1; i<V+1; i++) {
			roots[i] = i;
		}
	}
	
	// 대표자 노드 찾기
	private static int find(int a) {
		if(a==roots[a]) return a;
		
		return roots[a] = find(roots[a]);
	}
	
	// 두 원소를 하나의 집합으로 합치기
	private static boolean union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		
		// 이미 같은 집합이었으면, 합치지 않음
		if(aRoot == bRoot)
			return false;
		
		// a, b가 포함된 집합 2개 합치기
		roots[bRoot] = aRoot;
		return true;
	}

}

class Edge implements Comparable<Edge> {
	int a, b;
	int weight;
	public Edge(int a, int b, int weight) {
		super();
		this.a = a;
		this.b = b;
		this.weight = weight;
	}
	@Override
	public int compareTo(Edge o) {
		return Integer.compare(this.weight, o.weight);
	}
}
