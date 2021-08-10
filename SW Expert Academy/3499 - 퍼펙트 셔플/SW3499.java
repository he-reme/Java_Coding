import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SW3499 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		int index1, end1;
		int index2, end2;
		for(int test_case=1; test_case<=T; test_case++) {
			int N = Integer.parseInt(br.readLine());
			String[] cards = br.readLine().split(" ");
			
			// 1. 덱 반으로 나누기
			index1 = 0;
			if(N%2==1)
				end1 = N/2+1;
			else
				end1 = N/2;
			
			index2 = end1;
			end2 = N;
			
			// 테스트 케이스 번호 출력
			System.out.printf("#%d ", test_case);
			
			// 새로운 덱 출력
			while(true) {
				if(index1==end1) break;
				System.out.printf(cards[index1]+" ");
				
				if(index2==end2) break;
				System.out.printf(cards[index2]+" ");
				
				index1++;
				index2++;
			}
			System.out.println();
		}
	}
}
