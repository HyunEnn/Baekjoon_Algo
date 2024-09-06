
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static class KeyBoard {
        char key;
        int r, c;

        KeyBoard(char key, int r, int c) {
            this.key = key;
            this.r = r;
            this.c = c;
        }
    }

    static List<KeyBoard> keyBoards = new ArrayList<>();
    static char[] leftHand;
    static char[] rightHand;
    static String l, r;
    static int lr, lc, rr, rc;
    static int copyLr, copyLc, copyRr, copyRc;
    static String findKey;
    static int moveLen;
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 기본 키보드 입력에 대한 값들과 위치 선정
        keyBoards.add(new KeyBoard('q', 0, 0));
        keyBoards.add(new KeyBoard('w', 0, 1));
        keyBoards.add(new KeyBoard('e', 0, 2));
        keyBoards.add(new KeyBoard('r', 0, 3));
        keyBoards.add(new KeyBoard('t', 0, 4));
        keyBoards.add(new KeyBoard('y', 0, 5));
        keyBoards.add(new KeyBoard('u', 0, 6));
        keyBoards.add(new KeyBoard('i', 0, 7));
        keyBoards.add(new KeyBoard('o', 0, 8));
        keyBoards.add(new KeyBoard('p', 0, 9));

        keyBoards.add(new KeyBoard('a', 1, 0));
        keyBoards.add(new KeyBoard('s', 1, 1));
        keyBoards.add(new KeyBoard('d', 1, 2));
        keyBoards.add(new KeyBoard('f', 1, 3));
        keyBoards.add(new KeyBoard('g', 1, 4));
        keyBoards.add(new KeyBoard('h', 1, 5));
        keyBoards.add(new KeyBoard('j', 1, 6));
        keyBoards.add(new KeyBoard('k', 1, 7));
        keyBoards.add(new KeyBoard('l', 1, 8));

        keyBoards.add(new KeyBoard('z', 2, 0));
        keyBoards.add(new KeyBoard('x', 2, 1));
        keyBoards.add(new KeyBoard('c', 2, 2));
        keyBoards.add(new KeyBoard('v', 2, 3));
        keyBoards.add(new KeyBoard('b', 2, 4));
        keyBoards.add(new KeyBoard('n', 2, 5));
        keyBoards.add(new KeyBoard('m', 2, 6));

        st = new StringTokenizer(br.readLine());
        l = st.nextToken().toLowerCase(); // 왼손
        r = st.nextToken().toLowerCase(); // 오른손
        leftHand = new char[]{'q', 'w', 'e', 'r', 't', 'a', 's', 'd', 'f', 'g', 'z', 'x', 'c', 'v'};
        rightHand = new char[]{'y', 'u', 'i', 'o', 'p', 'h', 'j', 'k', 'l', 'b', 'n', 'm'};
        for (int i = 0; i < keyBoards.size(); i++) {
            KeyBoard keyBoard = keyBoards.get(i);
            if (keyBoard.key == l.charAt(0)) { // 왼손 기준 위치 찾기
                lr = keyBoard.r;
                lc = keyBoard.c;
            } else if (keyBoard.key == r.charAt(0)) { // 오른손 기준 위치 찾기
                rr = keyBoard.r;
                rc = keyBoard.c;
            }
        }
        findKey = br.readLine(); // 입력해야 하는 키
        moveLen = 0;

        for (int i = 0; i < findKey.length(); i++) {
            char c = findKey.charAt(i);
            // 여기서, 왼손과 오른손 어느 탐색이 필요할지 체크하고 진행한다.
            for (int j = 0; j < leftHand.length; j++) {
                if (c == leftHand[j]) {
                    moveLen += calculateLeft(c);
                }
            }

            for (int j = 0; j < rightHand.length; j++) {
                if (c == rightHand[j]) {
                    moveLen += calculateRight(c);
                }
            }

            moveLen++; // 누르는 것에 대한 1초 증가
        }

        System.out.println(moveLen);
    }

    public static int calculateRight(char c) {
        int move = 0;
        for (int i = 0; i < keyBoards.size(); i++) {
            KeyBoard keyBoard = keyBoards.get(i);
            if(keyBoard.key == c) {
                move = Math.abs(rr - keyBoard.r) + Math.abs(rc - keyBoard.c);
                rr = keyBoard.r;
                rc = keyBoard.c;
                break; // 찾았으니 시간복잡도 감소를 위한 멈춤
            }
        }
        return move;
    }


    public static int calculateLeft(char c) {
        int move = 0;
        for (int i = 0; i < keyBoards.size(); i++) {
            KeyBoard keyBoard = keyBoards.get(i);
            if (keyBoard.key == c) {
                move = Math.abs(lr - keyBoard.r) + Math.abs(lc - keyBoard.c);
                lr = keyBoard.r;
                lc = keyBoard.c;
                break; // 찾았으니 시간복잡도 감소를 위한 멈춤
            }
        }
        return move;
    }
}
