package com.ssafy.algo16;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SW3289 {

	public static int n, m; // 원소 개수, 연산 개수
	public static int parents[]; // 원소가 속한 집합의 대표자
	
	public static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T  = Integer.parseInt(br.readLine());
		
		for(int test_case=1; test_case<=T; test_case++) {
			
			sb.append("#").append(test_case).append(" ");
			
			// n, m 입력
			String[] info = br.readLine().split(" ");
			n = Integer.parseInt(info[0]);
			m = Integer.parseInt(info[1]);
			
			// 집합 초기화
			make();
			
			// 연산
			int type, a, b;
			for(int i=0; i<m; i++) {
				
				info = br.readLine().split(" ");
				
				a = Integer.parseInt(info[1]);
				b = Integer.parseInt(info[2]);
				
				// a, b 합집합
				if(info[0].equals("0"))
					union(a, b);

				// a, b가 같은 집합에 포함되어 있는지 확인
				else {
					if(find(a)==find(b))
						sb.append("1");
					else
						sb.append("0");
				}
			}
			
			sb.append("\n");
		}
		System.out.println(sb);
	}
	
	// 모든 원소를 자신을 대표자로 만듦
	private static void make() {
		
		parents = new int[n+1];
		
		for(int i=1; i<n+1; i++)
			parents[i] = i;
	}
	
	// a가 포함되어있는 집합과 b가 포함되어있는 집합을 합집합
	private static void union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		
		parents[bRoot] = aRoot;
	}

	// a의 집합 대표자 찾기
	private static int find(int a) {
		
		// 대표자가 자기 자신이면
		if(a==parents[a])
			return a;
		
		return parents[a] = find(parents[a]);
	}
}
