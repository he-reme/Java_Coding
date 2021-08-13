import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

public class BJ2961 {

	public static int answer = Integer.MAX_VALUE;
	public static int N; // 재료 개수
	public static List<Ingredient> ingredient = new LinkedList<>(); // 재료 정보
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine()); 
		
		int sour, bitter;
		for(int n=0; n<N; n++) {
			String[] info = br.readLine().split(" ");
			sour = Integer.parseInt(info[0]);
			bitter = Integer.parseInt(info[1]);
			ingredient.add(new Ingredient(sour, bitter));
		}
		
		subCombination(0, 1, 0);

		System.out.println(answer);
	}

	// 부분조합 뽑기
	private static void subCombination(int index, int total_sour, int total_bitter) {
		
		// 재료가 1개라도 선택 됐으면
		if(total_bitter!=0) {
			int sub = Math.abs(total_sour - total_bitter);
			answer = answer<sub ? answer:sub;
		}

		if(index==N)
			return;
		
		int	sour = ingredient.get(index).sour * total_sour;
		int	bitter = ingredient.get(index).bitter + total_bitter;

		// 해당 재료를 선택하는 경우
		subCombination(index+1, sour, bitter);
			
		// 해당 재료를 선택하지 않는 경우
		subCombination(index+1, total_sour, total_bitter);
	}
}

class Ingredient {
	int sour, bitter;

	public Ingredient(int sour, int bitter) {
		this.sour = sour;
		this.bitter = bitter;
	}	
}
