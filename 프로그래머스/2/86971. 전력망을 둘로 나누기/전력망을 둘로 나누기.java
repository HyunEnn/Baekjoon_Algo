import java.util.*;

class Solution {
    static ArrayList<Integer>[] list;
    static boolean[] v;
    public int solution(int n, int[][] wires) {
        int answer = Integer.MAX_VALUE;
        list = new ArrayList[n+1];
        for(int i=1;i<=n;i++) {
            list[i] = new ArrayList<>();
        }
        
        for(int i=0;i<wires.length;i++) {
            int f = wires[i][0];
            int l = wires[i][1];
            list[f].add(l);
            list[l].add(f);
        }
        
        // 경우의 수를 기반으로 선을 끊었을 때, 2개의 경우로 나뉘는지
        for(int i=0;i<wires.length;i++) {
            int s = wires[i][0];
            int e = wires[i][1];
            list[s].remove(Integer.valueOf(e));
            list[e].remove(Integer.valueOf(s));
            
            v = new boolean[n+1];
            v[1] = true;
            int graph = recursive(1); // 1번 노드 탐색
            int graph2 = 0;
            // for(int j=1;j<=n;j++) {
            //     System.out.print(v[j] + " ");
            // }
            // System.out.println();
            // 탐색 후에 v가 하나라도 false있으면 그 노드를 기반으로 탐색
            for(int j=1;j<=n;j++) {
                if(!v[j]) {
                    v[j] = true;
                    graph2 = recursive(j);
                }
            }
            // System.out.println(graph + " " + graph2);
            answer = Math.min(answer, Math.abs(graph - graph2));
            // 끝난 후에 되돌리기
            list[s].add(e);
            list[e].add(s);
        }
        return answer;
    }
    
    public static int recursive(int idx) {
        Queue<Integer> Q = new ArrayDeque<>();
        Q.offer(idx);
        int cnt = 0;
        
        while(!Q.isEmpty()) {
            int p = Q.poll();
            cnt++;
            for(int i : list[p]) {
                if(!v[i]) {
                    v[i] = true;
                    Q.offer(i);
                }
            }
        }
        return cnt;
    }
}