import java.util.*;

class Solution {
  static ArrayList[] list;
    
    public int solution(int n, int[][] wires) {
        int answer = Integer.MAX_VALUE;
        list = new ArrayList[n+1];
        for(int i=1; i<=n; i++){
          list[i] = new ArrayList<Integer>();
        }
        for(int i=0;i<wires.length;i++) {
          list[wires[i][0]].add(wires[i][1]);
          list[wires[i][1]].add(wires[i][0]);
        }

        for(int i=0;i<wires.length;i++) {
          int a = wires[i][0];
          int b = wires[i][1];
          // 현재 값을 remove 하고, 다시 복원하면서 가능한 전선의 갯수가 최대한 비슷한 경우를 탐색
          list[a].remove(Integer.valueOf(b));
          list[b].remove(Integer.valueOf(a));

          int cnt = bfs();
          int diff = Math.abs(n - cnt - cnt);
          answer = Math.min(answer, diff);
          // 복원
          list[a].add(b);
          list[b].add(a);
        }
        return answer;
    }

    private static int bfs() {
      Queue<Integer> Q = new ArrayDeque<>();
      Q.offer(1);
    
      int count = 1;
      boolean[] visited = new boolean[list.length + 1];
        visited[1] = true;
      // Implementation for BFS
      while(!Q.isEmpty()) {
        int p = Q.poll();
        for(int i=0;i<list[p].size();i++) {
          int next = (int)list[p].get(i);
          if(!visited[next]) {
            visited[next] = true;
            count++;
            Q.offer(next);
          }
        }
      }
      return count;
    }
}