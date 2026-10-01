import java.util.*;

class Solution {
    static char[][] map;
    static int[] dr = {0, 1, 1, 1};
    static int[] dc = {1, 1, 0, -1};
    public int solution(String[] board) {
        map = new char[board.length][board[0].length()];
        int answer = 1;
        // 0. 우선 O와 X의 갯수 체크 
        int correct = 0, fail = 0;
        for(int i=0;i<3;i++) {
            for(int j=0;j<3;j++) {
                map[i][j] = board[i].charAt(j);
                if(map[i][j] == 'O') correct++;
                else if(map[i][j] == 'X') fail++;
            }
        }
        if(correct == 0 && fail == 0) return 1;
        // 우선 O와 X의 갯수가 X가 더 많으면 무조건 불가능 판정
        if(fail > correct) return 0;
        // O가 빙고가 만들어졌는지 체크한다. ( 3 x 3 , 빙고 )
        boolean correct_flag = solve('O');  
        boolean fail_flag = solve('X');
        // 둘 다 빙고면 절대 불가능
        if(correct_flag && fail_flag) return 0;
        // 그 외에 각각 빙고인 경우 계산
        if(correct_flag) {
            if(correct == fail + 1) return 1;
            else return 0;
        }
        if(fail_flag) {
            if(correct == fail) return 1;
            else return 0;
        }
        
        if(correct > fail + 1) return 0;
        
        return answer;
    }
    
    public static boolean solve(char c) {
        for(int i=0;i<3;i++) {
            for(int j=0;j<3;j++) {
                if(map[i][j] == c) {
                    for(int k=0;k<4;k++) {
                        int t = 1;
                        while(true) {
                            int nr = i + dr[k] * t;
                            int nc = j + dc[k] * t;
                            if(nr < 0 || nr > 2 || nc < 0 || nc > 2) break;

                            if(map[nr][nc] == c) t++;
                            else break;
                        }
                        if(t == 3) return true;
                    }
                }
            }
        }
        
        return false;
    }
}
