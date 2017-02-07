package longestpalindrome;


public class LongestPalindrome {


    public static void main(String[] args) {
        Palindrome p = new Palindrome();
        String s = "abba";

        System.out.println(p.longestPalindrome(s));
    }


    private static class Palindrome {

        public String longestPalindrome(String str) {
            if (str.isEmpty()) {
                return null;
            }
            if (str.length() == 1) {
                return str;
            }

            String longestPalindrome = str.substring(0, 1);

            for (int i = 0; i < str.length(); i++) {
                // Odd length check - "aba"
                String tmp = findPalindrome(str, i, i);
                if (tmp.length() > longestPalindrome.length()) {
                    longestPalindrome = tmp;
                }
                // Even length check - "abba"
                tmp = findPalindrome(str, i, i + 1);
                if (tmp.length() > longestPalindrome.length()) {
                    longestPalindrome = tmp;
                }
            }

            return longestPalindrome;
        }

        public String findPalindrome(String str, int left, int right) {
            while (left >= 0 && right <= str.length() - 1 && str.charAt(left) == str.charAt(right)) {
                left--;
                right++;
            }
            return str.substring(left + 1, right);
        }
    }

}
