class Solution {
    static boolean[] v;
    static int max;
    public int solution(int k, int[][] dungeons) {
        int answer = -1;
        max = 0;
        // System.out.println(dungeons.length);
        v = new boolean[dungeons.length];
        DFS(k, 0, dungeons);
        return max;
    }

    public static void DFS(int k, int cnt, int[][] dungeons) {
        max = Math.max(max, cnt);

        for(int i=0;i<dungeons.length;i++) {
            if(!v[i] && k >= dungeons[i][0]) {
                v[i] = true;
                DFS(k - dungeons[i][1], cnt + 1, dungeons);
                v[i] = false;
            }
        }
    }

}