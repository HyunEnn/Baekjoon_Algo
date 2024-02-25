class Solution {
    public int solution(int[][] sizes) {
        int answer = 0;
        int arrX = sizes[0][0]; // 60
        int arrY = sizes[0][1]; // 50
        for(int i=1;i<sizes.length;i++) {
            int checkFirstX = 0;
            int checkFirstY = 0;
            int checkLastX = 0;
            int checkLastY = 0;
            if(sizes[i][0] > arrX || sizes[i][1] > arrY) {
                if(sizes[i][0] > arrX)
                    checkFirstX = sizes[i][0] - arrX;
                if(sizes[i][1] > arrY) 
                    checkFirstY = sizes[i][1] - arrY;
                
                int tmp = sizes[i][0];
                sizes[i][0] = sizes[i][1];
                sizes[i][1] = tmp;
                System.out.println(sizes[i][0] + " " + sizes[i][1]);
                System.out.println("arrX : " + arrX + ", arrY : " + arrY);
                
                if(sizes[i][0] > arrX || sizes[i][1] > arrY) {
                    if(sizes[i][0] > arrX)
                        checkLastX = sizes[i][0] - arrX;
                    if(sizes[i][1] > arrY) 
                        checkLastY = sizes[i][1] - arrY;
                }
                if(checkFirstX + checkFirstY > checkLastX + checkLastY) {
                    arrX += checkLastX;
                    arrY += checkLastY;
                }
                else {
                    arrX += checkFirstX;
                    arrY += checkFirstY;
                }
                System.out.println("arrX : " + arrX + ", arrY : " + arrY);
            }
        }
        answer = arrX * arrY;
        return answer;
    }
}