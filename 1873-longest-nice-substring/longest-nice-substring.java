class Solution {
    public String longestNiceSubstring(String s) {
        return helper(s);
    }

    private String helper(String s) {
        if (s.length() < 2) return "";

        boolean[] lower = new boolean[26];
        boolean[] upper = new boolean[26];

        for (char c : s.toCharArray()) {
            if (Character.isLowerCase(c)) {
                lower[c - 'a'] = true;
            } else {
                upper[c - 'A'] = true;
            }
        }

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (Character.isLowerCase(c) && !upper[c - 'a'] ||
                Character.isUpperCase(c) && !lower[c - 'A']) {

                String left = helper(s.substring(0, i));
                String right = helper(s.substring(i + 1));

                return left.length() >= right.length() ? left : right;
            }
        }

        return s;
    }
}