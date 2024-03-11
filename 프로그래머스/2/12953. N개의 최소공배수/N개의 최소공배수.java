class Solution {
    public int solution(int[] arr) {
        int answer = arr[0];
        for(int i=1;i<arr.length;i++) {
            answer = LCM(answer, arr[i]);
        }
        
        return answer;
    }
    
    public static int LCM(int a, int b) {
        return (a*b) / GCD(a, b);
    }
    
    public static int GCD(int a, int b) {
        if(b == 0) return a;
        return GCD(b, a % b);
    } 
} // 8 14 -> 14, 8 -> 8, 6 -> 6, 2 -> 2, 0 
// 최대공약수를 구한 다음에, (a * b) / gcd(a, b)를 해주면 된다?
// 5 7 이면, 35 / gcd(5, 7)
// 2, 6, 8, 14 => 2 * 1 3 4 7 = 24*7 = 168