# Week07学习笔记

## 字典树和并查集

### Trie树

**基本结构：**字典树，即Trie树，又称单词查找树或键树，一种树形结构。典型应用于统计和排序大量的字符串（但不仅限于字符串），所以经常被搜索引擎系统用于文本词频统计。

**优点：**最大限度地减少无谓的字符串比较，查询效率比哈希表高。

**基本性质：**

1. 结点本身不存在完整单词；
2. 从根节点到某一结点，路径上经过的字符连接起来，为该结点对应的字符串；
3. 每个结点的所有子节点路径代表的字符都不相同。

**结点存储额外信息：**数字（单词统计频次）

**结点的内部实现：**至少是26叉树

**核心思想：**

Trie树的核心思想是空间换时间。

利用字符串的公共前缀来降低查询时间的开销以达到提高效率的目的。

**Trie树代码模板**

```java
class Trie {
    private boolean isEnd;
    private Trie[] next;
    /** Initialize your data structure here. */
    public Trie() {
        isEnd = false;
        next = new Trie[26];
    }
    
    /** Inserts a word into the trie. */
    public void insert(String word) {
        if (word == null || word.length() == 0) return;
        Trie curr = this;
        char[] words = word.toCharArray();
        for (int i = 0;i < words.length;i++) {
            int n = words[i] - 'a';
            if (curr.next[n] == null) curr.next[n] = new Trie();
            curr = curr.next[n];
        }
        curr.isEnd = true;
    }
    
    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        Trie node = searchPrefix(word);
        return node != null && node.isEnd;
    }
    
    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        Trie node = searchPrefix(prefix);
        return node != null;
    }

    private Trie searchPrefix(String word) {
        Trie node = this;
        char[] words = word.toCharArray();
        for (int i = 0;i < words.length;i++) {
            node = node.next[words[i] - 'a'];
            if (node == null) return null;
        }
        return node;
    }
}
```

**单词搜索2时间复杂度分析**

时间复杂度：O(M($4*3^{L-1}$))，其中M是二维网格中的单元格数，L是单词的最大长度。

- 该算法循环遍历二维网格中的所有单元，因此在复杂度公式中我们有M 作为因子。然后将其归结为每个启动单元所需的最大步骤数（即$4*3^{L-1}$）。
- 假设单词的最大长度是 L，从一个单元格开始，最初我们最多可以探索 4 个方向。假设每个方向都是有效的（即最坏情况），在接下来的探索中，我们最多有 3 个相邻的单元（不包括我们来的单元）要探索。因此，在回溯探索期间，我们最多遍历$4*3^{L-1}$个单元格。 
- 想象一下，二维网格中的每个单元都包含字母 a，单词词典包含一个单词 ['aaaa']。这是算法将遇到的最坏的情况之一。



### 并查集

#### 基本操作

- makeSet(s)：建立一个新的并查集，其中包含s个单元素集合。
- unionSet(x,y)：把元素x和元素y所在的集合合并，要求x和y所在的集合不相交，如果相交则不合并。
- find(x)：找到元素x所在的集合的代表，该操作也可以用于判断两个元素是否位于同一个集合，只要将它们各自的代表比较一下就可以了。

并查集代码模板

```java
class UnionFind { 
	private int count = 0; 
	private int[] parent; 
	public UnionFind(int n) { 
		count = n; 
		parent = new int[n]; 
		for (int i = 0; i < n; i++) { 
			parent[i] = i;
		}
	} 
	public int find(int p) { 
		while (p != parent[p]) { 
			parent[p] = parent[parent[p]]; 
			p = parent[p]; 
		}
		return p; 
	}
	public void union(int p, int q) { 
		int rootP = find(p); 
		int rootQ = find(q); 
		if (rootP == rootQ) return; 
		parent[rootP] = rootQ; 
		count--;
	}
}
```



## 高级搜索

### 剪枝

状态树搜索发现分支已经处理过了，把它暂存在缓存里，整个分支可以剪掉，不需要再手动进行计算，差分支和次优分支也可以剪掉。

### **双向BFS**

如果已经知道搜索的开始状态和结束状态， 要找一个满足某种条件的一条路径（一般是最短路径），为了避免无谓的“组合爆炸”产生，就可以采取双向广度搜索算法，也就是从开始状态和结束状态同时开始搜索，一个向前搜，一个向后找。 这样做的好处是什么？ 我们不妨假设每次搜索的分支因子是r，如果最短的路径长为L的话（也就是搜了L层），那么，用一般的BFS算法（不考虑去掉重复状态），总的搜索状态数是$r^L$；而如果采取双向BFS算法，那么，从前往后搜，我们只需要搜索$\frac{L}{2}$层，从后往前搜，我们也只要搜$\frac{L}{2}$层，因此，搜索状态数是$2*r^{\frac{L}{2}}$，比普通BFS就快了很多了。 双向BFS算法的实质还是BFS，只不过两边同时开始BFS而已。还是可以利用队列来实现：可以设置两个队列，一个用于向前的BFS，另一个用于向后的BFS，利用这两个队列，同时从前、后开始层次遍历搜索树。

