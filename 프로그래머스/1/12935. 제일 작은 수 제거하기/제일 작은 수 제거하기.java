class Solution {
    public int[] solution(int[] arr) {
        // 배열 길이가 1인 경우 -1로 반환하고 끝
        if (arr.length==1) {
            int[] answer_one = {-1};
            return answer_one;
        }

        // 가장작은 수 하나 뺴야하니까 총 배열 길이에서 -1함
        int[] answer = new int[arr.length-1];
        
        int min = Integer.MAX_VALUE;
        for (int i=0; i<arr.length; i++){
            min = Math.min(min, arr[i]);
        }
        int answer_num = 0;
        for (int j=0; j< arr.length; j++){
            if (arr[j]==min){
                continue;
            }
            else{
                answer[answer_num++] = arr[j];
            }
        }
        return answer;
    }
}