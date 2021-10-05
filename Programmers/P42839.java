import java.util.*;
public class P42839 {

	public static int answer = 0; // 답
	public static String[] numberList; // 한자리 숫자 리스트
	public static int[] visited; // 사용하고 있는 숫자인지 체크
	public static Map<Integer, Integer> map = new HashMap<>(); // 소수임을 체크한 숫자 저장하여 중복 확인 방지
	
	public static void main(String[] args) {
		solution("333");
	}
	
	private static int solution(String numbers) {
        
		numberList = numbers.split("");
        visited = new int[numberList.length];
        map.put(1,  1);
        
        makeNumber(0, "");
        return answer;
    }

	/* 한자리 숫자 조합하여 숫자 만들기 */
	private static void makeNumber(int len, String num) {
		
		// 만든 숫자가 소수인지 확인
		if(checkPrimeNumber(num))
			answer++;
		
		// 한자리 숫자들을 모두 사용했을 경우
		if(len==numberList.length)
			return;
				
		// 숫자를 조합으로 만듦
		for(int i=0; i<numberList.length; i++) {
			if(visited[i]==1)
				continue;
			
			visited[i] = 1;
			makeNumber(len+1, num + numberList[i]);
			visited[i] = 0;
		}	
	}
	
	/* 만든 숫자가 소수인지 확인 */
	private static boolean checkPrimeNumber(String num) {
		String tmp = new String(num);
		
		// step1. 0 제거
		while(true) {
			if(tmp.equals(""))
				return false;
			
			if(tmp.charAt(0)=='0')
				tmp = tmp.substring(1);
			else
				break;
		}

			
		// step2. 이전에 확인했던 숫자인지 확인
		int number = Integer.parseInt(tmp);
		if(map.get(number)==null)
			map.put(number, 1);
		else
			return false;
		
		// step3. 소수인지 확인
		for(int i=2; i<number; i++) {
			if(number%i==0)
				return false;
		}
		return true;
	}
}
