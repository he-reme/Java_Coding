import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.IntStream;

public class BJ17406 {

	static int answer = 100 * 50;
	static int N, M, K; // 배열 크기(N,M), 회전 연산의 개수(K)
	static List<RotationInfo> rInfo = new LinkedList<>();
	static int[] isChecked = new int[6];
	
	static int[][] direction = { {0, 1}, {1, 0}, {0, -1}, {-1, 0} };
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] info = br.readLine().split(" ");
		N = Integer.parseInt(info[0]);
		M = Integer.parseInt(info[1]);
		K = Integer.parseInt(info[2]);
		
		int[][] map = new int[51][51];
		for (int r = 1; r < N + 1; r++) {
			String[] line = br.readLine().split(" ");
			for (int c = 1; c < M + 1; c++)
				map[r][c] = Integer.parseInt(line[c-1]);
		}
			
		for (int k = 0; k < K; k++)
		{
			info = br.readLine().split(" ");
			RotationInfo tmp = new RotationInfo(Integer.parseInt(info[0]), Integer.parseInt(info[1]), Integer.parseInt(info[2]));
			rInfo.add(tmp);
		}
		
		permutation(0, map);
		
		System.out.println(answer);
		
		br.close();
	}

	private static void permutation(int cnt, int[][] map) {
		if (cnt == K)
		{
			for (int r = 1; r < N + 1; r++)
			{
				int sum = IntStream.of(map[r]).sum();
				answer = answer < sum ? answer : sum;
			}
			return;
		}

		for (int k = 0; k < K; k++)
		{
			if (isChecked[k] == 1)
				continue;

			int[][] tmp = new int[51][51];
			copy(map, tmp);

			isChecked[k] = 1;
			rotate(rInfo.get(k).r, rInfo.get(k).c, rInfo.get(k).s, tmp);
			permutation(cnt + 1, tmp);
			isChecked[k] = 0;
		}
	}
	

	private static void rotate(int r, int c, int s, int[][] map)
	{
		int startX = r - s;
		int startY = c - s;
		int move = s * 2;

		while (true)
		{
			if (startX == r && startY == c)
				break;

			int x = startX;
			int y = startY;
			int now = map[x][y];
			int next = 0;

			// 네 방향 회전
			for (int d = 0; d < 4; d++)
			{
				// move 만큼 회전
				for (int m = 0; m < move; m++)
				{
					x = x + direction[d][0];
					y = y + direction[d][1];

					next = map[x][y]; // 다음 숫자
					map[x][y] = now;
					now = next;
				}
			}
			startX = startX + 1;
			startY = startY + 1;
			move = move - 2;
		}
	}

	private static void copy(int[][] map, int[][] tmp)
	{
		for (int r = 1; r < N + 1; r++)
			for (int c = 1; c < M + 1; c++)
				tmp[r][c] = map[r][c];
	}
}

class RotationInfo {
	int r, c, s;

	public RotationInfo(int r, int c, int s) {
		this.r = r;
		this.c = c;
		this.s = s;
	}
	
};
