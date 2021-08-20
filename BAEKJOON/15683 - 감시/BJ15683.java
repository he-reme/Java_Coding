package com.ssafy.algo14;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

public class BJ15683 {

	public static int answer = Integer.MAX_VALUE;
	public static int N, M; // 지도 크기
	public static List<CCTV> cctvs = new LinkedList<>(); // CCTV 리스트
	
	public static int[][] direction = {{0, 1}, {0, -1}, {-1, 0}, {1, 0}};
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String[] info = br.readLine().split(" ");
		N = Integer.parseInt(info[0]);
		M = Integer.parseInt(info[1]);
		
		int[][] map = new int[N][M];
		int blindSpot = 0;
		
		for(int n=0; n<N; n++) {
			
			info = br.readLine().split(" ");
			
			for(int m=0; m<M; m++) {
				
				map[n][m] = Integer.parseInt(info[m]);
				
				if(map[n][m]==0)
					blindSpot++;
				else if(map[n][m]>0 && map[n][m]<6)
					cctvs.add(new CCTV(n, m, map[n][m]));
			}
		}
		
		putCCTV(0, blindSpot, map);
		
		System.out.println(answer);
		
		br.close();
		
	}
	
	/* CCTV 놓기 */
	// num : CCTV 번호, blindSpot : 사각지대 갯수
	private static void putCCTV (int num, int blindSpot, int[][] map) {
		
		// 기저조건
		if(num==cctvs.size()) {
			answer = answer < blindSpot ? answer : blindSpot;
			return;
		}
		
		int[][] tmp = new int[N][M];
		int cctvSpot; // 새로 감시 시작한 공간의 수
		
		CCTV cctv = cctvs.get(num); // 현재 CCTV
			
		if(cctv.n==1) {
			for(int d=0; d<4; d++) {
				copy(map, tmp);
				cctvSpot = getCCTVSpot(cctv.x, cctv.y, d, tmp);
				putCCTV(num+1, blindSpot-cctvSpot, tmp);
			}
		}
		else if(cctv.n==2) {
			for(int d=0; d<3; d=d+2) {
				copy(map, tmp);
				cctvSpot = getCCTVSpot(cctv.x, cctv.y, d, tmp);
				cctvSpot += getCCTVSpot(cctv.x, cctv.y, d+1, tmp);
				putCCTV(num+1, blindSpot-cctvSpot, tmp);
			}
			
		}
		else if(cctv.n==3) {
			for(int d1=0; d1<2; d1++) {
				for(int d2=2; d2<4; d2++) {
					copy(map, tmp);
					cctvSpot = getCCTVSpot(cctv.x, cctv.y, d1, tmp);
					cctvSpot += getCCTVSpot(cctv.x, cctv.y, d2, tmp);
					putCCTV(num+1, blindSpot-cctvSpot, tmp);
				}
			}
		}
		else if(cctv.n==4) {
			for(int d1=0; d1<2; d1++) {
				for(int d2=d1+1; d2<3; d2++) {
					for(int d3=d2+1; d3<4; d3++) {
						copy(map, tmp);
						cctvSpot = getCCTVSpot(cctv.x, cctv.y, d1, tmp);
						cctvSpot += getCCTVSpot(cctv.x, cctv.y, d2, tmp);
						cctvSpot += getCCTVSpot(cctv.x, cctv.y, d3, tmp);
						putCCTV(num+1, blindSpot-cctvSpot, tmp);
					}
				}
			}
		}
		else if(cctv.n==5) {
			copy(map, tmp);
			cctvSpot = 0;
			for(int d=0; d<4; d++)
				cctvSpot += getCCTVSpot(cctv.x, cctv.y, d, tmp);
			putCCTV(num+1, blindSpot-cctvSpot, tmp);
		}
		
		
	}
	private static int getCCTVSpot(int x, int y, int d, int[][] tmp) {
		int cctvSpot = 0;
		while(true) {
			x = x + direction[d][0];
			y = y + direction[d][1];
			
			// 지도를 벗어났거나 벽인 경우
			if(x<0 || x>=N || y<0 || y>=M || tmp[x][y]==6)
				break;

			// 처음 감시되는 곳인 경우
			if(tmp[x][y]==0)
				cctvSpot++;
			
			// 감시하는 곳임을 표시
			tmp[x][y] = -1;
		}
		
		return cctvSpot;
	}
	
	private static void copy(int[][] map, int[][] tmp) {
		for(int n=0; n<N; n++)
			for(int m=0; m<M; m++)
				tmp[n][m] = map[n][m];
	}

}

class CCTV {
	int x, y; // CCTV 좌표
	int n; // 번호
	public CCTV(int x, int y, int n) {
		super();
		this.x = x;
		this.y = y;
		this.n = n;
	}
	
}
