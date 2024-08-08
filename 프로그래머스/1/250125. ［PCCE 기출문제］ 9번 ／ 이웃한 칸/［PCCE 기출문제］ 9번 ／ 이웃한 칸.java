class Solution {
    static int dh_size;
    static int dw_size;
    public int solution(String[][] board, int h, int w) {
        int count = 0;
        int[] dh = {0, 1, -1, 0};
        int[] dw = {1, 0, 0, -1};
        dh_size = board.length;
        dw_size = board[0].length;
        String s = board[h][w];
        System.out.println(s);
        for(int i=0;i<4;i++) {
            int nh = h + dh[i];
            int nw = w + dw[i];
            if(canGo(h, w, nh, nw, board)) 
                count++;
        }
        return count;
    }
    
    public static boolean inRange(int h, int w) {
        return h >= 0 && h < dh_size && w >= 0 && w < dw_size;
    }
    
    public static boolean canGo(int h, int w, int nh, int nw, String[][] board) {
        if(!inRange(nh, nw)) 
            return false;
        if(board[h][w].equals(board[nh][nw]))
            return true;
        return false;
    }
}