class Solution {
    static char[][] b;
    static boolean[][] v;
    public int solution(int m, int n, String[] board) {
        int answer = 0;
        b = new char[m][n];
        
        for(int i=0;i<board.length;i++) {
            for(int j=0;j<board[i].length();j++) {
                b[i][j] = board[i].charAt(j);
            }
        }
        // System.out.println(findBlock(m, n));
        // moveBlock(m, n);
        while(true) {
            int cnt = findBlock(m, n);
            answer += cnt;
            if(cnt == 0) break;
            // 블럭 밀기
            moveBlock(m, n);
        }
        return answer;
    }
    
    public static void moveBlock(int m, int n) {
        for(int j=0;j<n;j++) {
            int ch = 0;
            for(int i=m-1;i>=0;i--) {
                if(b[i][j] == '0') ch++;
                else {
                    if(ch != 0) {
                        b[i+ch][j] = b[i][j];
                        b[i][j] = '0';
                    }
                }
            }
        }
    }
    
    public static int findBlock(int m, int n) {
        int ch = 0;
        v = new boolean[m][n];
        for(int i=0;i<m-1;i++) {
            for(int j=0;j<n-1;j++) {
                if(b[i][j] != '0' &&
                b[i][j] == b[i][j+1] &&
                b[i][j] == b[i+1][j] &&
                b[i][j] == b[i+1][j+1]) {
                    v[i][j] = true;
                    v[i][j+1] = true;
                    v[i+1][j] = true;
                    v[i+1][j+1] = true;
                }
            }
        }
        ch += checkTrue(m, n);
        return ch;
    }
    
    public static int checkTrue(int m, int n) {
        int ch = 0;
        for(int i=0;i<m;i++) {
            for(int j=0;j<n;j++) {
                if(v[i][j]) {
                    b[i][j] = '0';
                    ch++;
                }
            }
        }
        return ch;
    }
}