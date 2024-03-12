import java.util.*;

class Solution {
    public boolean solution(String[] phone_book) {
        boolean answer = true;
        Arrays.sort(phone_book);
        
        for (int i = 0; i < phone_book.length - 1; i++) {
            if (phone_book[i + 1].startsWith(phone_book[i])) {
                // 현재 번호가 다음 번호의 접두사인 경우
                answer = false;
                break;
            }
        }
        // for(int i=0;i<phone_book.length-1;i++) {
        //     String start = phone_book[i];
        //     for(int j=i+1;j<phone_book.length;j++) {
        //         if(phone_book[j].startsWith(start)) {
        //             answer = false;
        //             return answer;
        //         }
        //     }
        // }
        
        return answer;
        
    }
}
// 12, 123, 1235, 567, 88