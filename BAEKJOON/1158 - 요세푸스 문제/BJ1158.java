import java.io.*;
import java.util.*;

public class BJ1158 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		String[] info = br.readLine().split(" ");
		int N = Integer.parseInt(info[0]); // 사람 수
		int K = Integer.parseInt(info[1]); // 제거될 순서
		
		List<Integer> list = new LinkedList<>();
		for(int n=1; n<=N; n++)
			list.add(n);
		
		sb.append("<");
		int index = K-1;
		while(list.size()>0) {
			sb.append(list.get(index));
		
			list.remove(index);	
			if(list.size()==0)
				break;
			
			sb.append(", ");
			
			// 제거되면 그 자리에 다시 숫자가 채워지기 때문에, K-1 만큼 index를 더해줘야 함
			index = index + K - 1; 
			
			// index가 리스트의 인덱스를 넘어가는 경우
			// 원형으로 나열되어있기 때문에 리스트 크기만큼 빼줘야 함
			while(index>=list.size())
				index = index - list.size();
		}
		sb.append(">");
		System.out.println(sb.toString());
	}

}
