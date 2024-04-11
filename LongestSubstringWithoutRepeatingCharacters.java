// https://leetcode.com/problems/longest-substring-without-repeating-characters/
// Given a string s, find the length of the longest 
// substring
//  without repeating characters.

 

// Example 1:

// Input: s = "abcabcbb"
// Output: 3
// Explanation: The answer is "abc", with the length of 3.
// Example 2:

// Input: s = "bbbbb"
// Output: 1
// Explanation: The answer is "b", with the length of 1.
// Example 3:

// Input: s = "pwwkew"
// Output: 3
// Explanation: The answer is "wke", with the length of 3.
// Notice that the answer must be a substring, "pwke" is a subsequence and not a substring.
 

// Constraints:

// 0 <= s.length <= 5 * 104
// s consists of English letters, digits, symbols and spaces.

// Seen this question in a real interview before?
// 1/5
// Yes
// No
// Accepted
// 5.6M
// Submissions
// 16.3M
// Acceptance Rate
// 34.7%
// Topics
// Companies
// Similar Questions
// Discussion (308)

// Choose a type





















// Read more






// Copyright ©️ 2024 LeetCode All rights reserved

class Solution {
    int maximum = 0;

    public int lengthOfLongestSubstring(String s) {
        
        HashMap<Character, Integer> hashMap = new HashMap<Character, Integer>();
        
        Queue<Character> queue = new LinkedList<>();
        for (Character character : s.toCharArray()){
            
            
            queue.add(character);
            if(hashMap.containsKey(character))
                hashMap.put(character, hashMap.get(character)+1);
            else 
                hashMap.put(character,1);
            
            //check in map for uniqueness
            boolean unique = checkForUnique(hashMap);
            if(unique && maximum < queue.size())
                maximum = queue.size();
            
            if(!unique) {
                while (true) {
                    Character c = queue.remove();
                    if (hashMap.get(c) == 1)
                        hashMap.remove(c);
                    else
                        hashMap.put(c, hashMap.get(c) - 1);
                    if (checkForUnique(hashMap))
                        break;
                }
            }
        }
        return maximum;
    }

    private boolean checkForUnique(HashMap<Character, Integer> hashMap) {
        boolean flag = true;
        for(Map.Entry<Character,Integer> entry : hashMap.entrySet()){
            if(entry.getValue() > 1 )
            {
                flag = false;
                break;
            }
        }
    
    return flag;
    
    }
}
