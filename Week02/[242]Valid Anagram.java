//Given two strings s and t , write a function to determine if t is an anagram o
//f s. 
//
// Example 1: 
//
// 
//Input: s = "anagram", t = "nagaram"
//Output: true
// 
//
// Example 2: 
//
// 
//Input: s = "rat", t = "car"
//Output: false
// 
//
// Note: 
//You may assume the string contains only lowercase alphabets. 
//
// Follow up: 
//What if the inputs contain unicode characters? How would you adapt your soluti
//on to such case? 
// Related Topics Hash Table Sort


//leetcode submit region begin(Prohibit modification and deletion)
/*
首先判断两个字符串长度是否相等，不相等则直接返回 false
若相等，则初始化 26 个字母哈希表，遍历字符串 s 和 t
s 负责在对应位置增加，t 负责在对应位置减少
如果哈希表的值都为 0，则二者是字母异位词

时间复杂度O(n)，空间复杂度O(1)
 */
class Solution {
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        int[] alpha = new int[26];
        for (int i = 0; i < s.length(); i++) {
            alpha[s.charAt(i)-'a']++;
            alpha[t.charAt(i)-'a']--;
        }
        for (int i = 0; i < 26; i++) {
            if(alpha[i]!=0) {
                return false;
            }
        }
        return true;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
