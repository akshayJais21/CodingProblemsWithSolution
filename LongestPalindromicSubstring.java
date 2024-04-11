// https://leetcode.com/problems/longest-palindromic-substring/description/
class Solution {
 static String answer = "";
    int max = 1;

    public static String longestPalindrome(String s) {
        System.out.println(answer);
        for(int i = 0 ; i < s.length(); i++){
            lengthOfLongPalindrome(s,i);
        }
        String c = answer;
        answer = "";

        return c;
    }

    private static void lengthOfLongPalindrome(String s, int i) {
        /// 3 possibilties i is middle or not
        calclength(i,i,s) ;
        calclength(i,i+1,s) ;
        calclength(i-1,i,s) ;


    }

    private static boolean inBounds(int a, int b, String s) {
        if( a>= 0 && a <= s.length()-1 && b >= 0 && b <= s.length()-1)
            return true;
        return false;
    }

    public static void calclength(int a, int b, String s){
        while (inBounds(a,b,s) && s.charAt(a) == s.charAt(b)){
            if(s.substring(a,b+1).length() > answer.length())
                answer = s.substring(a,b+1);
            a--;
            b++;
        }


    }
}
