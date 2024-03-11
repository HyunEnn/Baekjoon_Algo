class Solution {
    public int solution(int a, int b, int n) {
        int answer = 0;
        while(n >= a) {
            int remain = n % a;
            answer += (n / a) * b; // 20 / 2 * 1 = 10
            // System.out.println("answer : " + answer);
            n = (n/a)*b + remain; // n은 10으로 치환    
            // System.out.println("n : " + n);
        }
        return answer;
    }
}
// a = 줘야 하는 병수, b = 바꿔주는 병수 
// 빈 병을 2개에 콜라 1병 
// 빈 병 20개 -> 콜라 10병 -> 다 마신 10병 -> 콜라 5병 -> 다 마신 콜라 5병 -> 콜라 2병 -> 1병
