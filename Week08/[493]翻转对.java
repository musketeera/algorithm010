//给定一个数组 nums ，如果 i < j 且 nums[i] > 2*nums[j] 我们就将 (i, j) 称作一个重要翻转对。 
//
// 你需要返回给定数组中的重要翻转对的数量。 
//
// 示例 1: 
//
// 
//输入: [1,3,2,3,1]
//输出: 2
// 
//
// 示例 2: 
//
// 
//输入: [2,4,3,5,1]
//输出: 3
// 
//
// 注意: 
//
// 
// 给定数组的长度不会超过50000。 
// 输入数组中的所有数字都在32位整数的表示范围内。 
// 
// Related Topics 排序 树状数组 线段树 二分查找 分治算法 
// 👍 116 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int reversePairs(int[] nums) {
        return mergeSortedAndCount(nums,0,nums.length - 1) ;
    }
    public void merge (int[] nums , int startIndex , int midIndex , int endIndex) {
        int leftIndex = startIndex ;
        int rightIndex = midIndex + 1 ;
        int i = 0 ;
        int[] newArray = new int[endIndex-startIndex+1] ;
        while (leftIndex <= midIndex && rightIndex <= endIndex) {
            if ( nums[leftIndex] < nums[rightIndex]) {
                newArray[i++] = nums[leftIndex++] ;
            }else {
                newArray[i++] = nums[rightIndex++] ;
            }
        }
        while (leftIndex <= midIndex) {
            newArray[i++] = nums[leftIndex++] ;
        }
        while (rightIndex <= endIndex) {
            newArray[i++] = nums[rightIndex++] ;
        }
        for (int j=0 ; j< endIndex - startIndex +1 ; j++ ) {
            nums[startIndex + j] = newArray[j] ;
        }
    }

    public int mergeSortedAndCount(int[] nums, int startIndex , int endIndex) {
        if (startIndex < endIndex) {
            int count = 0 ;
            int midIndex = (startIndex + endIndex) >> 1 ;
            count += mergeSortedAndCount(nums,startIndex,midIndex) + mergeSortedAndCount(nums,midIndex+1,endIndex);
            int leftIndex = startIndex ;
            int rightIndex = midIndex + 1 ;
            while (leftIndex <= midIndex && rightIndex <= endIndex) {
                if (  (long)nums[leftIndex] > (2*(long)nums[rightIndex])) {
                    count += midIndex - leftIndex + 1 ;
                    rightIndex ++ ;
                } else {
                    leftIndex ++ ;
                }
            }
            merge(nums , startIndex , midIndex , endIndex) ;
            return count ;
        } else
            return 0 ;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
