package com.ssafy.algo13;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ3109 {

	public static int R, C; // 맵 크기
	public static String[][] map; // 맵
	public static int answer = 0;
	
	public static int[][] direction = {{-1, 1}, {0, 1}, {1, 1}};
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] info = br.readLine().split(" ");
		R = Integer.parseInt(info[0]);
		C = Integer.parseInt(info[1]);
		map = new String[R][C];
		
		for(int r=0; r<R; r++) {
			info = br.readLine().split("");
			for(int c=0; c<C; c++) {
				map[r][c] = info[c];
			}
		}

		for(int r=0; r<R; r++) {
			putPipeline(r, 0);
		}
		
		System.out.println(answer);
	}

	/* 파이프 놓기 */
	// sRow : 시작 row, (x,y) : 현재 좌표, cnt : 현재까지 놓은 파이프라인 수
	private static boolean putPipeline(int x, int y) {
				
		if(y==C-2) {
			answer++;
			return true;
		}
		
		for(int d=0; d<3; d++) {
			int nx = x + direction[d][0];
			int ny = y + direction[d][1];
			
			if(nx<0 || nx>=R)
				continue;
			
			if(map[nx][ny].equals("x")) {
				continue;
			}
			
			map[nx][ny] = "x";
			if(putPipeline(nx, ny))
				return true;
		}
		return false;
	}
}
