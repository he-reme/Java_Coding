import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 서로소 문제...
public class SW7465 {

	public static int answer; // 무리 개수
	public static int N, M; // 마을에 사는 사람 수, 서로를 알고 있는 사람의 관계 수
	public static int[] leader; // 사람n이 속한 무리의 리더
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		for(int test_case=1; test_case<=T; test_case++) {
			
			// N, M 입력
			String info[] = br.readLine().split(" ");
			N = Integer.parseInt(info[0]);
			M = Integer.parseInt(info[1]);

			// 무리 초기화
			make();
			 
			int a, b;
			for(int m=0; m<M; m++) {
				info = br.readLine().split(" ");
				a = Integer.parseInt(info[0]);
				b = Integer.parseInt(info[1]);
				union(a, b);
			}
			System.out.printf("#%d %d%n", test_case, answer);
		}

	}
	
	// 무리 초기화 (나 자신 혼자부터 무리 시작)
	private static void make() {
		
		answer = N; // 처음엔 모두가 다른 무리에 들어가 있음
		leader = new int[N+1];
		
		for(int i=1; i<=N; i++)
			leader[i] = i;
	}
	
	// 무리 짓기 
	private static void union(int a, int b) {
		int aLeader = find_leader(a);
		int bLeader = find_leader(b);
		
		// 이미 같은 무리일 경우
		if(aLeader==bLeader)
			return;
		
		// 두 무리가 다른 무리일 경우 : 합치기
		answer--;
		leader[bLeader] = aLeader;
		
	}

	// 무리 대장 찾기
	private static int find_leader(int v) {
		
		// v가 속한 무리의 리더가 v인 경우
		if(leader[v]==v) 
			return v;
		
		// 계속 파고 들면서 리더를 갱신
		return leader[v] = find_leader(leader[v]);
	}
}
