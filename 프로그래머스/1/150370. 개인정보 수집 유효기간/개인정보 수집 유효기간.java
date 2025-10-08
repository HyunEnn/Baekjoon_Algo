import java.util.*;

class Solution {
    static HashMap<String, Integer> map = new HashMap<>();
    public int[] solution(String today, String[] terms, String[] privacies) {
        
        for(String term : terms) {
            String[] keys = term.split(" ");
            map.put(keys[0], Integer.parseInt(keys[1]));
        }
        
        String[] todays = today.split("\\.");
        int year = Integer.parseInt(todays[0]);
        int month = Integer.parseInt(todays[1]);
        int day = Integer.parseInt(todays[2]);
        
        List<Integer> list = new ArrayList<>();
        
        for(int i=0;i<privacies.length;i++) {
            String[] info = privacies[i].split(" ");
            String[] dates = info[0].split("\\.");
            
            int pv_year = Integer.parseInt(dates[0]);
            int pv_month = Integer.parseInt(dates[1]) + map.get(info[1]);
            int pv_day = Integer.parseInt(dates[2]) - 1;
            
            // 12월을 넘어가면 1로 만들고 pv_year + 1
            pv_year += (pv_month - 1) / 12;
            pv_month = (pv_month - 1) % 12 + 1;
           
            if(pv_day <= 0) {
                pv_month--;
                if(pv_month == 0) { pv_year--; pv_month = 12; }
                pv_day = 28;
            }
            // System.out.println(pv_year + " " + pv_month + " " + pv_day);
                
            if(pv_year == year) {
                if(pv_month == month) {
                    if(pv_day < day) {
                        list.add(i + 1);
                    }
                } else if(pv_month < month) {
                    list.add(i + 1);
                }
            } else if(pv_year < year) {
                list.add(i + 1);
            }
            
            // 현재 날짜보다 작으면, 그 키 값을 list에 저장한다.
            
        }
        
        Collections.sort(list);
        int[] answer = new int[list.size()];
        for(int i=0;i<list.size();i++) {
            answer[i] = list.get(i);
        }
        return answer;
    }
}