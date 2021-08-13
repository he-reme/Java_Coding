import java.util.Scanner;
import java.util.stream.IntStream;

public class BJ3040 {

	public static int[] dwarf = new int[9];
	public static int sum = 0;
	public static int answer = 0; // 정답을 찾으면 1이 됨
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		
		for(int i=0; i<9; i++)
			dwarf[i] = sc.nextInt();
	
		sum = IntStream.of(dwarf).sum();
		
		combination(0, 0, sum, 0);
	}
	
	private static void combination(int index, int cnt, int num, int flag) {
		
		// 난쟁이가 아닌 애 2명만 뽑으면 됨
		if(cnt==2) {
			if(num==100) {
				// 출력
				StringBuilder br = new StringBuilder();
				for(int i=0; i<9; i++) {
					if((flag & 1<<i) !=0) continue;
					br.append(dwarf[i]).append("\n");
				}
				System.out.println(br.toString());
				answer = 1;
				
			}
			return;
		}
		
		if(answer==1 || index==9)
			return;
		
		// 해당 난쟁이를 골랐을 때
		combination(index+1, cnt+1, num-dwarf[index], flag | 1<<index );
	
		// 해당 난쟁이를 고르지 않았을 때
		combination(index+1, cnt, num, flag);
		
	}
}

