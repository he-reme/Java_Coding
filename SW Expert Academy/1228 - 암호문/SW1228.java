import java.io.*;
import java.util.LinkedList;
import java.util.List;
public class SW1228 {

	public static List<Integer> psw = new LinkedList<>();
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		for(int test_case=1; test_case<=1; test_case++) {
			psw.clear();
			
			// 1. 원본 패스워드 입력
			int length = Integer.parseInt(br.readLine()); // 원본 암호문의 길이
			String[] origin = br.readLine().split(" "); // 원본 암호문 
			for(int i=0; i<length; i++) {
				int num = Integer.parseInt(origin[i]);
				psw.add(num);
			}
			
			// 2. 명령문 입력
			int orderNum = Integer.parseInt(br.readLine()); // 명령어 개수
			String[] order = br.readLine().split(" "); // 명령어 나열한 문자열들
			
			// 3. 명령문 반영
			int index = 1; // 명령문 인덱스
			while(true) {
				if(orderNum==0)
					break;
				
				int addIdx = Integer.parseInt(order[index++]); // 추가할 인덱스
				int addCnt = Integer.parseInt(order[index++]); // 추가할 암호 갯수
				
				for(int i=0; i<addCnt; i++) {
					int num = Integer.parseInt(order[index++]);
					psw.add(addIdx, num);
					addIdx++; // 추가할 인덱스는 앞에 계속 한개씩 추가되므로 1개씩 뒤로 밀려나야 함
				}
				orderNum--;
				index++;
			}
		
			System.out.printf("#%d ", test_case);
			for(int i=0; i<10; i++) 
				System.out.printf("%d ", psw.get(i));
			System.out.println();
		}
		br.close();
	}
}
