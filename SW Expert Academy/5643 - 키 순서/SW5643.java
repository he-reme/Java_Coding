package hwalgo26_서울_8반_김혜림;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class SW5643 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine().split(" ")[0]);
		for(int test_case=1; test_case<=T; test_case++) {
			
			// 정보 입력
			int N = Integer.parseInt(br.readLine().split(" ")[0]); // 학생들의 수
			int M = Integer.parseInt(br.readLine().split(" ")[0]); // 키 비교 횟수
			int[] cnt_small = new int[N+1]; // 학생 N보다 키가 작은 학생 수
			int[] cnt_tall = new int[N+1]; // 학생 N보다 키가 큰 학생 수
			
			List<List<Integer>> relation = new LinkedList<>(); // 키 비교
			for(int n=0; n<=N; n++)
				relation.add(new LinkedList<>());
		
			// 키 비교 입력
			for(int m=0; m<M; m++) {
				String[] info = br.readLine().split(" ");
				int small = Integer.parseInt(info[0]);
				int tall = Integer.parseInt(info[1]);
				relation.get(small).add(tall);
			}
			
			// 1번 학생 ~ N번 학생을 순회하면서 해당 학생보다 작은 학생, 큰 학생 수 카운트
			for(int now=1; now<=N; now++) {
				
				int[] visited = new int[N+1];
				Queue<Integer> queue = new LinkedList<>();
				
				queue.offer(now);
				visited[now] = 1;
				
				while(!queue.isEmpty()) {
					int small = queue.poll();
					for(int tall : relation.get(small)) {
						if(visited[tall]==1)
							continue;
						
						cnt_tall[now]++;
						cnt_small[tall]++;
						
						queue.offer(tall);
						visited[tall] = 1;
					}
				}
			}
			int answer = 0;
			for(int n=1; n<=N; n++) {
				if(cnt_small[n]+cnt_tall[n] == N-1)
					answer++;
			}
			
			System.out.printf("#%d %d%n", test_case, answer);
		}

	}

}
