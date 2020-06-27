//Given two integers n and k, return all possible combinations of k numbers out 
//of 1 ... n. 
//
// Example: 
//
// 
//Input: n = 4, k = 2
//Output:
//[
//  [2,4],
//  [3,4],
//  [2,3],
//  [1,2],
//  [1,3],
//  [1,4],
//]
// 
// Related Topics Backtracking


import java.util.LinkedList;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)

/**
 回溯法:它将第一个添加到组合中的数和现有的组合作为参数。 backtrack(first, curr)

若组合完成- 添加到输出中。

遍历从 first t到 n的所有整数。

将整数 i 添加到现有组合 curr中。

继续向组合中添加更多整数 :
backtrack(i + 1, curr).

将 i 从 curr中移除，实现回溯。
 */

class Solution {
    List<List<Integer>> combine = new LinkedList();
    int n;
    int k;

    public void backtrack(int first, LinkedList<Integer> curr) {
        // if the combination is done
        if (curr.size() == k)
            combine.add(new LinkedList(curr));

        for (int i = first; i < n + 1; ++i) {
            // add i into the current combination
            curr.add(i);
            // use next integers to complete the combination
            backtrack(i + 1, curr);
            // backtrack
            curr.removeLast();
        }
    }

    public List<List<Integer>> combine(int n, int k) {
        this.n = n;
        this.k = k;
        backtrack(1, new LinkedList<Integer>());
        return combine;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
