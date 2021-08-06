import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SW1873 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T =Integer.parseInt(br.readLine());
		
		int[][] direction = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
		
		for(int test_case=1; test_case<=T; test_case++) {
			
			// 맵 정보 입력
			String[] size = br.readLine().split(" ");
			int H = Integer.parseInt(size[0]);
			int W = Integer.parseInt(size[1]);
			
			char[][] map = new char[H][W];
			
			Tank tank = new Tank();

			
			for(int h=0; h<H; h++) {
				String state = br.readLine();
				for(int w=0; w<W; w++) {
					map[h][w] = state.charAt(w);

					if(map[h][w]=='^' || map[h][w]=='v' || map[h][w]=='<' || map[h][w]=='>') {
						int d = 0;
						if(map[h][w]=='^') d = 0;
						else if(map[h][w]=='v') d = 1;
						else if(map[h][w]=='<') d = 2;
						else if(map[h][w]=='>') d = 3;
						map[h][w] = '.';
						tank.set(h, w, d);
					}
				}
			}
			
			int N = Integer.parseInt(br.readLine());
			String orders = br.readLine();
			for(int i=0; i<N; i++) {
				char order = orders.charAt(i);
				int d = tank.d;
				
				// 대포 쏘기 
				if(order=='S') {
					int boomX = tank.x;
					int boomY = tank.y;
					while(true) {
						boomX += direction[d][0];
						boomY += direction[d][1];
						
						// 폭탄이 맵 밖으로 나가거나 강철로 만들어진 벽이면
						if(boomX<0 || boomX>=H || boomY<0 || boomY>=W || map[boomX][boomY]=='#')
							break;
						
						// 벽돌 벽이면
						if(map[boomX][boomY]=='*'){
							map[boomX][boomY] = '.';
							break;
						}
					}
				}
				// 이동 하기
				else {
					if(order=='U') d = 0;
					else if(order=='D') d = 1;
					else if(order=='L') d = 2;
					else if(order=='R') d = 3;
					
					int x = tank.x + direction[d][0];
					int y = tank.y + direction[d][1];
					
					// 이동하려는 곳이 격자 밖이거나, 평지가 아닌 경우 : 방향만 전환
					if(x<0 || x>=H || y<0 || y>=W || map[x][y]!='.') {
						tank.d = d;
						continue;
					}
						
					tank.set(x, y, d); // 이동, 방향전환
				}			
			}
			
			if(tank.d==0) map[tank.x][tank.y] = '^';
			else if(tank.d==1) map[tank.x][tank.y] = 'v';
			else if(tank.d==2) map[tank.x][tank.y] = '<';
			else if(tank.d==3) map[tank.x][tank.y] = '>';
			
			System.out.printf("#%d ", test_case);
			for(int h=0; h<H; h++) {
				for(int w=0; w<W; w++) {
					System.out.printf("%c", map[h][w]);
				}
				System.out.println();
			}
		}
		br.close();
	}
}

class Tank{
	int x, y;
	int d;
	public Tank() {

	}
	public void set(int x, int y, int d) {
		this.x = x;
		this.y = y;
		this.d = d;
	}
}