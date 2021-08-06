import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class SW1225 {

	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
		for(int test_case=1; test_case<=10; test_case++) {
			int T = Integer.parseInt(br.readLine());
			Queue<Integer> queue = new LinkedList<>();
			String[] line = br.readLine().split(" ");
			
			for(int i=0; i<line.length; i++) {
				int num = Integer.parseInt(line[i]);
				queue.offer(num);
			}
			
			int cycle = 1;
			while(true) {
				int n = queue.poll();
				if(n-cycle<=0) {
					queue.offer(0);
					break;
				}
				queue.offer(n-cycle);
				cycle++;
				if(cycle==6)
					cycle = 1;
			}

			
			System.out.printf("#%d ", test_case);
			for(int num : queue) {
				System.out.print(num); 
			}
			System.out.println();
		}

	}

}

