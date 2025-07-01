import java.util.*;

class Solution {
    static class Point {
        String uid, name;
        int num;
        Point(String uid, int num) {
            this.uid = uid;
            this.num = num;
        }
    }
    static List<Point> list = new ArrayList<>();
    static HashMap<String, String> map = new HashMap<>();
    public String[] solution(String[] record) {
        for(int i=0;i<record.length;i++) {
            String[] rec = record[i].split(" ");
            if(rec[0].equals("Enter")) {
                list.add(new Point(rec[1], 0));
                map.put(rec[1], rec[2]);
            } 
            else if(rec[0].equals("Leave")) {
                list.add(new Point(rec[1], 1));
            } 
            else if(rec[0].equals("Change")) {
                map.put(rec[1], rec[2]);
            }
        }
        
        String[] answer = new String[list.size()];
        for(int i=0;i<answer.length;i++) {
            Point p = list.get(i);
            StringBuilder sb = new StringBuilder();
            if(p.num == 0) 
                sb.append(map.get(p.uid)).append("님이 들어왔습니다.");
            else 
                sb.append(map.get(p.uid)).append("님이 나갔습니다.");
            answer[i] = sb.toString();    
        }
        return answer;
    }
}