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



## HeapSort(堆排序算法)：时间复杂度O(N*logN),额外空间复杂度O(1)，是一个不稳定性的排序。

# 一 准备知识

堆的结构可以分为大根堆和小根堆，是一个[完全二叉树](https://baike.baidu.com/item/完全二叉树/7773232?fr=aladdin)，而堆排序是根据堆的这种数据结构设计的一种排序，下面先来看看什么是大根堆和小根堆。

## 1.1 大根堆和小根堆

性质：每个结点的值都大于其左孩子和右孩子结点的值，称之为大根堆；每个结点的值都小于其左孩子和右孩子结点的值，称之为小根堆。如下图

![](E:\Week02图片\1.png)

 我们对上面的图中每个数都进行了标记，上面的结构映射成数组就变成了下面这个样子

![](E:\Week02图片\2.png)

​	还有一个基本概念：查找数组中某个数的父结点和左右孩子结点，比如已知索引为i的数，那么

1.父结点索引：(i-1)/2（这里计算机中的除以2，省略掉小数）

2.左孩子索引：2*i+1

3.右孩子索引：2*i+2

​	所以上面两个数组可以脑补成堆结构，因为他们满足堆的定义性质：

**大根堆**：arr(i)>arr(2*i+1) && arr(i)>arr(2*i+2)

**小根堆**：arr(i)<arr(2*i+1) && arr(i)<arr(2*i+2)

# 二 堆排序基本步骤

**基本思想：**

​	1.首先将待排序的数组构造成一个大根堆，此时，整个数组的最大值就是堆结构的顶端

​	2.将顶端的数与末尾的数交换，此时，末尾的数为最大值，剩余待排序数组个数为n-1

​	3.将剩余的n-1个数再构造成大根堆，再将顶端数与n-1位置的数交换，如此反复执行，便能得到有序数组

## 2.1 构造堆

将无序数组构造成一个大根堆（升序用大根堆，降序就用小根堆）

假设存在以下数组

![](E:\Week02图片\3.png)

​	主要思路：第一次保证0~0位置大根堆结构（废话），第二次保证0~1位置大根堆结构，第三次保证0~2位置大根堆结构...直到保证0~n-1位置大根堆结构（每次新插入的数据都与其父结点进行比较，如果插入的数比父结点大，则与父结点交换，否则一直向上交换，直到小于等于父结点，或者来到了顶端）

​	插入6的时候，6大于他的父结点3，即arr(1)>arr(0)，则交换；此时，保证了0~1位置是大根堆结构，如下图：

![](E:\Week02图片\4.png)

​	 插入8的时候，8大于其父结点6，即arr(2)>arr(0),则交换；此时，保证了0~2位置是大根堆结构，如下图

![](E:\Week02图片\5.png)

​	插入5的时候，5大于其父结点3，则交换，交换之后，5又发现比8小，所以不交换；此时，保证了0~3位置大根堆结构，如下图 

![](E:\Week02图片\6.png)

​	插入7的时候，7大于其父结点5，则交换，交换之后，7又发现比8小，所以不交换；此时**整个数组已经是大根堆结构** 

![](E:\Week02图片\7.png)

## 2.2 固定最大值再构造堆

此时，我们已经得到一个大根堆，下面将顶端的数与最后一位数交换，然后将剩余的数再构造成一个大根堆

![](E:\Week02图片\8.png)

 	此时最大数8已经来到末尾，则固定不动，后面只需要对顶端的数据进行操作即可，拿顶端的数与其左右孩子较大的数进行比较，如果顶端的数大于其左右孩子较大的数，则停止，如果顶端的数小于其左右孩子较大的数，则交换，然后继续与下面的孩子进行比较

​	下图中，5的左右孩子中，左孩子7比右孩子6大，则5与7进行比较，发现5<7，则交换；交换后，发现5已经大于他的左孩子，说明剩余的数已经构成大根堆，后面就是重复固定最大值，然后构造大根堆
![](E:\Week02图片\9.png)

​	如下图：顶端数7与末尾数3进行交换，固定好7，

![](E:\Week02图片\10.png)

​	剩余的数开始构造大根堆 ，然后顶端数与末尾数交换，固定最大值再构造大根堆，重复执行上面的操作，最终会得到有序数组

![](E:\Week02图片\11.png)

# 三 总结

对上面的流程总结下：

1、首先将无需数组构造成一个大根堆（新插入的数据与其父结点比较）

2、固定一个最大值，将剩余的数重新构造成一个大根堆，重复这样的过程

# 四 代码

代码中主要两个方法：

1、将待排序数组构造成一个大根堆（元素上升）

2、固定一个最大值，将剩余的数再构造成一个大根堆（元素下降）

```java
    //堆排序
    public static void heapSort(int[] arr) {
        //构造大根堆
        heapInsert(arr);
        int size = arr.length;
        while (size > 1) {
            //固定最大值
            swap(arr, 0, size - 1);
            size--;
            //构造大根堆
            heapify(arr, 0, size);
 
        }
 
    }
 
    //构造大根堆（通过新插入的数上升）
    public static void heapInsert(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            //当前插入的索引
            int currentIndex = i;
            //父结点索引
            int fatherIndex = (currentIndex - 1) / 2;
            //如果当前插入的值大于其父结点的值,则交换值，并且将索引指向父结点
            //然后继续和上面的父结点值比较，直到不大于父结点，则退出循环
            while (arr[currentIndex] > arr[fatherIndex]) {
                //交换当前结点与父结点的值
                swap(arr, currentIndex, fatherIndex);
                //将当前索引指向父索引
                currentIndex = fatherIndex;
                //重新计算当前索引的父索引
                fatherIndex = (currentIndex - 1) / 2;
            }
        }
    }
    //将剩余的数构造成大根堆（通过顶端的数下降）
    public static void heapify(int[] arr, int index, int size) {
        int left = 2 * index + 1;
        int right = 2 * index + 2;
        while (left < size) {
            int largestIndex;
            //判断孩子中较大的值的索引（要确保右孩子在size范围之内）
            if (arr[left] < arr[right] && right < size) {
                largestIndex = right;
            } else {
                largestIndex = left;
            }
            //比较父结点的值与孩子中较大的值，并确定最大值的索引
            if (arr[index] > arr[largestIndex]) {
                largestIndex = index;
            }
            //如果父结点索引是最大值的索引，那已经是大根堆了，则退出循环
            if (index == largestIndex) {
                break;
            }
            //父结点不是最大值，与孩子中较大的值交换
            swap(arr, largestIndex, index);
            //将索引指向孩子中较大的值的索引
            index = largestIndex;
            //重新计算交换之后的孩子的索引
            left = 2 * index + 1;
            right = 2 * index + 2;
        }
 
    }
    //交换数组中两个元素的值
    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
```



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