# Week03学习笔记

## 1、递归的实现、特性以及思维要点

递归的本质是循环，通过循环体调用自己来进行循环。

### 盗梦空间

- 向下进入到不同的梦境中，向上又回到原来的一层
- 用参数来传递不同层之间的变量
- 每一层和周围的人都是一份拷贝（发生和携带变化）

递归代码模板

```java
public void recur(int level, int param) { 
  // terminator  递归终结条件
  if (level > MAX_LEVEL) { 
    // process result 
    return; 
  }
  // process current logic 处理当前层逻辑
  process(level, param); 
  // drill down 进入下一层
  recur( level: level + 1, newParam); 
  // restore current status 清理当前层
 
}
```

### 思维要点

1. 不要人肉进行递归（最大误区）

2. 找到最近最简单方法，将其拆解成可重复解决的问题（找重复子问题）

3. 数学归纳法思维

   

## 2、分治、回溯的实现和特性

### 2.1 分治

递归的细分类（问题找重复性）

分治思想：把一个大的问题拆分成多个子问题，最后组合子问题的结果，如斐波那契、爬楼梯等。

分治代码模板

```java
public void divideConquer(problem, param1, param2, ...) {
  // 终止条件
  if problem == null: 
		printResult();
		return;
  
  // 处理当前层逻辑
  data = prepareData(problem);
  subproblems = splitProblem(problem, data);
  
  // 解决子问题 下探到下层
  subresult1 = divideConquer(subproblems[0], p1, ...);
  subresult2 = divideConquer(subproblems[1], p1, ...);
  subresult3 = divideConquer(subproblems[2], p1, ...);
  
  // 组合子问题
  result = processResult(subresult1, subresult2, subresult3, …);
	
  // 清理变量
}
```

### 2.2 回溯

回溯法采用试错的思想，尝试分步解决一个问题。在分步的过程中，如果现有分布不能得到有效解答，则取消上一步甚至上几步的计算，再次尝试其他的分步寻找有效解答。（不断的在每一层去试）

回溯法通常用最简单的递归实现，最后有两种情况：

- 找到可能存在的有效答案
- 尝试了所有分步后，没有找到有效答案

最坏情况下，回溯法时间复杂度为指数级别。