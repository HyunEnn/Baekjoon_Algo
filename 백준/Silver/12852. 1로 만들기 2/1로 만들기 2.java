
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static class Pair {
        int idx, cnt;
        List<Integer> ls = new ArrayList<>();
        Pair(int idx, int cnt, List<Integer> ls) {
            this.idx = idx;
            this.cnt = cnt;
            this.ls = new ArrayList<>(ls);
            this.ls.add(idx);
        }
    }
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        Queue<Pair> Q = new ArrayDeque<>();
        boolean[] v = new boolean[N+1];
        v[N] = true;
        Q.offer(new Pair(N, 0, new ArrayList<>()));
        int ans = Integer.MAX_VALUE;
        List<Integer> list = new ArrayList<>();
        while(!Q.isEmpty()) {
            Pair curr = Q.poll();
            if(curr.idx <= 0) continue;

            if(curr.idx == 1 && ans > curr.cnt) {
                ans = curr.cnt;
                list = new ArrayList<>(curr.ls);
                break;
            }
            for(int i=0;i<3;i++) {
                switch(i) {
                    case 0: if(curr.idx % 3 == 0 && !v[curr.idx / 3]) {
                        v[curr.idx / 3] = true;
                        Q.offer(new Pair(curr.idx / 3, curr.cnt + 1, curr.ls));
                    }
                    break;
                    case 1: if(curr.idx % 2 == 0 && !v[curr.idx / 2]) {
                        v[curr.idx / 2] = true;
                        Q.offer(new Pair(curr.idx / 2, curr.cnt + 1, curr.ls));
                    }
                    break;
                    case 2:
                        if(!v[curr.idx - 1]) {
                            v[curr.idx - 1] = true;
                            Q.offer(new Pair(curr.idx - 1, curr.cnt + 1, curr.ls));
                        }
                    break;
                }
            }
        }

        System.out.println(ans);
        StringBuilder sb = new StringBuilder();
        for(int i : list) {
            sb.append(i).append(" ");
        }

        System.out.println(sb.toString());
    }
}
