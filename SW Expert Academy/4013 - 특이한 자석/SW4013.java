import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
public class SW4013 {

	public static List<List<Integer>> magnet = new LinkedList<>(); // 자석 상태
	public static MagnetIndex[] index = new MagnetIndex[5]; // 빨간색 화살표, 오른쪽, 왼쪽 위치
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());
		for(int test_case=1; test_case<=T; test_case++) {
			int K = Integer.parseInt(br.readLine()); // 회전 횟수
			magnet.clear();
			
			String[] info;		
			magnet.add(new LinkedList<>());
			for(int i=1; i<=4; i++) {
				magnet.add(new LinkedList<>());
				
				info = br.readLine().split(" ");
				for(int j=0; j<8; j++)
					magnet.get(i).add(Integer.parseInt(info[j]));
				
				index[i] = new MagnetIndex(0, 2, 6);
			}
			
			/* 자석 회전 시키기 */
			for(int k=0; k<K; k++) {
				info = br.readLine().split(" ");
				int num = Integer.parseInt(info[0]);
				int direction = Integer.parseInt(info[1]);
				
				
				// step1. 해당 자석을 기준으로 왼쪽에 위치한 자석들 회전
				int now = num;
				for(int next=num-1; next>0; next--) {
					int now_left = magnet.get(now).get(index[now].left);
					int next_right = magnet.get(next).get(index[next].right);
					
					if(now_left==next_right)
						break;

					now = next;
				}
				
				// 왼쪽 자석들 회전 시키기
				int next_direction = direction * (-1);
				for(int next=num-1; next>=now; next--) {
					rotate(next, next_direction);
					next_direction *= (-1);
				}
				
				// step2. 해당 자석을 기준으로 오른쪽에 위치한 자석들 회전
				now = num;
				for(int next=num+1; next<5; next++) {
					int now_right = magnet.get(now).get(index[now].right);
					int next_left = magnet.get(next).get(index[next].left);
	
					if(now_right==next_left)
						break;
					
					now = next;
				}
				
				// 오른쪽 자석들 회전 시키기
				next_direction = direction * (-1);
				for(int next=num+1; next<=now; next++) {
					rotate(next, next_direction);
					next_direction *= (-1);
				}
				
				// step3. 해당 자석 회전
				rotate(num, direction);
			}
			
			
			int answer = 0;
			int score = 1;
			for(int m=1; m<5; m++) {
				if(magnet.get(m).get(index[m].red)==1)
					answer += score;
				score *= 2;
			}
		
			System.out.printf("#%d %d%n", test_case, answer);
		}

	}
	
	/* 자석 회전 */
	private static void rotate(int num, int direction) {
		// 시계방향 회전
		if(direction==1) {
			index[num].red = --index[num].red==-1 ? 7 : index[num].red;
			index[num].right = --index[num].right==-1 ? 7 : index[num].right;
			index[num].left = --index[num].left==-1 ? 7 : index[num].left;
		}
		// 시계 반대방향 회전
		else {
			index[num].red = ++index[num].red==8 ? 0 : index[num].red;
			index[num].right = ++index[num].right==8 ? 0 : index[num].right;
			index[num].left = ++index[num].left==8 ? 0 : index[num].left;
		}
	}
	
	static class MagnetIndex {
		int red; // 빨간색 화살표
		int right, left; // 오른쪽 왼쪽 인덱스
		public MagnetIndex(int red, int right, int left) {
			super();
			this.red = red;
			this.right = right;
			this.left = left;
		}
		
	}

}

