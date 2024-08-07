class Solution {
    public String solution(String new_id) {
        // 1단계: 대문자를 소문자로 변환
        new_id = firstPolicy(new_id);
        // 2단계: 허용된 문자만 남기기
        new_id = secondPolicy(new_id);
        // 3단계: 연속된 '.'을 하나로 줄이기
        new_id = thirdPolicy(new_id);
        // 4단계: 문자열의 처음과 끝에 있는 '.' 제거
        new_id = fourthPolicy(new_id);
        // 5단계: 빈 문자열이면 "a"로 대체
        new_id = fifthPolicy(new_id);
        // 6단계: 길이가 15자를 초과하면 15자까지 자르기
        new_id = sixPolicy(new_id);
        // 7단계: 길이가 2자 이하이면 길이를 3으로 맞추기
        new_id = sevenPolicy(new_id);

        return new_id;
    }

    // 1단계
    public static String firstPolicy(String new_id) {
        return new_id.toLowerCase();
    }

    // 2단계
    public static String secondPolicy(String new_id) {
        StringBuilder sb = new StringBuilder();
        for (char c : new_id.toCharArray()) {
            if (Character.isLetterOrDigit(c) || c == '-' || c == '_' || c == '.') {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    // 3단계
    public static String thirdPolicy(String new_id) {
        StringBuilder sb = new StringBuilder();
        char lastChar = '\0';
        for (char c : new_id.toCharArray()) {
            if (c != '.' || lastChar != '.') {
                sb.append(c);
                lastChar = c;
            }
        }
        return sb.toString();
    }

    // 4단계
    public static String fourthPolicy(String new_id) {
        if (new_id.length() > 0 && new_id.charAt(0) == '.') {
            new_id = new_id.substring(1);
        }
        if (new_id.length() > 0 && new_id.charAt(new_id.length() - 1) == '.') {
            new_id = new_id.substring(0, new_id.length() - 1);
        }
        return new_id;
    }

    // 5단계
    public static String fifthPolicy(String new_id) {
        return new_id.length() == 0 ? "a" : new_id;
    }

    // 6단계
    public static String sixPolicy(String new_id) {
        if (new_id.length() > 15) {
            return new_id.substring(0, 15).replaceAll("\\.$", ""); // 끝의 '.' 제거
        }
        return new_id;
    }

    // 7단계
    public static String sevenPolicy(String new_id) {
        while (new_id.length() < 3) {
            new_id += new_id.charAt(new_id.length() - 1);
        }
        return new_id;
    }
}
