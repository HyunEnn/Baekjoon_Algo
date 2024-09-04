
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static Deque<Integer> deque = new ArrayDeque<>();
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        for(int i=0;i<N;i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            switch(a) {
                case 1: a = 1;
                    int b = Integer.parseInt(st.nextToken());
                    deque.addFirst(b);
                    break;
                case 2: a = 2;
                    int c = Integer.parseInt(st.nextToken());
                    deque.addLast(c);
                    break;
                case 3: a = 3;
                    if(!deque.isEmpty())
                        System.out.println(deque.poll());
                    else
                        System.out.println(-1);
                    break;
                case 4:a = 4;
                    if(!deque.isEmpty())
                        System.out.println(deque.removeLast());
                    else
                        System.out.println(-1);
                    break;
                case 5: a = 5;
                    System.out.println(deque.size());
                    break;
                case 6: a = 6;
                    if(deque.isEmpty())
                        System.out.println(1);
                    else
                        System.out.println(0);
                    break;
                case 7: a = 7;
                    if(!deque.isEmpty())
                        System.out.println(deque.peek());
                    else
                        System.out.println(-1);
                    break;
                case 8: a = 8;
                    if(!deque.isEmpty())
                        System.out.println(deque.peekLast());
                    else
                        System.out.println(-1);
                    break;
            }
        }
    }
}
