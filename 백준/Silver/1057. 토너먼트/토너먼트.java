
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, A, B;
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        Deque<Integer> Q = new ArrayDeque<>();
        Queue<Integer> Q2 = new ArrayDeque<>();
        for(int i=1;i<=N;i++) {
            Q.offer(i);
        }

        boolean flag = false;
        int cnt = 1;
        while(true) {
            while(!Q.isEmpty()) {
                int x = Q.poll();
                // 여기서 Q가 있으면 비교해서 Q2에 넣는다
                if(!Q.isEmpty()) {
                    int y = Q.poll();
                    // 둘 다 A, B라면 바로 종료
                    if((x == A || x == B) && (y == A || y == B)) {
                        flag = true;
                        break;
                    }

                    if(x == A || x == B) Q2.offer(x);
                    else if(y == A || y == B) Q2.offer(y);
                    else Q2.offer(x);
                }
                // 없으면 부전승 처리로 Q2 에 바로 삽입
                else {
                    Q2.offer(x);
                }
            }
            if(flag) break;
            else {
                cnt++;
                Q = new ArrayDeque<>(Q2);
                Q2.clear();
            }
        }

        System.out.println(cnt);
    }
}
