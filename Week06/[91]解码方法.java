//一条包含字母 A-Z 的消息通过以下方式进行了编码： 
//
// 'A' -> 1
//'B' -> 2
//...
//'Z' -> 26
// 
//
// 给定一个只包含数字的非空字符串，请计算解码方法的总数。 
//
// 示例 1: 
//
// 输入: "12"
//输出: 2
//解释: 它可以解码为 "AB"（1 2）或者 "L"（12）。
// 
//
// 示例 2: 
//
// 输入: "226"
//输出: 3
//解释: 它可以解码为 "BZ" (2 26), "VF" (22 6), 或者 "BBF" (2 2 6) 。
// 
// Related Topics 字符串 动态规划 
// 👍 440 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
/*
与爬楼梯同一类型，加了很多限制条件
时间复杂度O(N)
空间复杂度O(N)
 */
class Solution {
    Map<Integer, Integer> map = new HashMap<>();

    public int numDecodings(String s) {
        return helper(s, 0);
    }

    public int helper(String s, int pos) {
        if (pos == s.length()) {
            return 1;
        }
        if (map.containsKey(pos)) {
            return map.get(pos);
        }

        // handle the first character
        int first = s.charAt(pos) - '0';
        if (first == 0) {
            return 0;
        }
        int count1 = helper(s, pos + 1);

        // handle the second character
        int count2 = 0;
        if (pos + 1 < s.length()) { // make sure that the length is greater than 2
            int second = s.charAt(pos + 1) - '0';
            if (first * 10 + second <= 26) { // the first - second charcater combined should be less than 26
                count2 = helper(s, pos + 2);
            }
        }
        map.put(pos, count1 + count2); // memoization
        return count1 + count2;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
