
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static class Point {
        int x, y, z;

        Point(int x, int y, int z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }
    }

    static int A, B, C;
    static int max;
    static boolean[][][] v;
    static HashSet<Integer> set = new HashSet<>();
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        v = new boolean[A + 1][B + 1][C + 1];
        max = A + B + C;
        bfs();

        List<Integer> list = new ArrayList<>(set);
        list.sort((a, b) -> a - b);
        for (int i : list) {
            System.out.print(i + " ");
        }
    }

    public static void bfs() {
        Queue<Point> Q = new ArrayDeque<>();
        Q.offer(new Point(0, 0, C)); // 처음엔 C가 가득 차있음
        v[0][0][C] = true;

        while (!Q.isEmpty()) {
            Point p = Q.poll();

            if (p.x == 0) set.add(p.z);

            for (int i = 0; i < 6; i++) {
                // c-a, c-b, b-a, b-c, a-b, a-c
                Point now = pour(p, i);
                if (!v[now.x][now.y][now.z]) {
//                    System.out.println("현재 진행중인 값 : " + now.x + " " + now.y + " " + now.z);
                    v[now.x][now.y][now.z] = true;
                    Q.offer(new Point(now.x, now.y, now.z));
                }
            }
        }
    }

    public static Point pour(Point p, int idx) {
        int a = p.x, b = p.y, c = p.z;
        int curr = 0;
        switch (idx) {
            case 0: // c -> a
                curr = A - a;
                if(curr > 0 && c > 0) {
                    int next = Math.min(curr, c);
                    a += next;
                    c -= next;
                }
                break;
            case 1: // c -> b
                curr = B - b;
                if (curr > 0 && c > 0) {
                    int next = Math.min(curr, c);
                    b += next;
                    c -= next;
                }
                break;
            case 2: // b -> a
                curr = A - a;
                if(curr > 0 && b > 0) {
                    int next = Math.min(curr, b);
                    a += next;
                    b -= next;
                }
                break;
            case 3: // b -> c
                curr = C - c;
                if(curr > 0 && b > 0) {
                    int next = Math.min(curr, b);
                    c += next;
                    b -= next;
                }
                break;
            case 4: // a -> b
                curr = B - b;
                if(curr > 0 && a > 0) {
                    int next = Math.min(curr, a);
                    a -= next;
                    b += next;
                }
                break;
            case 5: // a -> c
                curr = C - c;
                if(curr > 0 && a > 0) {
                    int next = Math.min(curr, a);
                    a -= next;
                    c += next;
                }
                break;
        }

        return new Point(a, b, c);
    }
}
