package BEAKJOON;

import java.util.*;
import java.io.*;

public class BJ5430 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		for(int test_case=1; test_case<=T; test_case++) {
			
			String[] p = br.readLine().split(""); // ������ �Լ�
			int n = Integer.parseInt(br.readLine()); // ���� ����
			Deque<Integer> deque = new LinkedList<>(); // �迭 ����
			
			// �迭�� �� �Է�
			String info = br.readLine();
			info = info.substring(1, info.length()-1);
			String[] numbers = info.split(",");
			
			for(int i=0; i<n; i++)
				deque.add(Integer.parseInt(numbers[i]));
				
			
			// �Լ� ����
			int mode = 0; // 0: �ռ��� ������ ���, 1: �޼��� ������ ���
			boolean isError = false; // false : ������ �߻����� �ʾ��� ��, true : ������ �߻����� ��
			
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
