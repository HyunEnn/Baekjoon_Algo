class Solution {
    public String solution(int[] food) {
        String answer = "";
        StringBuilder sb = new StringBuilder();
        for(int i=1;i<food.length;i++) {
            if(food[i] / 2 > 0) {
                int rec = food[i] / 2;
                for(int j=0;j<rec;j++) {
                    sb.append(i);
                }
            }
        }
        sb.append(0); // 왼쪽 주자 끝
        for(int i=food.length-1;i>0;i--) {
            if(food[i] / 2 > 0) {
                int rec = food[i] / 2;
                for(int j=0;j<rec;j++) {
                    sb.append(i);
                }
            }
        }
        
        System.out.println(sb.toString());
        
        // food 는 4가 고정임
        return sb.toString();
        
    }
}