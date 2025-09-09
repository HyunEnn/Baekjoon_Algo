
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int T;
    static Deque<Integer> list;
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            // 방향값 
            String direction = br.readLine();
            // 배열의 갯수
            int n = Integer.parseInt(br.readLine());
            list = new ArrayDeque<>();
            String input = br.readLine();
            if (n != 0) {
                input = input.substring(1, input.length() - 1);
                String[] arr = input.split(",");
                for (String s : arr) {
                    list.add(Integer.parseInt(s));
                }
            }
            boolean flag = false;
            boolean reverse = false;
            for (int i = 0; i < direction.length(); i++) {
                if (direction.charAt(i) == 'R') {
                    reverse = !reverse; // 뒤집지 말고, 표시만
                } else if (direction.charAt(i) == 'D') {
                    if (list.isEmpty()) {
                        flag = true;
                        break;
                    }
                    // 뒤집음 표시가 있으면, 마지막 값 제거
                    if (reverse) list.removeLast();
                    // 아니라면, 기존의 맨 앞 제거
                    else list.removeFirst();
                }
            }

            if (flag) System.out.println("error");
            else {
                StringBuilder sb = new StringBuilder();
                sb.append("[");
                // 리스트가 안 비었을때만 진행
                if(!list.isEmpty()) {
                    // 만약에 뒤집은 상태로 뽑는거라면
                    if(reverse) {
                        while(list.size() > 1) {
                            sb.append(list.removeLast()).append(",");
                        }
                    } else {
                        while(list.size() > 1) {
                            sb.append(list.removeFirst()).append(",");
                        }
                    }

                    sb.append(list.remove());
                }
                sb.append("]");
                System.out.println(sb.toString());
            }
        }
    }
}
