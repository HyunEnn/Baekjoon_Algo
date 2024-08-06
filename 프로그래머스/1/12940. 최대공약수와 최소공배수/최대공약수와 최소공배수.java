class Solution {
    static int gcd = 0, lcd = 0;
    public int[] solution(int n, int m) {
        int[] answer = new int[2];
        // 최대공약수
        GCD(n, m);
        // 최소공배수
        LCD(n, m);
        answer[0] = gcd;
        answer[1] = lcd;
        return answer;
    }

    public static void LCD(int n, int m) {
        if(n % m == 0 || m % n == 0) {
            lcd = Math.max(n, m);
        } // 나눠 떨어지면 둘 중 큰 값이 최소공배수
        else {
            int idx = Math.max(n, m);
            while(true) {
                idx++;
                if(idx % n == 0 && idx % m == 0) {
                    lcd = idx;
                    break;
                }
            }
        }
    }
    
    public static void GCD(int n, int m) {
        if(n % m == 0 || m % n == 0) {
            // 둘 중 나누어 떨어지면 가장 작은게 최대공약수
            gcd = Math.min(n, m);
        }
        else {
            int idx = 1;
            int max = Math.min(n, m);
            while(idx <= max) {
                if(n % idx == 0 && m % idx == 0) 
                    gcd = idx;
                idx++;
            }
        }
    }
}
