import java.util.*;
public class P1829 {

	public int[] solution(int m, int n, int[][] picture) {
		int[] answer = new int[2];
		int numberOfArea = 0; // 그림에 있는 영역 갯수
        int maxSizeOfOneArea = 0; // 그림의 가장 큰 영역의 크기

        // 그림 입력
        int[][] pictures = new int[m][n];
        for(int i=0; i<m; i++)
        	pictures[i] = picture[i].clone();
        
        for(int r=0; r<m; r++) {
        	for(int c=0; c<n; c++) {
        		// 색이 있는 부분이거나 이미 영역으로 지정된 부분인 경우
        		if(pictures[r][c]==0 || pictures[r][c]==-1)
        			continue;
        		
        		// 새로운 영역을 구하고 답 갱신
        		int result = find_area(m, n, r, c, pictures);
        		if(result > maxSizeOfOneArea)
        			maxSizeOfOneArea = result;
        		numberOfArea++;
        	}
        }
        
        answer[0] = numberOfArea;
        answer[1] = maxSizeOfOneArea;
        return answer;
    }
	
	// 새로운 영역 구하기
	private static int find_area(int m, int n, int r, int c, int[][] picture) {
		int sizeOfArea = 1; // 새로운 영역의 사이즈
		int num = picture[r][c]; // 새로운 영역의 숫자
		int[][] direction = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
		
		Queue<Pair> queue = new LinkedList<>();
		queue.offer(new Pair(r, c));
		picture[r][c] = -1;
		
		while(!queue.isEmpty()) {
			Pair now = queue.poll();
			for(int d=0; d<4; d++) {
				Pair next = new Pair(now.r + direction[d][0], now.c + direction[d][1]);
				if(next.r<0 || next.r>=m || next.c<0 || next.c>=n)
					continue;
				if(picture[next.r][next.c]!=num)
					continue;
				
				sizeOfArea++;
				queue.offer(next);
				picture[next.r][next.c] = -1;
				
			}
		}
		return sizeOfArea;
	}
	
	static class Pair {
		int r, c;

		public Pair(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}
	}
}