代码模板

```c++
void BFS_bothsides()//双向BFS 
{
    if(s1.state==s2.state)//起点终点相同时要特判
    {
           //do something
           found=true;
           return;
    }
    bool found=false;
    memset(visited,0,sizeof(visited));  // 判重数组
    while(!Q1.empty())  Q1.pop();   // 正向队列
    while(!Q2.empty())  Q2.pop();  // 反向队列
    //======正向扩展的状态标记为1，反向扩展标记为2
    visited[s1.state]=1;   // 初始状态标记为1
    visited[s2.state]=2;   // 结束状态标记为2
    Q1.push(s1);  // 初始状态入正向队列
    Q2.push(s2);  // 结束状态入反向队列
    while(!Q1.empty() || !Q2.empty())
    {
           if(!Q1.empty())
                  BFS_expand(Q1,true);  // 在正向队列中搜索
           if(found)  // 搜索结束 
                  return ;
          if(!Q2.empty())
                  BFS_expand(Q2,false);  // 在反向队列中搜索
           if(found) // 搜索结束
                  return ;
    }
}
void BFS_expand(queue<Status> &Q,bool flag)
{  
 	s=Q.front();  // 从队列中得到头结点s
 	Q.pop()
 	for( 每个s 的子节点 t )
	{
        t.state=Gethash(t.temp);  // 获取子节点的状态
        if(flag)   // 在正向队列中判断
        {
           	if(visited[t.state]!=1)// 没在正向队列出现过
            {
                if(visited[t.state]==2)  // 该状态在反向队列中出现过
              	{
                    各种操作；
                    found=true；
                    return;
                }
                visited[t.state]=1;   // 标记为在在正向队列中
                Q.push(t);  // 入队
           	}
        }
        else    // 在正向队列中判断
        {
            if (visited[t.state]!=2) // 没在反向队列出现过
         	{
                if(visited[t.state]==1)  // 该状态在正向向队列中出现过
                {
                    各种操作；
                    found=true；
                    return;
                }
                visited[t.state]=2;  // 标记为在反向队列中
                Q.push(t);  // 入队
            }
        }
    }
}
```

### 回溯法

采用试错的思想，它尝试分步的去解决一个问题。在分步解决问题的过程中，当它通过尝试发现有的分步答案不能得到有效的正确解答时候，它将取消上一步甚至是上几步的计算，再通过其他的可能的分步解答再次尝试寻找问题的答案。

通常用最简单的递归方法来实现，在反复重复上述的步骤后可能出现两种情况：

- 找到一个可能存在的正确的答案
- 在尝试了所有可能的分步方法后宣告该问题没有答案

在最坏的情况下，回溯法会导致一次复杂度为指数时间的计算。

### 启发式搜索

**A*search**

```python
def AstarSearch(graph, start, end):
	pq = collections.priority_queue() # 优先级 —> 估价函数
	pq.append([start]) 
	visited.add(start)
	while pq: 
		node = pq.pop() # can we add more intelligence here ?
		visited.add(node)
		process(node) 
		nodes = generate_related_nodes(node) 
   unvisited = [node for node in nodes if node not in visited]
		pq.push(unvisited)
```

启发式函数：h(n)，它用来评价哪些结点最有希望的是一个我们要找的结点，h(n)会返回一个非负实数，也可以认为是从结点n的目标结点路径的估计成本。

启发式函数是一种告知搜索方向的方法。它提供了一种明智的方法来猜测哪个邻居结点会导向一个目标。



## 红黑树和AVL树

### AVL树

Balance Factor （平衡因子）：

是它的右子树的高度减去左子树的高度（有时相反）

balance factor={-1,0,1}

通过旋转操作来进行平衡

1.左旋 2.右旋 3.左右旋 4.右左旋

### 红黑树

红黑树是一种近似平衡的二叉搜索树（Binary Search Tree），它能够确保任何一个结点的左右子树的高度差小于两倍。具体来说，红黑树是满足如下条件的二叉搜索树：

- 每个结点要么是红色，要么是黑色
- 根结点是黑色
- 每个叶结点（NIL结点，空结点）是黑色的
- 不能有相邻的两个红色结点
- 从任一结点到其每个叶子的所有路径都包含相同数目的黑色结点

### 对比

- AVL trees provide faster lookups than RedBlack Trees because they are more strictly balanced.
- RedBlack Trees provide faster insertion and removal operations than AVL trees as fewer rotations are done due to relatively relaxed balancing.
- AVL trees store balance factors or heights with each node,thus requires storage for an integer per node whereas RedBlack Tree requires only 1 bit of information per node.
- RedBlack Trees are used in most of the language libraries like map,multimap,multisetin C++whereas AVL trees are used in databases where faster retrievals are required.