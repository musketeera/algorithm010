//给定一个非空二维矩阵 matrix 和一个整数 k，找到这个矩阵内部不大于 k 的最大矩形和。 
//
// 示例: 
//
// 输入: matrix = [[1,0,1],[0,-2,3]], k = 2
//输出: 2 
//解释: 矩形区域 [[0, 1], [-2, 3]] 的数值和是 2，且 2 是不超过 k 的最大数字（k = 2）。
// 
//
// 说明： 
//
// 
// 矩阵内的矩形区域面积必须大于 0。 
// 如果行数远大于列数，你将如何解答呢？ 
// 
// Related Topics 队列 二分查找 动态规划 
// 👍 101 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
/*
通过前缀和的方式，我们把每行的总和求得，通过前缀和的找差值最大就能找到最大矩阵了
划分左右边界，并求出在此边界下，每行的总和
通过二分法找不超过K的矩阵
 */
class Solution {
    public int maxSumSubmatrix(int[][] matrix, int k) {
        int[] sumCol = new int[matrix.length];
        int maxLessK = matrix[0][0] < k ? matrix[0][0] : Integer.MIN_VALUE;
        int pre = 0;
        int tmpSum = 0;
        for (int left = 0; left < matrix[0].length; left++) {
            Arrays.fill(sumCol, 0);
            for (int right = left; right < matrix[0].length; right++) {
                for (int i = 0; i < sumCol.length; i++) {
                    sumCol[i] += matrix[i][right];
                    if (i == 0) {
                        pre = sumCol[0];
                        tmpSum = pre;
                    } else {
                        pre = Math.max(pre + sumCol[i], sumCol[i]);
                        tmpSum = Math.max(pre, tmpSum);
                    }

                }
                if (tmpSum <= k) {
                    maxLessK = Math.max(maxLessK, tmpSum);
                    continue;
                }
                for (int i = 0; i < sumCol.length; i++) {
                    tmpSum = 0;
                    for (int j = i; j < sumCol.length; j++) {
                        tmpSum += sumCol[j];
                        if (tmpSum <= k) {
                            maxLessK = Math.max(maxLessK, tmpSum);
                        }
                    }
                }

            }
        }
        return maxLessK;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
