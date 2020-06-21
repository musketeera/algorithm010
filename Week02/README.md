# Week02学习笔记
## 1 哈希表、映射、集合的实现与特性
哈希表（Hash table）也叫散列表。可以根据关键码直接访问数据。

它通过哈希函数（Hash Function）把关键码映射到表中的一个位置，来加快访问速度。

哈希碰撞工程中常用解决方式：拉链式，通过增加一个链表来实现。

平均查询时间复杂度：O(1)，如果哈希函数不好，查询则会退化到O(n)。
### 1.1 Java中的使用：
Map：key-value形式，key不重复，定义为一个接口

HashMap() ，TreeMap() 二叉搜索树 内部用红黑树实现

​	●put(key, value)

​	●get(key)

​    ●containsKey(key)，containsValue(value)

​	●size()

​	●clear()


​	●Set：不重复的元素集合，定义为一个接口

HashSet()，TreeSet() 二叉搜索树 内部用红黑树实现
	●add(value)
	●remove(value)
	●contains(value)

### 1.2 HashMap 源码分析
Java中的HashSet内部是基于HashMap实现的，每次存的value是一个空的Object。

HashMap由数组+链表组成的，数组是HashMap的主体，链表则是主要为了解决哈希冲突而存在的，如果定位到的数组位置不含链表（当前entry的next指向null）,那么查找，添加等操作很快，仅需一次寻址即可；如果定位到的数组包含链表，对于添加操作，其时间复杂度为O(n)，首先遍历链表，存在即覆盖，否则新增；对于查找操作来讲，仍需遍历链表，然后通过key对象的equals方法逐一比对查找。所以，性能考虑，HashMap中的链表出现越少，性能才会越好。

put(K key, V value) 存数据

```java
public V put(K key, V value) {
    //如果table数组为空数组{}，进行数组填充（为table分配实际内存空间），入参为threshold，
    //此时threshold为initialCapacity 默认是1<<4(24=16)
    if (table == EMPTY_TABLE) {
        inflateTable(threshold);
    }
   //如果key为null，存储位置为table[0]或table[0]的冲突链上
    if (key == null)
        return putForNullKey(value);
    int hash = hash(key);//对key的hashcode进一步计算，确保散列均匀
    int i = indexFor(hash, table.length);//获取在table中的实际位置
    for (Entry<K,V> e = table[i]; e != null; e = e.next) {
    //如果该对应数据已存在，执行覆盖操作。用新value替换旧value，并返回旧value
        Object k;
        if (e.hash == hash && ((k = e.key) == key || key.equals(k))) {
            V oldValue = e.value;
            e.value = value;
            e.recordAccess(this);
            return oldValue;
        }
    }
    modCount++;//保证并发访问时，若HashMap内部结构发生变化，快速响应失败
    addEntry(hash, key, value, i);//新增一个entry
    return null;
}
```

get(Object key) 取数据

```java
public V get(Object key) {//如果key为null,则直接去table[0]处去检索即可。
    if (key == null) {
         return getForNullKey();
    }  
    Entry<K,V> entry = getEntry(key);
    return null == entry ? null : entry.getValue();
}
```

get方法的实现key(hashcode)–>hash–>indexFor–>最终索引位置，找到对应位置table[i]，再查看是否有链表，遍历链表，通过key的equals方法比对查找对应的记录。要注意的是，有人觉得上面在定位到数组位置之后然后遍历链表的时候，e.hash == hash这个判断没必要，仅通过equals判断就可以。其实不然，试想一下，如果传入的key对象重写了equals方法却没有重写hashCode，而恰巧此对象定位到这个数组位置，如果仅仅用equals判断可能是相等的，但其hashCode和当前对象不一致。

![put方法流程图](E:\20181105181728652.png)

## 2 树、二叉树、二叉搜索树的实现与特性
### 2.1 树
Linked List是一种特殊的树，每一层都只有一个子节点。

![树的定义](E:\12.jpg)

### 2.2 二叉树

![二叉树](E:\13.jpg)

二叉树的遍历：

- 前序遍历：根 - 左 - 右
- 中序遍历：左 - 根 - 右
- 后序遍历：左 - 右 - 根

一般树的遍历都使用递归来实现，示例代码：

```java
// 前序
void preorder(Node root) {
  if (root != null) {
    System.out.println(root.val);
    preorder(root.left);
    preorder(root.right);
  }
}

// 中序
void inorder(Node root) {
  if (root != null) {
    inorder(root.left);
    System.out.println(root.val);
    inorder(root.right);
  }
}

// 后序
void postorder(Node root) {
  if (root != null) {
    postorder(root.left);
    postorder(root.right);
    System.out.println(root.val);
  }
}
```

### 2.3 二叉搜索树

![二叉搜索树](E:\14.jpg)

二叉搜索树特性：

- 左子树上所有节点值都小于根节点的值。

- 右子树上所有节点值都大于根节点的值。

- 中序遍历为升序排列

- 查询、插入、删除时间复杂度都为O(log n)，退化成链表时最坏O(n)

  

## 3 堆和二叉堆、图

### 3.1 堆

可以迅速找到一堆数中的最大值或最小值的数据结构。

如果根节点的值最大叫大顶堆，根节点的值最小叫小顶堆。

堆的实现有好多种，常见的二叉堆、斐波那契堆等。

常见操作时间复杂度：

- 查找最大/最小值：O(1)
- 删除最大/最小值：O(log n)
- 插入元素：O(log n)，最好情况O(1)

### 3.2 二叉堆

![二叉堆](E:\15.jpg)

通过完全二叉树实现（不是二叉搜索树）。

性质：

- 是一颗完全树。
- 树中任意节点的值总是大于等于子节点的值。

实现：

- 一般通过数组实现。
- 假设第一个数组索引为0，父节点和子节点关系如下：
  - i节点的左孩子索引为：2*i+1
  - i节点的右孩子索引为：2*i+2
  - i节点的父节点索引为：(i - 1) / 2 向下取整

插入操作：

1. 新元素插入到最后面
2. 依次向上调整整个堆的结构，直到根节点

删除最大值：

1. 将最后面元素放到第一个位置
2. 依次向下调整整个堆的结构，直到堆尾

### 3.3 图

![图](E:\16.jpg)

图的属性：

- 点 V
  - 入度 - 出度
  - 点与点之间是否连通
- 边 E
  - 有向 - 无向
  - 权重（边长）

常见算法：

- DFS 深度优先搜索
- BFS 广度优先搜索