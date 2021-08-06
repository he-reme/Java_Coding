import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SW1210 {

	// 아래에서 위로 백트래킹
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
		int[][] direction = {{0, -1}, {0, 1}, {-1, 0}}; // 좌, 우, 상
		
		for (int test_case = 1; test_case < 2; test_case++) {

			String t = br.readLine();
			int[][] map = new int[100][100]; // 사다리타기판 상황
			int d = 2; // 상 방향
			int nowX = 99; // 목표 지점부터 백트래킹
			int nowY = 0;
            for(int r=0; r<100; r++) {
            	String[] line = br.readLine().split(" ");

                for (int c=0; c<100; c++) {
                	map[r][c] = Integer.parseInt(line[c]);
                	if(map[r][c]==2)
                		nowY = c;
                }
            }   
                
            while(true) {
               	if(nowX==0)
               		break;
               	
               	int nextX = nowX + direction[d][0];
               	int nextY = nowY + direction[d][1];
               	
               	// 1. d==2일 경우 (상으로 가고있는 중인 경우)
               	// 1-1. 양 옆으로 갈 수 있는지 체크
               	// 1-2. 없으면 상으로 gogo
               	if(d==2) {
               		for(int i=0; i<3; i++) {
               			nextX = nowX + direction[i][0];
               			nextY = nowY + direction[i][1];
               			if(nextY<0 || nextY>=100)
               				continue;
               			if(map[nextX][nextY]==0)
               				continue;
               			d=i;
               			break;
               		}
               	}
               	// 2. d==0 또는 d==1일 경우 (좌/우로 가고있는 중인 경우)
               	// 2-1. 해당 방향으로 계속 갈 수 있는지 체크
               	// 2-2. 없으면 상으로 gogo
               	else {
               		nextX = nowX + direction[d][0];
               		nextY = nowY + direction[d][1];
               		if(nextY<0 || nextY>=100 || map[nextX][nextY]==0){
               			d = 2;
               			nextX = nowX + direction[d][0];
               			nextY = nowY + direction[d][1];
               		}	
               	}
               	nowX = nextX;
               	nowY = nextY; 
           }
		
           System.out.printf("#%d %d", test_case, nowY);
		}
	}

}
