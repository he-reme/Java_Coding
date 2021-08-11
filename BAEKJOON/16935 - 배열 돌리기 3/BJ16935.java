import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

public class BJ16935 {

	public static int N, M, R; // 배열의 크기 (N, M), 연산 수(R)
	
	public static int[][] A = new int[100][100];
	public static List<Integer> opp = new LinkedList<>();
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		// N, M, R 입력
		String[] info = br.readLine().split(" ");
		N = Integer.parseInt(info[0]);
		M = Integer.parseInt(info[1]);
		R = Integer.parseInt(info[2]);
		
		// 배열 A 입력
		for(int n=0; n<N; n++) {
			info = br.readLine().split(" ");
			for(int m=0; m<M; m++)
				A[n][m] = Integer.parseInt(info[m]);
		}
		
		// 연산 입력
		info = br.readLine().split(" ");
		for(int r=0; r<R; r++)
			opp.add(Integer.parseInt(info[r]));
		
		
		// 연산 돌리기
		for(int r=0; r<R; r++) {
			
			int op = opp.get(r);
			
			// 이어진 연산이 반대인 경우 상쇄됨으로 연산하지 않아도 됨
			if(r<R-1) {
				int op2 = opp.get(r+1);
				if( (op==3 && op2==4) || (op==4 && op2==3) || (op==5 && op2==6) || (op==6 && op2==5)) {
					r = r+1;
					continue;
				}
			}
			
			// 연산
			if(op==1)
				number1();
			else if(op==2)
				number2();
			else if(op==3)
				number3();
			else if(op==4)
				number4();
			else if(op==5)
				number5();
			else if(op==6)
				number6();
		}
		
		// 출력
		for(int n=0; n<N; n++) {
			for(int m=0; m<M; m++) {
				sb.append(A[n][m]);
				sb.append(" ");
			}
			sb.append("\n");
		}
		
		System.out.println(sb.toString());
		
		br.close();
	}

	// 연산 1
	private static void number1() {
		for(int n=0; n<N/2; n++) {
			int[] tmp = A[N-1-n].clone();
			A[N-1-n] = A[n];
			A[n] = tmp;
		}
	}
	
	// 연산 2
	private static void number2() {
		for(int m=0; m<M/2; m++) {
			for(int n=0; n<N; n++) {
				int tmp = A[n][M-1-m];
				A[n][M-1-m] = A[n][m];
				A[n][m] = tmp;
			}
		}
	}
	
	// 연산 3
	private static void number3() {
		int[][] tmp = new int[100][100];
		int tmpN = M;
		int tmpM = N;
		
		for(int n=0; n<N; n++)
			for(int m=0; m<M; m++)
				tmp[m][N-1-n] = A[n][m];

		copy(tmp);
		
		N = tmpN;
		M = tmpM;
	}

	// 연산 4
	private static void number4() {
		int[][] tmp = new int[100][100];
		int tmpN = M;
		int tmpM = N;
		
		for(int n=0; n<N; n++)
			for(int m=0; m<M; m++)
				tmp[M-1-m][n] = A[n][m];

		copy(tmp);
		
		N = tmpN;
		M = tmpM;
	}
	
	// 연산 5
	private static void number5() {
		int[][] tmp = new int[100][100];
		
		// 1사분면 이동
		for(int n=0; n<N/2; n++)
			for(int m=0; m<M/2; m++)
				tmp[n][m+M/2] = A[n][m];
			
		// 2사분면 이동
		for(int n=0; n<N/2; n++)
			for(int m=M/2; m<M; m++)
				tmp[n+N/2][m] = A[n][m];
		
		// 3사분면 이동
		for(int n=N/2; n<N; n++)
			for(int m=M/2; m<M; m++)
				tmp[n][m-M/2] = A[n][m];

		// 4사분면 이동
		for(int n=N/2; n<N; n++)
			for(int m=0; m<M/2; m++)
				tmp[n-N/2][m] = A[n][m];
		
		copy(tmp);
	}
	
	// 연산 6
	private static void number6() {
		int[][] tmp = new int[100][100];
		
		// 1사분면 이동
		for(int n=0; n<N/2; n++)
			for(int m=0; m<M/2; m++)
				tmp[n+N/2][m] = A[n][m];
			
		// 2사분면 이동
		for(int n=0; n<N/2; n++)
			for(int m=M/2; m<M; m++)
				tmp[n][m-M/2] = A[n][m];
		
		// 3사분면 이동
		for(int n=N/2; n<N; n++)
			for(int m=M/2; m<M; m++)
				tmp[n-N/2][m] = A[n][m];

		// 4사분면 이동
		for(int n=N/2; n<N; n++)
			for(int m=0; m<M/2; m++)
				tmp[n][m+M/2] = A[n][m];

		copy(tmp);
	}
	
	// 배열 임시 복사
	private static void copy(int[][] tmp) {
		for(int n=0; n<100; n++)
			for(int m=0; m<100; m++)
				A[n][m] = tmp[n][m];
	}
}
