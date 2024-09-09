
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, M;
    static class People implements Comparable<People> {
        int num, cnt, curr;
        People(int num, int cnt, int curr) {
            this.num = num;
            this.cnt = cnt;
            this.curr = curr;
        }
        @Override
        public int compareTo(People p) {
            if(this.cnt == p.cnt)
                return this.curr - p.curr;
            return this.cnt - p.cnt;
        }
    }
    static List<People> peoples = new ArrayList<>();
    static List<Integer> answers = new ArrayList<>();
    static int[] arr;
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        arr = new int[M];
        st = new StringTokenizer(br.readLine());
        for(int i=0;i<M;i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        for(int i=0;i<M;i++) {
            // 값이 안 찼으면, peoples 에 값이 있으면 업데이트, 없으면 추가
            if(peoples.size() < N) {
                boolean flag = false;
                for(int j=0;j<peoples.size();j++) {
                    People p = peoples.get(j);
                    if(p.num == arr[i]) {
                        peoples.set(j, new People(arr[i], p.cnt + 1, p.curr));
                        flag = true;
                    }
                }
                if(!flag)
                    peoples.add(new People(arr[i], 1, i));
            }
            else {
                // 값이 가득 찼다면
                boolean flag = false;
                for(int j=0;j<peoples.size();j++) {
                    People p = peoples.get(j);
                    if(p.num == arr[i]) {
                        int cnt = p.cnt;
                        peoples.set(j, new People(arr[i],cnt + 1, p.curr));
                        flag = true;
                    }
                }
                // 기존의 값을 건드리지 않았다면, 앞에꺼 삭제 후 뒤에 추가
                if(!flag) {
                    peoples.remove(0);
                    peoples.add(new People(arr[i], 1, i));
                }
            }
            Collections.sort(peoples);
//            System.out.println("현재 횟수 : " + i);
//            for(People p : peoples) {
//                System.out.print(p.num + " " + p.cnt + " " + p.curr + " , ");
//            }
//            System.out.println();
        }

        for(People p : peoples) {
            answers.add(p.num);
        }
        Collections.sort(answers);
        for(int i : answers) {
            System.out.print(i + " ");
        }
    }
}
