import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static int cnt;
    static int T;
    static int[] trueP;
    static ArrayList<Integer>[] party;
    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        T = Integer.parseInt(st.nextToken());

        trueP = new int[T];
        for (int i = 0; i < T; i++) {
            trueP[i] = Integer.parseInt(st.nextToken());
        }

        party = new ArrayList[M];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            party[i] = new ArrayList<>();
            int x = Integer.parseInt(st.nextToken());
            for (int j = 0; j < x; j++) {
                int a = Integer.parseInt(st.nextToken());
                party[i].add(a);
            }
        }

        parent = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            parent[i] = i;
        }

        // 진실을 아는 사람들을 union
        for (int i = 0; i < M; i++) {
            if (party[i].size() > 1) {
                int first = party[i].get(0);
                for (int j = 1; j < party[i].size(); j++) {
                    union(first, party[i].get(j));
                }
            }
        }

        cnt = 0;
        // 각 파티에 대해 진실을 아는 사람이 있는지 확인
        for (int i = 0; i < M; i++) {
            boolean isPossible = true;
            for (int j = 0; j < trueP.length; j++) {
                if (find(trueP[j]) == find(party[i].get(0))) {
                    isPossible = false;
                    break;
                }
            }
            if (isPossible) {
                cnt++;
            }
        }

        System.out.println(cnt);
    }

    static void union(int a, int b) {
        a = find(a);
        b = find(b);
        if (a != b) {
            if (a > b) {
                parent[a] = b;
            } else {
                parent[b] = a;
            }
        }
    }

    static int find(int idx) {
        if (idx == parent[idx])
            return idx;
        else {
            parent[idx] = find(parent[idx]);
            return parent[idx];
        }
    }
}
