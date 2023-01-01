package BEAKJOON;

import java.util.*;
import java.io.*;

public class BJ5430 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		for(int test_case=1; test_case<=T; test_case++) {
			
			String[] p = br.readLine().split(""); // 수행할 함수
			int n = Integer.parseInt(br.readLine()); // 수의 개수
			Deque<Integer> deque = new LinkedList<>(); // 배열 상태
			
			// 배열에 수 입력
			String info = br.readLine();
			info = info.substring(1, info.length()-1);
			String[] numbers = info.split(",");
			
			for(int i=0; i<n; i++)
				deque.add(Integer.parseInt(numbers[i]));
				
			
			// 함수 수행
			int mode = 0; // 0: 앞숫자 버리는 모드, 1: 뒷숫자 버리는 모드
			boolean isError = false; // false : 에러가 발생하지 않았을 때, true : 에러가 발생했을 때
			
			for(int i=0; i<p.length; i++) {
				
				if(p[i].equals("R")) {
					if(mode==0)
						mode = 1;
					else
						mode = 0;
				}
				
				else if(p[i].equals("D")) {
					if(deque.isEmpty()) {
						isError = true;
						break;
					}
					
					if(mode==0)
						deque.pollFirst();
					else
						deque.pollLast();
				}
			}
			
			if(isError==true)
				sb.append("error").append("\n");
			else {
				sb.append("[");
				while(!deque.isEmpty()) {
					if(mode==0)
						sb.append(deque.pollFirst());
					else
						sb.append(deque.pollLast());
					
					if(deque.isEmpty())
						break;
					
					sb.append(",");
				}
				sb.append("]").append("\n");
			}
		}

		System.out.println(sb);
		br.close();
	}

}
