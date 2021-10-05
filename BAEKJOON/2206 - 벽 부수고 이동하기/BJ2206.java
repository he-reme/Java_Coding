package BEAKJOON;

import java.util.*;
import java.io.*;

public class BJ2206 {

	public static int answer = Integer.MAX_VALUE;
	public static int N, M; // 맵 크기
	public static int[][] map; // 맵
	
	public static int[][] direction = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// 정보 입력
		String[] info = br.readLine().split(" ");
		N = Integer.parseInt(info[0]);
		M = Integer.parseInt(info[1]);
		map = new int[N][M];
		
		for(int r=0; r<N; r++) {
			info = br.readLine().split("");
			for(int c=0; c<M; c++)
				map[r][c] = Integer.parseInt(info[c]);
		}
		
		// 벽 1개 부수며 최단 거리 구하기
		bfs();
		
		// (N-1, M-1)에 접근할 수 없느 ㄴ경우
		if(answer==Integer.MAX_VALUE)
			answer = -1;
		
		System.out.println(answer);

	}
	
	// 벽 1개 부수며 최단 거리 구하기
	private static void bfs() {
		
		boolean[][][] visited = new boolean[2][N][M]; // visited[벽부셨는지여부][행][열]
		
		Queue<Pair> queue = new LinkedList<>();
		queue.offer(new Pair(0, 0, 1, 0));
		
		while(!queue.isEmpty()) {
			Pair now = queue.poll();
			
			// 목표 지점에 도달한 경우
			if(now.r==N-1 && now.c==M-1) {
				answer =now.dist;
				break;
			}
				
			// 사방 탐색
			for(int d=0; d<4; d++) {
				Pair next = new Pair(now.r+direction[d][0], now.c+direction[d][1], now.dist+1, now.crash);
				
				// 맵 범위를 넘어서거나 벽인 경우
				if(next.r<0 || next.r>=N || next.c<0 || next.c>=M)
					continue;
				
				// step1. 해당 위치에 벽이 없는 경우
				if(map[next.r][next.c]==0) {
					// 방문하지 않았으면 이동 가능
					if(visited[next.crash][next.r][next.c]==false) {
						visited[next.crash][next.r][next.c] = true;
						queue.offer(next);
					}
				}
				// step2. 해당 위치에 벽이 있는 경우
				else {
					// 방문하지 않았고, 벽을 부술 수 있는 경우만 방문 가능 (현재까지 벽을 부수지 않았을 경우)
					if(visited[next.crash][next.r][next.c]==false && next.crash==0) {
						next.crash = 1;
						visited[next.crash][next.r][next.c] = true;
						queue.offer(next);
					}
				}
			}
		}
		
	}

	static class Pair {
		int r, c;
		int dist; // 현재까지 이동 거리
		int crash; // 현재까지 벽을 부셨는지 여부 (0: 부시지 않음, 1: 부심)
		
		
		public Pair(int r, int c, int dist, int crash) {
			super();
			this.r = r;
			this.c = c;
			this.dist = dist;
			this.crash = crash;
		}
	}
}


