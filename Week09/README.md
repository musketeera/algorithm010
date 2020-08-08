# Week09学习笔记

## 高级动态规划

递归：函数自己调用自己

分治：分而治之

### 感触

1. 人肉递归低效、很累
2. 找到最近最简方法，将其拆解成可重复解决的问题
3. 数学归纳法思维

本质：寻找重复性——>计算机指令集

### 动态规划要点

1. "Simplifying a complicated problem by breaking it down into simpler sub-problems" (in a recursive manner)
2. Divide & Conquer + Optimal substructure 分治+最优子结构
3. 顺推形式：动态递推

### 关键点

动态规划和递归或者分治没有根本上的区别（关键看有无最优子结构）

拥有共性：找到重复子问题

差异性：最优子结构、中途可以淘汰次优解

**买卖股票的最佳时机**

k=1

```
dp[i][1][0] = max(dp[i-1][1][0], dp[i-1][1][1] + prices[i])
dp[i][1][1] = max(dp[i-1][1][1], dp[i-1][0][0] - prices[i]) 
            = max(dp[i-1][1][1], -prices[i])
k = 0 的 base case，所以 dp[i-1][0][0] = 0

现在发现 k 都是 1，不会改变，即 k 对状态转移已经没有影响了
可以进行进一步化简去掉所有 k
dp[i][0] = max(dp[i-1][0], dp[i-1][1] + prices[i])
dp[i][1] = max(dp[i-1][1], -prices[i])
```

**股票买卖的最佳时机II**

k=+infinity

```
dp[i][k][0] = max(dp[i-1][k][0], dp[i-1][k][1] + prices[i])
dp[i][k][1] = max(dp[i-1][k][1], dp[i-1][k-1][0] - prices[i])
            = max(dp[i-1][k][1], dp[i-1][k][0] - prices[i])

我们发现数组中的 k 已经不会改变了，也就是说不需要记录 k 这个状态了
dp[i][0] = max(dp[i-1][0], dp[i-1][1] + prices[i])
dp[i][1] = max(dp[i-1][1], dp[i-1][0] - prices[i])
```

**买卖股票的最佳时机 III**

k = +infinity with cooldown

```
dp[i][0] = max(dp[i-1][0], dp[i-1][1] + prices[i])
dp[i][1] = max(dp[i-1][1], dp[i-2][0] - prices[i])
第 i 天选择 buy 的时候，要从 i-2 的状态转移，而不是 i-1 
```

**买卖股票的最佳时机 IV**

k = +infinity with fee

```
dp[i][0] = max(dp[i-1][0], dp[i-1][1] + prices[i])
dp[i][1] = max(dp[i-1][1], dp[i-1][0] - prices[i] - fee)
相当于买入股票的价格升高了
在第一个式子里减也是一样的，相当于卖出股票的价格减小了
```

**最佳买卖股票时机含冷冻期**

k = 2

```
dp[i][k][0] = max(dp[i-1][k][0], dp[i-1][k][1] + prices[i])
dp[i][k][1] = max(dp[i-1][k][1], dp[i-1][k-1][0] - prices[i])
```

**买卖股票的最佳时机含手续费**

k = any integer

```java
int maxProfit_k_any(int max_k, int[] prices) {
    int n = prices.length;
    if (max_k > n / 2) 
        return maxProfit_k_inf(prices);

    int[][][] dp = new int[n][max_k + 1][2];
    for (int i = 0; i < n; i++) 
        for (int k = max_k; k >= 1; k--) {
            if (i - 1 == -1) { /* 处理 base case */ }
            dp[i][k][0] = max(dp[i-1][k][0], dp[i-1][k][1] + prices[i]);
            dp[i][k][1] = max(dp[i-1][k][1], dp[i-1][k-1][0] - prices[i]);     
        }
    return dp[n - 1][max_k][0];
}
```

**不同路径 II**

状态转移方程
$$
dp[i][j]=
\left \{
\begin{aligned}
dp[i-1,j]+dp[i,j-1],  \text{(i,j)上无障碍物} \,  \ \\
0,  \text{(i,j)上有障碍物} \,  \
\end{aligned}
\right.
$$

### 字符串算法

**高级字符串算法**

- 最长子串、子序列
- 字符串+DP

**字符串匹配算法**

1. 暴力法（brute force）
2. Rabin-Karp算法
3. KMP算法