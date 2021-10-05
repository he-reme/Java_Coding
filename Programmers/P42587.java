import java.util.*;
public class P42587 {
	public int solution(int[] priorities, int location) {
        int answer = 0;
        int len = priorities.length;
        
        Integer[] sort_priorities = new Integer[len]; // 내림차순 정렬된 우선순위
        Queue<Integer> print = new LinkedList<>(); // 인쇄 큐
        
        for(int i=0; i<len; i++) {
            sort_priorities[i] = priorities[i];
            print.offer(priorities[i]);
        }
        
         Arrays.sort(sort_priorities, Collections.reverseOrder());
        
        int p = 0; // 현재 처리해야 할 중요도의 인덱스
        while(!print.isEmpty()) {
            int now_priority = print.poll(); // 현재 인쇄 대기상태의 문서 중요도
            
         // 현재 처리해야 할 중요도에 맞는 문서인 경우
            if(now_priority==sort_priorities[p]) {
                answer++;
                
                if(location==0)
                    break;
                
                location--;
                p++;
            }
         // 현재 처리해야 할 중요도에 맞지 않는 문서인 경우
            else {
                if(location==0)
                    location = print.size();
                else
                    location--;
                
                print.offer(now_priority);
            }
        }
        return answer;
    }
}
