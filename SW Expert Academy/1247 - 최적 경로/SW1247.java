import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

public class SW1247 {

	public static int answer;
	public static int num; // 고객 수
	public static List<Pair> map = new LinkedList<>(); // 0은 회사, 1은 집, 그 외는 고객의 좌표
	public static int[] isVisit; // 방문한 고객집 체크
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		for(int test_case=1; test_case<=T; test_case++) {
			
			// 테스트 케이스를 위한 변수 초기화
			answer = Integer.MAX_VALUE;
			num = Integer.parseInt(br.readLine());
			map.clear();
			isVisit = new int[num+2];
			
			// 회사, 집, 고객들 집 좌표 입력
			String[] info = br.readLine().split(" ");
			for(int i=0; i<num*2+4; i=i+2)
				map.add(new Pair(Integer.parseInt(info[i]), Integer.parseInt(info[i+1])));
			
			// 고객 집 방문
			visitCustomer(0, 0, map.get(0));
			
			System.out.printf("#%d %d%n", test_case, answer);
		}
		
		br.close();
	}
	
	private static void visitCustomer(int cnt, int dist, Pair now) {
		
		// 가지치기
		if(answer < dist)
			return;
		
		// 고객들을 모두 방문했을 때
		if(cnt==num) {
			Pair home = map.get(1);
			dist = dist + Math.abs(now.x-home.x) + Math.abs(now.y-home.y);
			answer = answer < dist ? answer : dist;
			return;
		}
		
		// 고객 집 방문하기 (순열)
		Pair next;
		int next_dist;
		for(int i=2; i<num+2; i++) {
			if(isVisit[i]==1)
				continue;
			
			next = map.get(i);
			next_dist = dist + Math.abs(now.x-next.x) + Math.abs(now.y-next.y);
			
			isVisit[i] = 1;
			visitCustomer(cnt+1, next_dist, next);
			isVisit[i] = 0;
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