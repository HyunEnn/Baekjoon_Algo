
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static class Pair implements Comparable<Pair> {
        int idx, num;
        Pair(int idx, int num) {
            this.idx = idx;
            this.num = num;
        }

        @Override
        public int compareTo(Pair p) {
            return num - p.num;
        }
    }
    static int N, M;
    static int[][] arr;
    static StringTokenizer st;
    static HashSet<String> set = new HashSet<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int ans = 0;
        arr = new int[N][M];
        for(int i=0;i<N;i++) {
            st = new StringTokenizer(br.readLine());
            List<Pair> list = new ArrayList<>();
            for(int j=0;j<M;j++) {
                list.add(new Pair(j, Integer.parseInt(st.nextToken())));
            }
            Collections.sort(list);
            StringBuilder sb = new StringBuilder();
            int rank = 0;
            for(int j=0;j<list.size();j++) {
                if(j >= 1 && list.get(j).num != list.get(j-1).num) rank++;
                arr[i][list.get(j).idx] = rank;
            }

//            for(int j=0;j<M;j++) {
//                System.out.print(arr[i][j] + " ");
//            }
        }

        // arr 을 2차원 배열로 각 자리별 랭크를 설정하였다.
        // 설정된 2차원 배열을 조합을 기준으로 생각한다.
        for(int i=0;i<N;i++) {
            for(int j=i+1;j<N;j++) {
                boolean flag = false;
                for(int k=0;k<M;k++) {
                    // 값이 다르면 같은 차원이 아니니 패스
                    if(arr[i][k] != arr[j][k]) {
                        flag = true;
                        continue;
                    }
                }
                if(!flag) ans++;
            }
        }

        System.out.println(ans);
    }
}
