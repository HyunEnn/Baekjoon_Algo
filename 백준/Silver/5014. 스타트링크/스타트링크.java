
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int F, S, G, U, D;
    static int[] stairs;
    static boolean[] v;
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        F = Integer.parseInt(st.nextToken()); // 최고 층
        S = Integer.parseInt(st.nextToken()); // 현재 층
        G = Integer.parseInt(st.nextToken()); // 목표 층
        U = Integer.parseInt(st.nextToken()); // 위로 진행 가능
        D = Integer.parseInt(st.nextToken()); // 아래로 진행 가능

        stairs = new int[F+1];
        for(int i=1;i<=F;i++) {
            stairs[i] = -1;
        }
        v = new boolean[F+1];

        simulate();

        if(stairs[G] == -1)
            System.out.println("use the stairs");
        else
            System.out.println(stairs[G]);
    }

    public static void simulate() {
        Queue<Integer> Q = new ArrayDeque<>();
        Q.offer(S); // 현재 층 시작
        stairs[S] = 0;
        while(!Q.isEmpty()) {
            int curr = Q.poll();
            if(curr + U <= F && stairs[curr + U] == -1) {
                stairs[curr + U] = stairs[curr] + 1;
                Q.offer(curr + U);
            }
            if(curr - D >= 1 && stairs[curr - D] == -1) {
                stairs[curr - D] = stairs[curr] + 1;
                Q.offer(curr - D);
            }
            // -1인 경우에는 접근 가능하고, 아니면 패스
        }
    }
}
