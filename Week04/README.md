# Week04

## 1.深度优先搜索和广度优先搜索

### 搜索-遍历

- 每个节点都要访问一次
- 每个节点仅访问一次

- 对于节点访问顺序不限

  -深度优先：depth first search

  -广度优先：breadth first search

深度优先遍历：我们选择一条支路，尽可能不断地深入，如果遇到死路就往回退，回退过程中如果遇到没探索过的支路，就进入该支路继续深入。（二叉树的前序、中序、后序遍历）

DFS代码模板

- 使用递归

```python
visited = set() 
def dfs(node, visited):
    if node in visited: # terminator
    	# already visited 
    	return 
	visited.add(node) 
	# process current node here. 
	...
	for next_node in node.children(): 
		if next_node not in visited: 
			dfs(next_node, visited)
```

- 非递归

```python
def DFS(self, tree): 
	if tree.root is None: 
		return [] 
	visited, stack = [], [tree.root]
	while stack: 
		node = stack.pop() 
		visited.add(node)
		process (node) 
		nodes = generate_related_nodes(node) 
		stack.push(nodes) 
	# other processing work 
	...

```

广度优先遍历：首先遍历起点相邻的几个节点，然后遍历距离起点稍远一些（隔一层）的节点，然后再遍历距离起点更远一些（隔两层）的节点，一层一层由内而外的遍历方式。（二叉树的层序遍历）

BFS代码模板

```python
def BFS(graph, start, end):
    visited = set()
	queue = [] 
	queue.append([start]) 
	while queue: 
		node = queue.pop() 
		visited.add(node)
		process(node) 
		nodes = generate_related_nodes(node) 
		queue.push(nodes)
	# other processing work 
	...
```

## 2.贪心算法

对问题求解时，总是做出在当前看来是最好的选择。也就是说，不从整体最优上加以考虑，他所做出的是在某种意义上的局部最优解。

贪心算法不是对所有问题都能得到整体最优解，关键是贪心策略的选择，选择的贪心策略必须具备无后效性，即某个状态以前的过程不会影响以后的状态，只与当前状态有关。

## 3.二分查找

### 前提

1. 目标函数单调性（单调递增或者单调递减）
2. 存在上下界（bounded）
3. 能够通过索引访问（index accessible）

代码模板

```python
left, right = 0, len(array) - 1 
while left <= right: 
	  mid = (left + right) / 2 
	  if array[mid] == target: 
		    # find the target!! 
		    break or return result 
	  elif array[mid] < target: 
		    left = mid + 1 
	  else: 
		    right = mid - 1
```



作业：使用二分查找，寻找一个半有序数组 [4, 5, 6, 7, 0, 1, 2] 中间无序的地方

```java
//查找一个半升序数组的中间无序下标
public int searchUnorderedIndex(int[] nums) {
    //基准值，看m索引的值小于基准值，说明m左边是有序的，直接看mid右边
    int point = nums[0];
    int i = 0;
    int j = nums.length - 1;
    while (i < j) {
        int m = i + (j - i) / 2;
        //如果i + 1 = j，说明j的下标值就是最小值，
        //但是还需要判断i和j的索引值，如果j值大于i值，说明这里就是无序点，返回j即可
        if (i + 1 == j) {
            if (nums[i] > nums[j]) {
                return j;
            }
            //j的值不大于i的值，说明就是一个完全有序数组,直接返回。
            return 0;
        }
        if (point > nums[m]) {
            j = m;
        } else {
            i = m;
        }
    }
    return 0;
}
```

