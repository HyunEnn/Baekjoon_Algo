class Solution {
    public String solution(String s) {
        char[] c = s.toCharArray();
        StringBuilder sb = new StringBuilder();
        for(int i=1;i<c.length;i++) {
            for(int j=0;j<c.length-1;j++) {
                if((int)c[j] < (int)c[j+1]) {
                    char tmp = c[j+1];
                    c[j+1] = c[j];
                    c[j] = tmp;
                }
            }
        }
        
        for(int i=0;i<c.length;i++) {
            sb.append(c[i]);
        }
        return sb.toString();
    }
}