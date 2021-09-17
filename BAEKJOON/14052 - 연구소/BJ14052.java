package com.ssafy.algo21;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BJ14052 {

	public static int N, M;
	public static List<Pair> empty = new LinkedList<>(); // 빈칸 위치
	public static Queue<Pair> virus = new LinkedList<>(); // 바이러스 위치
	public static int[][] map;
	
	public static int answer = 0;
	
	public static int[][] direction = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] info = br.readLine().split(" ");
		N = Integer.parseInt(info[0]);
		M = Integer.parseInt(info[1]);
		map = new int[N][M];
		
		for(int r=0; r<N; r++) {
			info = br.readLine().split(" ");
			for(int c=0; c<M; c++) {
				map[r][c] = Integer.parseInt(info[c]);
				
				// 빈칸 위치 추가
				if(map[r][c]==0)
					empty.add(new Pair(r, c));
				// 바이러스 위치 추가
				else if(map[r][c]==2)
					virus.offer(new Pair(r, c));	
			}
		}
		
		putWall(0, 0);
		
		System.out.println(answer);
	}
	
	// step1. 벽 놓기
	private static void putWall(int cnt, int start) {
		
		// 벽 3개를 다 놓았을 때
		if(cnt==3) {
			spreadVirus();
			return;
		}
		
		// 조합으로 벽 놓기
		for(int i=start; i<empty.size(); i++) {
			Pair pos = empty.get(i);
			
			map[pos.x][pos.y] = 1;
			putWall(cnt+1, i+1);
			map[pos.x][pos.y] = 0;
		}
	}
	
	// step2. 바이러스 퍼트리기
	private static void spreadVirus() {
		// 바이러스 큐
		Queue<Pair> queue = new LinkedList<>();
		queue.addAll(virus);
		
		int virus_cnt = 0; // 새롭게 퍼진 바이러스 갯수
		
		int[][] visited = new int[N][M]; // 방문한 좌표인지 체크
		
		while(!queue.isEmpty()) {
			Pair now = queue.poll();
			
			for(int d=0; d<4; d++) {
				Pair next = new Pair(now.x+direction[d][0], now.y+direction[d][1]);
				
				// 범위를 넘어선 경우
				if(next.x<0 || next.x>=N || next.y<0 || next.y>=M)
					continue;
				
				// 해당 위치가 벽이거나 바이러스가 시작된 곳이라면
				if(map[next.x][next.y]!=0)
					continue;
				
				// 이미 방문한 곳이라면
				if(visited[next.x][next.y]==1)
					continue;
				
				queue.offer(next);
				visited[next.x][next.y] = 1;
				virus_cnt++;
			}
		}
		
		
		int safety_zone = empty.size() - 3 - virus_cnt;
		answer = answer > safety_zone ? answer : safety_zone;
	}
}

class Pair {
	int x, y;

	public Pair(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}
	
}
