import java.util.*;

class Solution {
    static List<String> list = new ArrayList<>();
    public int solution(int cacheSize, String[] cities) {
        int answer = 0;
        // 일단 초기값을 넣자
        list.add(cities[0].toLowerCase());
        answer += 5;
        for(int i=1;i<cities.length;i++) {
            boolean flag = false;
            if(cacheSize == 0) {
                answer += 5;
                continue;
            }
            if(list.size() >= cacheSize) {
                for(int j=0;j<list.size();j++) {
                    if(list.get(j).equals(cities[i].toLowerCase())) {
                        list.add(list.get(j).toLowerCase());
                        list.remove(j);
                        answer += 1;
                        flag = true;
                        break;
                    }
                } // 다 돌았을 때 없으면, 바뀌었으면 flag 체크
                if(!flag) {
                    list.remove(0);
                    list.add(cities[i].toLowerCase());
                    answer += 5;      
                }

            } else { // list가 가득 안찻을 경우
                for(int j=0;j<list.size();j++) {
                    if(list.get(j).equals(cities[i].toLowerCase())) {
                        list.add(list.get(j).toLowerCase());
                        list.remove(j);
                        answer += 1;
                        flag = true;
                        break;
                    }
                }
                if(!flag) {
                    list.add(cities[i].toLowerCase());
                    answer += 5;    
                }


            }

            // for(String s : list) {
            //     System.out.print(s + " " + answer);
            // } System.out.println();
        }
        return answer;
    }
}