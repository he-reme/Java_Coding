import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class JO1828 {

	public static Temperature[] c;
	public static void main(String[] args) throws Exception {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		c = new Temperature[N];
		
		int low, high;
		for(int i=0; i<N; i++) {
			String[] info = br.readLine().split(" ");
			low = Integer.parseInt(info[0]);
			high = Integer.parseInt(info[1]);
			c[i] = new Temperature(low, high);
			
		}
		
		// 최고 보관 온도의 올림차순으로 정렬
		Arrays.sort(c);
		
		// 답 구하기
		int answer = 1; // 냉장고는 무조건 1대 이상
		int temp = c[0].high;
		for(int i=1; i<N; i++) {
			
			// 냉장고를 새로 추가하지 않아도 되는 경우
			if(temp >= c[i].low && temp <= c[i].high)
				continue;
			
			// 냉장고를 새로 추가해야 하는 경우
			temp = c[i].high;
			answer++;
			
		}
		
		System.out.println(answer);
	}

}

class Temperature implements Comparable<Temperature> {
	int low, high;

	public Temperature(int low, int high) {
		super();
		this.low = low;
		this.high = high;
	}

	@Override
	public int compareTo(Temperature o) {
		int value = this.high - o.high;
		if(value!=0)
			return value;
		return this.low - o.low;
	}
	
	
}
