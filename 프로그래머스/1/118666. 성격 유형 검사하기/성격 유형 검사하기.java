class Solution {
    static int[] RT = new int[2];
    static int[] CF = new int[2];
    static int[] JM = new int[2];
    static int[] AN = new int[2];
    public String solution(String[] survey, int[] choices) {
        String answer = "";
        for(int i=0;i<choices.length;i++) {
            char c = survey[i].charAt(1);
            if(survey[i].equals("RT") || survey[i].equals("TR"))
                // 여기서 5점이면, 뒤에값에 +1 , 4보다 크면 %4를 한 값을 뒤 배열에 저장하고
                // 작으면 앞의 배열에 %4 한 값을 저장해주자.
                incPer(choices[i], c);
            else if(survey[i].equals("CF") || survey[i].equals("FC"))
                incCF(choices[i], c);
            else if(survey[i].equals("JM") || survey[i].equals("MJ"))
                incJM(choices[i], c);
            else if(survey[i].equals("AN") || survey[i].equals("NA"))
                incAN(choices[i], c);
        }
        
        System.out.println(RT[0] + " " + RT[1]);
        System.out.println(CF[0] + " " + CF[1]);
        System.out.println(JM[0] + " " + JM[1]);
        System.out.println(AN[0] + " " + AN[1]);
        String last = comparePerson();
        return last;
    }
    
    public String comparePerson() {
        StringBuilder sb = new StringBuilder();
        if(RT[0] == RT[1])
            sb.append("R");
        else {
            if(RT[0] > RT[1])
                sb.append("R");
            else
                sb.append("T");
        }
        if(CF[0] == CF[1])
            sb.append("C");
        else {
            if(CF[0] > CF[1])
                sb.append("C");
            else
                sb.append("F");
        }
        if(JM[0] == JM[1])
            sb.append("J");
        else {
            if(JM[0] > JM[1])
                sb.append("J");
            else
                sb.append("M");
        }
        if(AN[0] == AN[1])
            sb.append("A");
        else {
            if(AN[0] > AN[1])
                sb.append("A");
            else
                sb.append("N");
        }
        return sb.toString();
    }
    
    public static void incAN(int ch, char c) {
        if(ch > 4) {
            if(c == 'N')
                AN[1] += ch%4;
            else
                AN[0] += ch%4;
        } else if(ch == 4) AN[0] += 0;
        else {
            if(c == 'N') // ch가 1 = 3, 2 = 2, 3 = 1
                if(ch == 1) AN[0] += 3;
                else if(ch == 3) AN[0] += 1;
                else AN[0] += ch;
            else
                if(ch == 1) AN[1] += 3;
                else if(ch == 3) AN[1] += 1;
                else AN[1] += ch;
        }
    }
    
    public static void incJM(int ch, char c) {
        if(ch > 4) {
            if(c == 'M')
                JM[1] += ch%4;
            else
                JM[0] += ch%4;
        } else if(ch == 4) JM[0] += 0;
        else {
            if(c == 'M')
                if(ch == 1) JM[0] += 3;
                else if(ch == 3) JM[0] += 1;
                else JM[0] += ch;
            else
                if(ch == 1) JM[1] += 3;
                else if(ch == 3) JM[1] += 1;
                else JM[1] += ch;
        }
    }
    
    public static void incCF(int ch, char c) {
        if(ch > 4) {
            if(c == 'F')
                CF[1] += ch%4;
            else
                CF[0] += ch%4;
        } else if(ch == 4) CF[0] += 0;
        else {
            if(c == 'F')
                if(ch == 1) CF[0] += 3;
                else if(ch == 3) CF[0] += 1;
                else CF[0] += ch;
            else
                if(ch == 1) CF[1] += 3;
                else if(ch == 3) CF[1] += 1;
                else CF[1] += ch;
        }
    }

    public static void incPer(int ch, char c) {
        if(ch > 4) {
            if(c == 'T')
                RT[1] += ch%4;
            else
                RT[0] += ch%4;
        } else if(ch == 4) RT[0] += 0;
        else {
            if(c == 'T')
                if(ch == 1) RT[0] += 3;
                else if(ch == 3) RT[0] += 1;
                else RT[0] += ch;
            else
                if(ch == 1) RT[1] += 3;
                else if(ch == 3) RT[1] += 1;
                else RT[1] += ch;
        }
    }
}