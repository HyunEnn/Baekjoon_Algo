class Solution {
    public String solution(int a, int b) {
        String answer = "";
        String[] defaultDate = {"SUN","MON","TUE","WED","THU","FRI","SAT"};
        // 1 ~ 12월 까지 각 1일의 요일은?
        String[] date = {"FRI", "MON", "TUE", "FRI", "SUN", "WED", "FRI", "MON", 
                         "THU", "SAT", "TUE", "THU"};
        int[] day = {31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        // a가 월, b가 일
        String startDate = date[a-1]; // 배열은 0부터 시작 ex) 5월 -> date[4]
        // 5월 1일은 일요일
        int curr = 0;
        for(int i=0;i<defaultDate.length;i++) {
            if(defaultDate[i].equals(startDate)) 
                curr = i;
        } // 7의 배수를 더한 값이 현재와 같아야 함
        // b = 22면, 일요일이 되야하는데... 2
        curr = (curr + (b - 1)) % 7;
        
        return defaultDate[curr];
    }
}