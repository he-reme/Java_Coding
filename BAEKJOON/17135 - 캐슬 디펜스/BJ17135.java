import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

public class BJ17135 {

	public static int answer = 0;
	
	public static int N, M, D; // 행의 수, 열의 수, 궁수의 공격 거리 제한
	
	public static List<Pair> enemy = new LinkedList<>(); // 적 정보
	public static int[] archer = new int[3]; // 궁수 정보
	
	public static List<Pair> tmp = new LinkedList<>(); // 임시 적 정보
	public static List<Pair> next = new LinkedList<>(); // 제거된 후의 적 정보
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] info = br.readLine().split(" ");
		N = Integer.parseInt(info[0]);
		M = Integer.parseInt(info[1]);
		D = Integer.parseInt(info[2]);
		
		for(int n=0; n<N; n++) {
			info = br.readLine().split(" ");
			for(int m=0; m<M; m++) {
				int num = Integer.parseInt(info[m]);
				if(num==1)
					enemy.add(new Pair(n, m));
			}
		}
		
		set_archer(0, 0);
		
		System.out.println(answer);
	}
	
	private static void set_archer(int index, int cnt) {
		if(cnt==3) {
			int kill_num = fight();
			answer = answer > kill_num? answer : kill_num;
			
			return;
		}
		
		for(int i=index; i<M; i++) {
			archer[cnt] = i;
			set_archer(i+1, cnt+1);
		}
		
	}
	
	private static int fight() {
		int kill_num = 0;
		int enemy_num = enemy.size();
		
		tmp.clear();
		for(int i=0; i<enemy.size(); i++)
			tmp.add(enemy.get(i));
		
		
		while(enemy_num>0) {
			
			// 1. 모든 궁수가 공격할 적 찾기
			int[] select_enemy = new int[enemy.size()];
			for(int a=0; a<3; a++) {
				
				int min = Integer.MAX_VALUE;
				int kill = 0;
				
				for(int e=0; e<tmp.size(); e++) {
					
					int dist = Math.abs(N - tmp.get(e).x) 
							+ Math.abs(archer[a] - tmp.get(e).y);

					if(dist>D)
						continue;
					
					// 앞에서 구한 적보다 거리가 더 가까운 적인 경우
					if(min>dist) {
						min = dist;
						kill = e;
					}
					
					// 앞에서 구한 적과 거리가 같은 경우
					else if(min==dist) {
						// 더 왼쪽에 있는 적으로 갱신
						if(tmp.get(e).y < tmp.get(kill).y)
							kill = e;
					}
				}
				
				// 제거할 적이 있으면
				if(min!=Integer.MAX_VALUE)
					select_enemy[kill] = 1;	
			}
			
			// 2. 공격 받은 적은 제거되고, 아닌 적은 내려오기
			next.clear();
			for(int e=0; e<tmp.size(); e++) {
				
				// 공격 받은 적이면
				if(select_enemy[e]==1) {
					kill_num++;
					enemy_num--;
				}
				
				// 공격 받지 않은 적이면
				else {
					int x = tmp.get(e).x;
					int y = tmp.get(e).y;
					
					if(x+1==N) // 성 안으로 이동한 경우
						enemy_num--;
					else // 아직 성 밖인 경우
						next.add(new Pair(x+1, y));
				}
			}
			
			tmp.clear();
			for(int i=0; i<next.size(); i++)
				tmp.add(next.get(i));
		}
		return kill_num;
	}
}

class Pair {
	int x, y;

	public Pair(int x, int y) {
		this.x = x;
		this.y = y;
	}
}