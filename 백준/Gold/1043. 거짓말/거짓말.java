
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, M;
    static int[] roots;
    static boolean[] v;
    static int[] arr;
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N+1];
        v = new boolean[N+1];
        Arrays.fill(arr, -1);
        st = new StringTokenizer(br.readLine());
        int s_root = Integer.parseInt(st.nextToken());
        roots = new int[s_root];
        for(int i=0;i<s_root;i++) {
            roots[i] = Integer.parseInt(st.nextToken());
        }
        // 초기, 유파
        for(int i=1;i<s_root;i++) {
            uni(roots[0], roots[i]);
        }

        // 파티별 유파 체크
        List<int[]> parties = new ArrayList<>();
        for(int i=0;i<M;i++) {
            st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken());
            int[] tmp = new int[num];
            for(int j=0;j<num;j++) {
                tmp[j] = Integer.parseInt(st.nextToken());
            }
            parties.add(tmp);
            for(int j=1;j<num;j++) {
                uni(tmp[j-1], tmp[j]);
            }
        }
        
        // 갱신된 배열에 루트 표시
        for(int i=0;i<s_root;i++) {
            int root = find(roots[i]);
            v[root] = true;
        }

        // 이제, 파티별 참석 가능여부 확인
        int answer = 0;
        for(int[] party : parties) {
            boolean flag = false;
            for(int p : party) {
                int root = find(p);
                if(v[root]) {
                    flag = true;
                    continue;
                }
            }
            if(!flag) answer++;
        }

        System.out.println(answer);

//        for(int i=1;i<=N;i++) {
//            System.out.print(v[i] + " ");
//        }
    }

    private static int find(int x) {
        if(arr[x] < 0) return x;
        return arr[x] = find(arr[x]);
    }

    private static boolean uni(int u, int v) {
        u = find(u);
        v = find(v);
        if(u == v) return false;
        arr[v] = u;
        return true;
    }
}
/**
 * 사람수 N, 파티수 M
 * 진실을 아는 사람의 수 , 진실을 아는 사람의 번호
 * 파티마다 오는 사람의 수, 오는 사람의 번호
 *
 * 거짓말쟁이로 알려지지 않으면서, 과장된 이야기를 할 수 있는 파티 개수의 최대값을 구하기
 * union-find 를 통해서, 파티별 유파를 진행하고, 다시 파티를 순회하면서
 * 진실을 아는 루트 그룹, 다른 파티에서 진실을 알게 되는 그룹이 아예 없는 파티에서만 거짓말을 하는 최대 갯수 구하기
 */