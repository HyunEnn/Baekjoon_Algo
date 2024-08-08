class Solution {
    static int leftR = 3;
    static int leftC = 0;
    static int rightR = 3;
    static int rightC = 2;
    static int[][] loc;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    public String solution(int[] numbers, String hand) {
        // 1, 3, 4는 왼손 , 3, 6, 9는 오른손을 사용
        // 2, 5, 8, 0은 둘 중 가까운 손을 사용
        String answer = "";
        loc = new int[4][3]; // 핸드폰 문자열

        for(int i=0;i<numbers.length;i++) {
            if(numbers[i] == 1 || numbers[i] == 4 || numbers[i] == 7) {
                locateHand(numbers[i]);
                answer += "L";
            }
            else if(numbers[i] == 3 || numbers[i] == 6 || numbers[i] == 9){
                locateHand(numbers[i]);
                answer += "R";
            }

            else  { // 현재 왼손과 오른손 위치를 통해 2,5,8,0과 가까운 값을 비교한다.
                answer += compare(numbers[i], hand);
            }

        }
        return answer;
    }

    public static String compare(int num, String hand) {
        if(num == 2) { // (0,1)
            int r = 0; int c = 1;
            int left = Math.abs(leftR - r) + Math.abs(leftC - c);
            int right = Math.abs(rightR - r) + Math.abs(rightC - c);
            if(left == right) { // 같을 경우, 손잡이에 따라 처리
                if(hand.equals("right")) {
                    rightR = r; rightC = c;
                    return "R";
                } else {
                    leftR = r; leftC = c;
                    return "L";
                }
            } else if(left > right) {
                rightR = r; rightC = c;
                return "R";
            } else {
                leftR = r; leftC = c;
                return "L";
            }
        } else if(num == 5) {
            int r = 1; int c = 1;
            int left = Math.abs(leftR - r) + Math.abs(leftC - c);
            int right = Math.abs(rightR - r) + Math.abs(rightC - c);
            if(left == right) { // 같을 경우, 손잡이에 따라 처리
                if(hand.equals("right")) {
                    rightR = r; rightC = c;
                    return "R";
                } else {
                    leftR = r; leftC = c;
                    return "L";
                }
            } else if(left > right) {
                rightR = r; rightC = c;
                return "R";
            } else {
                leftR = r; leftC = c;
                return "L";
            }
        } else if(num == 8) {
            int r = 2; int c = 1;
            int left = Math.abs(leftR - r) + Math.abs(leftC - c);
            int right = Math.abs(rightR - r) + Math.abs(rightC - c);
            if(left == right) { // 같을 경우, 손잡이에 따라 처리
                if(hand.equals("right")) {
                    rightR = r; rightC = c;
                    return "R";
                } else {
                    leftR = r; leftC = c;
                    return "L";
                }
            } else if(left > right) {
                rightR = r; rightC = c;
                return "R";
            } else {
                leftR = r; leftC = c;
                return "L";
            }
        } else {
            int r = 3; int c = 1;
            int left = Math.abs(leftR - r) + Math.abs(leftC - c);
            int right = Math.abs(rightR - r) + Math.abs(rightC - c);
            if(left == right) { // 같을 경우, 손잡이에 따라 처리
                if(hand.equals("right")) {
                    rightR = r; rightC = c;
                    return "R";
                } else {
                    leftR = r; leftC = c;
                    return "L";
                }
            } else if(left > right) {
                rightR = r; rightC = c;
                return "R";
            } else {
                leftR = r; leftC = c;
                return "L";
            }
        }

    }

    public static void locateHand(int num) {
        if(num == 1) {
            leftR = 0; leftC = 0; 
        }
        else if(num == 4) {
            leftR = 1; leftC = 0;
        }
        else if(num == 7) {
            leftR = 2; leftC = 0;
        }
        else if(num == 3) {
            rightR = 0; rightC = 2;
        }
        else if(num == 6) {
            rightR = 1; rightC = 2;
        }
        else if(num == 9) {
            rightR = 2; rightC = 2;
        }
    }
}