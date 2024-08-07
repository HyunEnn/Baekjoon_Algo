import java.util.*;

class Solution {
    static class Pair implements Comparable<Pair> {
        int score;
        float failure;

        Pair(int score, float failure) {
            this.score = score;
            this.failure = failure;
        }

        @Override
        public int compareTo(Pair p) {
            if (Float.compare(this.failure, p.failure) == 0) {
                return Integer.compare(this.score, p.score);
            }
            return Float.compare(p.failure, this.failure); // 내림차순 정렬
        }
    }

    public int[] solution(int N, int[] stages) {
        int[] nowStage = new int[N + 1]; // 스테이지에 도달했으나 클리어 못한 유저의 수
        int[] totalUsers = new int[N + 1]; // 도달한 전체 유저의 수

        // 스테이지에 도달했으나 클리어 못한 유저의 수 계산
        for (int stage : stages) {
            if (stage <= N) {
                nowStage[stage]++;
            }
        }

        // 총 유저 수를 스테이지별로 계산
        int remainingUsers = stages.length;
        for (int i = 1; i <= N; i++) {
            totalUsers[i] = remainingUsers;
            remainingUsers -= nowStage[i];
        }

        // 실패율을 기준으로 Pair 리스트를 생성
        List<Pair> list = new ArrayList<>();
        for (int i = 1; i <= N; i++) {
            float failureRate = totalUsers[i] > 0 ? (float) nowStage[i] / totalUsers[i] : 0;
            list.add(new Pair(i, failureRate));
        }

        // 실패율을 기준으로 내림차순 정렬
        Collections.sort(list);

        // 결과 배열 생성
        int[] answer = new int[N];
        for (int i = 0; i < N; i++) {
            answer[i] = list.get(i).score;
        }

        return answer;
    }
}
