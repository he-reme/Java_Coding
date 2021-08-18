import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

public class SW5644 {

	public static int answer;
	
	public static int M, A; // 총 이동 시간, BC의 개수

	// 사용자 A
	public static int aX, aY; // 위치
	public static List<Integer> aMove = new LinkedList<>(); // 이동 정보
	
	// 사용자 B
	public static int bX, bY; // 위치
	public static List<Integer> bMove = new LinkedList<>(); // 이동 정보

	// BC
	public static List<BC> bc = new LinkedList<>();
	
	public static int[][] direction = {{0,0}, {0,-1}, {1,0}, {0,1}, {-1,0}};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		for(int test_case=1; test_case<=T; test_case++) {
			answer = 0;
			
			aX = 1; aY = 1;
			aMove.clear();
			
			bX = 10; bY = 10;
			bMove.clear();
			
			bc.clear();
			
			String[] info = br.readLine().split(" ");
			M = Integer.parseInt(info[0]);
			A = Integer.parseInt(info[1]);
			
			// 사용자 A의 이동 정보 입력
			info = br.readLine().split(" ");
			for(int i=0; i<M; i++)
				aMove.add(Integer.parseInt(info[i]));
			
			// 사용자 B의 이동 정보 입력
			info = br.readLine().split(" ");
			for(int i=0; i<M; i++)
				bMove.add(Integer.parseInt(info[i]));
		
			// BC 정보 입력
			int x, y, c, p;
			for(int i=0; i<A; i++) {
				info = br.readLine().split(" ");
				x = Integer.parseInt(info[0]);
				y = Integer.parseInt(info[1]);
				c = Integer.parseInt(info[2]);
				p = Integer.parseInt(info[3]);
				bc.add(new BC(x, y, c, p));
			}
			
			move();
			
			System.out.printf("#%d %d%n", test_case, answer);
		}
		
		br.close();

	}
	
	private static void move() {
		int time = 0;
		
		List<Integer> aBC = new LinkedList<>();
		List<Integer> bBC = new LinkedList<>();
		
		while(true) {
			aBC.clear();
			bBC.clear();
			
			int x, y, c; // BC 정보
			
			/* 1. 현재 위치에서 충전 가능 확인 */
			for(int i=0; i<A; i++) {
				x = bc.get(i).x;
				y = bc.get(i).y;
				c = bc.get(i).c;
				
				// 사용자A가 범위 내에 있으면
				if( Math.abs(x-aX) + Math.abs(y-aY) <= c)
					aBC.add(i);
				
				// 사용자B가 범위 내에 있으면
				if( Math.abs(x-bX) + Math.abs(y-bY) <= c)
					bBC.add(i);
			}
			
			/* 2. 충전 가능하면 최대 비교, answer에 더해주기 */
			int maxCharge = 0; // 최대 충전량
			int tmp = 0; // 비교 충전량
			
			if(aBC.size()!=0 && bBC.size()==0) {
				for(int a=0; a<aBC.size(); a++) {
					tmp = bc.get(aBC.get(a)).p;
					maxCharge = maxCharge > tmp ? maxCharge : tmp;
				}
			}
			else if(aBC.size()==0 && bBC.size()!=0) {
				for(int b=0; b<bBC.size(); b++) {
					tmp = bc.get(bBC.get(b)).p;
					maxCharge = maxCharge > tmp ? maxCharge : tmp;
				}
			}
			
			else {
				for(int a=0; a<aBC.size(); a++) {
					for(int b=0; b<bBC.size(); b++) {
					
						// 같은 BC인 경우
						if(aBC.get(a)==bBC.get(b))
							tmp = bc.get(aBC.get(a)).p;
						
						// 다른 BC인 경우
						else
							tmp = bc.get(aBC.get(a)).p + bc.get(bBC.get(b)).p;
					
						maxCharge = maxCharge > tmp ? maxCharge : tmp;
					}
				}
			}
			answer = answer + maxCharge;
			
			if(time>=M)
				break;
			
			/* 3. 사용자 A, B 이동 및 시간 증가 */
			int d;
			
			// 사용자 A 이동
			d = aMove.get(time);
			aX = aX + direction[d][0];
			aY = aY + direction[d][1];
			
			// 사용자B 이동
			d = bMove.get(time);
			bX = bX + direction[d][0];
			bY = bY + direction[d][1];
			
			time++;
		}
	}
}

class BC {
	int x, y; // 위치
	int c, p; // 충전 범위, 처리량
	
	public BC(int x, int y, int c, int p) {
		super();
		this.x = x;
		this.y = y;
		this.c = c;
		this.p = p;
	}
	
}
