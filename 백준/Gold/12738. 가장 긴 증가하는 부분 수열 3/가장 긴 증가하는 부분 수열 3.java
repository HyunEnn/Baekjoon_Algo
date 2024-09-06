
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[] arr;
    static List<Integer> list = new ArrayList<>();
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        for(int i=0;i<N;i++) {
            if(list.isEmpty())
                list.add(arr[i]);
            else {
                if(list.get(list.size()-1) < arr[i])
                    list.add(arr[i]);
                else {
                    int left = 0;
                    int right = list.size() - 1;
                    while(left <= right) {
                        int mid = (left + right) / 2;
                        if(list.get(mid) >= arr[i])
                            right = mid - 1;
                        else
                            left = mid + 1;
                    }
                    list.set(left, arr[i]);
                }
            }
        }

        System.out.println(list.size());
    }
}
