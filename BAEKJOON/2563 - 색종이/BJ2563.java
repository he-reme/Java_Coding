import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ2563 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int answer = 0;
		int[][] white = new int[101][101];
		
		int cnt = Integer.parseInt(br.readLine());
		for(int i=0; i<cnt; i++) {
			String[] info = br.readLine().split(" ");
		
			int startX = Integer.parseInt(info[0]);
			int startY = Integer.parseInt(info[1]);
			
			for(int x=startX; x<startX+10; x++) {
				for(int y=startY; y<startY+10; y++) {
					if(white[x][y]!=0)
						continue;
					
					answer++;
					white[x][y] = 1;
				}
			}
		}
		System.out.println(answer);
	}
}
