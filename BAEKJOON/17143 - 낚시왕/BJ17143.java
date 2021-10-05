import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
public class BJ17143 {

	public static int[][] direction = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
	public static int R, C, M; // 격자판 크기 (R, C), 상어 수(M)
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] info = br.readLine().split(" ");
		R = Integer.parseInt(info[0]);
		C = Integer.parseInt(info[1]);
		M = Integer.parseInt(info[2]);
		Shark[] sharks = new Shark[M+1];
		int[][] map = new int[R+1][C+1];
		
		sharks[0] = new Shark(0, 0, 0, 0, 0); // 상어 번호를 1부터 시작하기 위해 빈 데이터 입력
		for(int m=1; m<=M; m++) {
			info = br.readLine().split(" ");
			
			int x = Integer.parseInt(info[0]); // 상어 행 위치
			int y = Integer.parseInt(info[1]); // 상어 열 위치
			int s = Integer.parseInt(info[2]); // 상어 속력
			int d = Integer.parseInt(info[3]); // 상어 방향
			int z = Integer.parseInt(info[4]); // 상어 크기
				
			sharks[m] = new Shark(x, y, s, d-1, z);
			map[x][y] = m;
		}
		
		int answer = 0; // 낚시왕이 잡은 상어 크기의 합
		int king = 0;
		while(true) {
		
			// step1. 낚시왕 이동
			if(++king==C+1)
				break;

			// step2. 낚시왕 낚시하기
			for(int r=1; r<R+1; r++) {
				if(map[r][king]==0)
					continue;
				Shark shark = sharks[map[r][king]];
				answer += shark.z;
				shark.z = 0; // 없는 상어 처리
				map[r][king] = 0;
				break;
			}
			
			// step3. 상어 이동
			int[][] tmp = new int[R+1][C+1];
			for(int m=1; m<=M; m++) {
				Shark shark = sharks[m];
				
				// 맵 위에 없는 상어인 경우
				if(shark.z==0)
					continue;
				
				// 상어 이동 시간 줄이기
				int total_move = shark.s;
				if(shark.d<=1) // 위아래로 움직이는 상어일 때
					total_move = total_move % ((R-1)*2);
				else // 좌우로 움직이는 상어일 때
					total_move = total_move % ((C-1)*2);
				
				// 상어 이동
				for(int move=0; move<total_move; move++) {
					int x = shark.x + direction[shark.d][0];
					int y = shark.y + direction[shark.d][1];
					
					if(x<1 || x>R || y<1 || y>C) {
						shark.d++;
						if(shark.d==2) shark.d=0;
						else if(shark.d==4) shark.d=2;
						move--;
						continue;
					}
					
					shark.x = x;
					shark.y = y;
				}
				
				// 상어가 없는 칸이거나 이미 있는 상어보다 크기가 큰 경우
				if(tmp[shark.x][shark.y]==0 || sharks[tmp[shark.x][shark.y]].z<shark.z) {
					sharks[tmp[shark.x][shark.y]].z = 0;
					tmp[shark.x][shark.y] = m;
				}
				// 이미 있는 상어보다 크기가 작은 경우
				else
					shark.z = 0;
			}
			copy(map, tmp);
		}
		
		System.out.println(answer);
		
		
	}
	private static void copy(int[][] a, int[][] b) {
		for(int r=1; r<=R; r++)
			for(int c=1; c<=C; c++)
				a[r][c] = b[r][c];
	}
	

}

class Shark {
	int x, y; // 상어 위치
	int s, d, z; // 상어 속력, 방향, 크기
	public Shark(int x, int y, int s, int d, int z) {
		super();
		this.x = x;
		this.y = y;
		this.s = s;
		this.d = d;
		this.z = z;
	}
}