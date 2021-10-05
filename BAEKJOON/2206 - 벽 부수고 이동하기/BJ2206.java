package BEAKJOON;

import java.util.*;
import java.io.*;

public class BJ2206 {

	public static int answer = Integer.MAX_VALUE;
	public static int N, M; // �� ũ��
	public static int[][] map; // ��
	
	public static int[][] direction = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// ���� �Է�
		String[] info = br.readLine().split(" ");
		N = Integer.parseInt(info[0]);
		M = Integer.parseInt(info[1]);
		map = new int[N][M];
		
		for(int r=0; r<N; r++) {
			info = br.readLine().split("");
			for(int c=0; c<M; c++)
				map[r][c] = Integer.parseInt(info[c]);
		}
		
		// �� 1�� �μ��� �ִ� �Ÿ� ���ϱ�
		bfs();
		
		// (N-1, M-1)�� ������ �� ���� �����
		if(answer==Integer.MAX_VALUE)
			answer = -1;
		
		System.out.println(answer);

	}
	
	// �� 1�� �μ��� �ִ� �Ÿ� ���ϱ�
	private static void bfs() {
		
		boolean[][][] visited = new boolean[2][N][M]; // visited[���μ̴�������][��][��]
		
		Queue<Pair> queue = new LinkedList<>();
		queue.offer(new Pair(0, 0, 1, 0));
		
		while(!queue.isEmpty()) {
			Pair now = queue.poll();
			
			// ��ǥ ������ ������ ���
			if(now.r==N-1 && now.c==M-1) {
				answer =now.dist;
				break;
			}
				
			// ��� Ž��
			for(int d=0; d<4; d++) {
				Pair next = new Pair(now.r+direction[d][0], now.c+direction[d][1], now.dist+1, now.crash);
				
				// �� ������ �Ѿ�ų� ���� ���
				if(next.r<0 || next.r>=N || next.c<0 || next.c>=M)
					continue;
				
				// step1. �ش� ��ġ�� ���� ���� ���
				if(map[next.r][next.c]==0) {
					// �湮���� �ʾ����� �̵� ����
					if(visited[next.crash][next.r][next.c]==false) {
						visited[next.crash][next.r][next.c] = true;
						queue.offer(next);
					}
				}
				// step2. �ش� ��ġ�� ���� �ִ� ���
				else {
					// �湮���� �ʾҰ�, ���� �μ� �� �ִ� ��츸 �湮 ���� (������� ���� �μ��� �ʾ��� ���)
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
		int dist; // ������� �̵� �Ÿ�
		int crash; // ������� ���� �μ̴��� ���� (0: �ν��� ����, 1: �ν�)
		
		
		public Pair(int r, int c, int dist, int crash) {
			super();
			this.r = r;
			this.c = c;
			this.dist = dist;
			this.crash = crash;
		}
	}
}


