import java.io.*;
public class SW9229 {

	public static int answer;
	public static String[] info; 
	public static int N, M; // 과자 봉지 개수, 무게 합 제한
	public static String[] snacks; // 과자들의 무게 정보
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for(int test_case=1; test_case<=T; test_case++) {
			answer = -1;
			
			info = br.readLine().split(" ");
			N = Integer.parseInt(info[0]); // 과자 봉지 개수
			M = Integer.parseInt(info[1]); // 무게 합 제한
			
			snacks = br.readLine().split(" ");
			
			choose(0, 0, 0);
			
			System.out.printf("#%d %d%n", test_case, answer);
		}
		
		br.close();
	}

	private static void choose(int index, int cnt, int weight) {
	
		if(weight>M)
			return;
		
		if(cnt==2) {
			answer = answer>weight? answer:weight;
			return;
		}
	
		if(index==N)
			return;
		// 해당 index의 과자를 들지 않았을 경우
		choose(index+1, cnt, weight);
		
		int add_weight = weight + Integer.parseInt(snacks[index]);
		// 해당 index의 과자를 들었을 경우
		choose(index+1, cnt+1, add_weight);
	}
}
