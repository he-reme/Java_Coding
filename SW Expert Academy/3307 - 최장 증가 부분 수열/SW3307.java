import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SW3307 {

	public static int answer;
	public static int N;
	public static int[] arr;
	public static int[] LIS;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		for(int test_case=1; test_case<=T; test_case++) {
			answer = 0;
			
			N = Integer.parseInt(br.readLine());
			arr = new int[N];
			LIS = new int[N];
			
			String[] info = br.readLine().split(" ");
			for(int i=0; i<N; i++) {
				arr[i] = Integer.parseInt(info[i]);
			}
			
			for(int i=0; i<N; i++) {
				LIS[i] = 1;
				for (int j=0; j<i; j++) {
					if(arr[j] <= arr[i] && LIS[i] < LIS[j]+1)
						LIS[i] = LIS[j]+1;
				}
				
				answer = answer > LIS[i] ? answer : LIS[i];
			}
			
			System.out.printf("#%d %d%n", test_case, answer);
		}
		
		br.close();
	}

}
