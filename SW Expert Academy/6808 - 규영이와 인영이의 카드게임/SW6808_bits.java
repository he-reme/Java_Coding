package com.ssafy.algo09;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SW6808_bits {

	public static int win, lose; // 규영이가 이긴 경우, 진 경우의 수
	public static int[] gCards = new int[10]; // 규영이의 카드 리스트 (순서O)
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());
		
		for(int test_case=1; test_case<=T; test_case++) {
			// 테스트 케이스마다 초기화
			win = 0;
			lose = 0;
			
			int flag = 0; // 가져간 카드인지 체크
			String[] info = br.readLine().split(" ");
			
			// 규영이의 카드 순서대로 입력 & 가져간 카드임을 표시
			for(int i=1; i<=9; i++) {
				int num = Integer.parseInt(info[i-1]);
				gCards[i] = num;
				flag = flag | 1<<num;
			}
			
			// 순열
			permutation(0, 0, 0, flag);
			
			System.out.printf("#%d %d %d%n", test_case, win, lose);
		}
	}
	
	// 순열 함수
	// cnt : 인영이가 고른 카드 수, gTotal : 규영이의 총 점수, iTotal : 인영이의 총 점수, flag : 가져간 카드 상황
	private static void permutation(int cnt, int gTotal, int iTotal, int flag) {
		
		// 인영이가 카드 순서를 다 정했을 때
		if(cnt==9) {
			// 총 점수 비교
			if(gTotal>iTotal) win++;
			else lose++;
			
			return;
		}
		
		// 1~18번의 카드에서 선택되지 않은 카드 선택하기
		for(int n=1; n<=18; n++) {
			
			// 이미 가져간 카드일 경우
			if((flag & 1<<n) !=0 )
				continue;
			
			int sum = gCards[cnt+1] + n;
			
			if(gCards[cnt+1]>n)
				permutation(cnt+1, gTotal+sum, iTotal, flag | 1<<n);
			else
				permutation(cnt+1, gTotal, iTotal+sum, flag | 1<<n);
		
		}
	}

}
