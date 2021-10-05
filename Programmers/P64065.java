import java.util.*;

public class P64065 {
	public int[] solution(String s) {
        
        
        // idea. 등장 횟수가 많을수록 앞에 먼저 오는 숫자
        Map<Integer, Integer> map = new HashMap<>();
        
        // step1. 괄호 없애기
        s = s.replace("{", "");
        s = s.replace("}", "");
        
        // step2. ','를 구분자로 토크나이즈
        String[] str_numbers = s.split(",");
        for(int i=0; i<str_numbers.length; i++) {
        	int number = Integer.parseInt(str_numbers[i]);
        	
        	if(map.get(number)==null)
        		map.put(number, 1);
        	else
        		map.put(number, map.get(number) + 1);	
        }
        
        int[] answer = new int[map.size()];
        List<Integer> keySetList = new ArrayList<>(map.keySet());
        // 내림차순
        Collections.sort(keySetList, new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				return map.get(o2).compareTo(map.get(o1));
			}
        });
        int index = 0;
        for(Integer key : keySetList) {
        	answer[index++] = key;
        }
        return answer;
    }
}

// key 정렬 : TreeMap 사용
/*
 TreeMap<Integer, Integer> map = new TreeMap<>();
 Iterator<Integer> itKey = map.descendingKeySet().iterator(); //내림차순
 int index = 0;
 while (itKey.hasNext())
 {
     int key = itKey.next();
     answer[index++] = map.get(key);
 }
*/
// value 정렬 : Collections.sort 사용
