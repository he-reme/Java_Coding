import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

public class SW5215 {

	public static int answer;
	public static List<Ingredient> ig; // 재료 정보
	public static int N, L; // 재료 갯수, 칼로리 제한

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for(int test_case=1; test_case<=T; test_case++) {
			
			// N, L 입력받기
			String[] limit = br.readLine().split(" ");
			N = Integer.parseInt(limit[0]);
			L = Integer.parseInt(limit[1]);
			
			// 재료 정보 입력 받기
			ig = new LinkedList();
			for(int i=0; i<N; i++) {
				String[] info = br.readLine().split(" ");
				int score = Integer.parseInt(info[0]);
				int calorie = Integer.parseInt(info[1]);
				ig.add(new Ingredient(score, calorie));
			}
			
			// 정답 0으로 초기화
			answer = 0;
			
			// dfs를 통해 재료 선택
			choose_ingredient(0, 0, 0);
			
			System.out.printf("#%d %d%n", test_case, answer);
		}

	}
	
	/*  
	 *  dfs를 통해 재료 선택
	 *  params : pScore(현재 점수), pCalorie(현재 칼로리), start(재료 선택 시작 인덱스)
	 */
	private static void choose_ingredient(int index, int pScore, int pCalorie) {

		if(pCalorie>L)
			return;
		
		if(index==N) {
			answer = answer > pScore ? answer : pScore;
			return;
		}
		
		// index번째 재료를 선택 안하고 쭉 간다
		choose_ingredient(index+1, pScore, pCalorie);
		
		// index번째 재료를 선택하고 쭉 간다
		choose_ingredient(index+1, pScore+ig.get(index).score, pCalorie+ig.get(index).calorie);
	}
}

// 재료 정보 클래스
class Ingredient {
	int score;
	int calorie;
	public Ingredient(int score, int calorie) {
		this.score = score;
		this.calorie = calorie;
	}
	
}
