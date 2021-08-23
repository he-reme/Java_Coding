import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class BJ1759 {

	public static int L, C; // 암호 길이, 문자의 종류 갯수
	public static char[] alphas; // 사용할 수 있는 문자 종류
	
	public static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// L, C 입력
		String[] info = br.readLine().split(" ");
		L = Integer.parseInt(info[0]);
		C = Integer.parseInt(info[1]);
		
		// 사용할 수 있는 문자 종류 입력
		String str = br.readLine().replace(" ", "");
		alphas = str.toCharArray();
		Arrays.sort(alphas);

		char[] password = new char[L];
		make_password(0, 0, 0, 0, password);
		
		System.out.println(sb);
	}
	
	// 암호 만들기
	/* start : 다음 탐색할 인덱스, len : 현재까지 만든 암호 길이, vowel : 모음 갯수, 
	   consonant : 자음 갯수, password : 현재까지 만든 암호 */
	private static void make_password(int start, int len, int vowel, int consonant, char[] password) {
		if(len==L) {
			// 최소 모음, 자음 조건을 만족하면
			if(vowel>=1 && consonant>=2) {
				Arrays.sort(password);
				sb.append(password).append("\n");
			}
			return;
		}
		
		for(int i=start; i<C; i++) {
			
			password[len] = alphas[i];
			
			// 해당 문자가 모음인 경우
			if(alphas[i]=='a' || alphas[i]=='e' || alphas[i]=='i' 
					|| alphas[i]=='o' || alphas[i]=='u')	
				make_password(i+1, len+1, vowel+1, consonant, password);
	
			// 해당 문자가 자음인 경우
			else 
				make_password(i+1, len+1, vowel, consonant+1, password);
		}
	}

}
