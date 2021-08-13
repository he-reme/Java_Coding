import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

public class BJ15686 {

	public static int answer = Integer.MAX_VALUE;
	
	public static int N, M; // 도시의 크기, 최대 치킨 집 수
	
	public static List<Pos> house = new LinkedList<>(); // 집들의 좌표
	public static List<Pos> chicken = new LinkedList<>(); // 치킨집들의 좌표
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] info = br.readLine().split(" ");
		
		N = Integer.parseInt(info[0]);
		M = Integer.parseInt(info[1]);

		for(int x=0; x<N; x++) {
			
			info = br.readLine().split(" ");
			
			for(int y=0; y<N; y++) {
				
				int num = Integer.parseInt(info[y]);
				
				if(num==1)
					house.add(new Pos(x, y));
				else if(num==2)
					chicken.add(new Pos(x, y));
			}
		}
		choose(0, 0, 0);
		System.out.println(answer);
	}
	
	// 최대 M개의 치킨집 선택
	private static void choose(int index, int cnt, int flag) {
		
		if(cnt==M) {
			
			int dist = distance(flag);
			answer = answer < dist ? answer : dist;

			return;
		}
		
		if(index==chicken.size() || cnt>M)
			return;
		

		// 해당 치킨집을 골랐을 때
		choose(index+1, cnt+1, flag | 1<<index);
		
		// 해당 치킨집을 고르지 않았을 때
		choose(index+1, cnt, flag);
	}
	
	// 치킨 거리 구하기
	private static int distance(int flag) {
		
		int total = 0; // 도시의 치킨 거리
		int min; // 집 마다의 치킨 거리
		for(int h=0; h<house.size(); h++) {
			
			min = Integer.MAX_VALUE;
			
			for(int c=0; c<chicken.size(); c++) {
				
				if((flag & 1<<c)==0)
					continue;
				
				int dist = Math.abs(house.get(h).x - chicken.get(c).x) 
						+ Math.abs(house.get(h).y - chicken.get(c).y);
				
				min = min < dist? min : dist;
			}
			total = total + min;
		}
		return total;
	}
}

class Pos {
	int x, y;

	public Pos(int x, int y) {
		this.x = x;
		this.y = y;
	}
}
