学习笔记

# Week01学习笔记

 

 

# **1**、高效学习方法

## **1.1** **课程学习流程**

#### ●课前预习，先看PPT

#### ●倍速看视频，不懂的地方放慢或者停下反复看

#### ●做课后练习题，反复练习

#### ●构建自己的知识图谱，逐渐完善

 

 

## **1.2**、做题流程 **五毒神掌**

#### ●5~10分钟看题，不要着急做，先理解题目

#### ●5分钟内想不出来，看题解，理解别人的代码

#### ●自己写一遍，尽量不再看题解

#### ●自己AC之后，看国际站上的高分解题方法

#### ●回来自己再做一遍

#### ●一天后，一周后再来做一遍，忘记了就再重复一遍上面的流程，直到不看题解就能做出来

#### ●面试前集中过一遍



# **2**、编程习惯

#### ●设置好最适合自己的编程环境(IDEA)

#### ●摒弃之前的坏习惯

#### ●自顶向下的编程方式，结构清晰

# **3** **复杂度分析**

## **3.1 时间复杂度**

#### 只看最高的复杂度

 

#### 常见的时间复杂度

 

#### ●O(1) 常数级别，如散列表操作

#### ●O(log n) 对数级别，如二分查找

#### ●O(n) 线性级别，如计数排序

#### ●O(n^2) 平方级别，如冒泡排序

#### ●O(2^n) 指数级别，如斐波那契数列

#### ●O(n!) 阶乘

 

## **3.2 空间复杂度**

#### 有没有额外申请空间，原地操作为O(1)

 

#### ●数组的长度

#### ●递归的深度

# **4** **数组、链表、跳表**

## **4.1 数组**

#### ●随机访问，O(1)时间复杂度

#### ●插入删除，O(n)时间复杂度 (涉及到元素的搬移)

#### Java中的Array List就是对数组的封装，默认数组大小是10， 每次插入都会进行元素的copy， 如果大小不够，就会进行扩容，申请一个当前大小二倍的空间，再把当前元素copy过去。

 

## **4.2 链表**

#### ●查找，O(n)时间复杂度

#### ●插入删除，O(1)时间复杂度

#### Java中的Link List使用双向链表来实现

#### 添加节点操作

1. #### new. next = node. next
2. #### node. next = new

#### 删除节点操作

1. #### node.pre.next = node.next
2. #### node.next = null

 

## **4.3 跳表**

#### ●插入删除查找，O(log n)时间复杂度

#### 只能用于有序列表

#### 通过空间换时间，增加索引，优化链表的查找速度，空间复杂度O(n)

#### 对标的是平衡树(AVL)和二分查找

#### 实际应用有：Redis、LevelDB等

# **5 栈、队列、双端队列、优先队列**

## **5.1 栈**

#### ●后进先出

#### ●添加删除，O(1)时间复杂度

#### Java中Stack的API，其内部通过数组实现

#### ●peek() 查看栈顶元素

#### ●pop() 出栈

#### ●push(e) 入栈

#### ●search(e) 搜索栈内元素，返回该元素在栈内的深度，即下标+1

 

## **5.2 队列**

#### ●先进先出

#### ●添加删除，O(1)时间复杂度

#### Java中的Queue是一个接口，他的实现有好多种，如LinkedList，PriorityQueue，LinkedBlockingQueue等。

| **抛出异常** | **返回值** | **说明**     |
| ------------ | ---------- | ------------ |
| add(e)       | offer(e)   | 入队         |
| remove(e)    | poll()     | 出队         |
| element(e)   | peek()     | 查看队首元素 |

 

 

## **5.3 双端队列**

#### ●两端都可以进出的队列

#### ●添加删除，O(1)时间复杂度

#### Java中的Deque也是一个接口，他继承了Queue

| **抛出异常**  | **返回值**    | **说明**       |
| ------------- | ------------- | -------------- |
| addFirst(e)   | offerFirst(e) | 在队头添加元素 |
| addLast(e)    | offerLast(e)  | 在队尾添加元素 |
| getFirst()    | peekFirst()   | 查看队首元素   |
| getLast()     | peekLast()    | 查看队尾元素   |
| removeFirst() | pollFirst()   | 删除队首元素   |
| removeLast()  | pollLast()    | 删除队尾元素   |

 

 

## **5.4 优先队列**

#### ●可以按优先级出队

#### ●插入，O(1)时间复杂度

#### ●取出，O(log n)时间复杂度

#### ●底层实现有：堆(heap)、二叉树搜索树(bst)等

#### Java中的是PriorityQueue，继承AbstractQueue，最终实现了Queue





# **6 本周作业**

## **6.1 用 add first 或 add last 这套新的 API 改写 Deque 的代码**

```java
Deque<String> deque = new LinkedList<>();

// 双端队列 实现 队列 先进先出
deque.addLast("a");
deque.addLast("b");
deque.addLast("c");
System.out.println(deque);

String peek = deque.peekFirst();
System.out.println(peek);
System.out.println(deque);

while (deque.size() > 0) {
        System.out.println(deque.pollFirst());
}

System.out.println(deque);

// 双端队列 实现 栈 后进先出
deque.addFirst("a");
deque.addFirst("b");
deque.addFirst("c");
System.out.println(deque);

String peek2 = deque.peekFirst();
System.out.println(peek2);
System.out.println(deque);

while (deque.size() > 0) {
        System.out.println(deque.pollFirst());
}

System.out.println(deque);
```



## 6.2 分析 Queue 和 Priority Queue 的源码

### 6.2 .1 Queue

#### Java中的Queue是一个接口，继承了Collection接口

#### 主要方法有：

```java
// 添加一个元素，添加失败会抛出异常 IllegalStateException
    boolean add(E e);

// 添加一个元素，添加失败返回false
    boolean offer(E e);

// 删除队首元素，删除失败会抛出异常 NoSuchElementException
    E remove();

// 删除队首元素，删除失败返回null
    E poll();

// 查看队首元素，不存在则抛出异常 NoSuchElementException
    E element();

// 查看队首元素，不存在返回null
    E peek();
```

### **6.2.2 Priority Queue**

#### 首先，PriorityQueue是继承自AbstractQueue，AbstractQueue则是实现了Queue接口，封装了一些队列基础的方法。

#### add方法（入队）

```java
public boolean add(E e) {
    return offer(e);
}

public boolean offer(E e) {
    if (e == null)
        throw new NullPointerException();
    modCount++;
    int i = size;
    // 是否扩容
    if (i >= queue.length)
        grow(i + 1);
    size = i + 1;
    if (i == 0)
        queue[0] = e;
    else
        siftUp(i, e);
    return true;
}
```

#### 首先，add方法内部全是通过offer方法来实现的，我们来看一下offer方法的大致实现过程：

#### ●判空，添加数据不能为null；

#### ●根据size与数组长度比较，判断是否扩容；

#### ●判断添加的是不是第一个元素，如果不是，进行调整，增加完元素之后，我们需要进行调整才能维护最大堆或最小堆的性质。而这里的这个调整在堆中被称为上浮（对应的方法是siftUp）。

#### grow方法

```java
private void grow(int minCapacity) {
    int oldCapacity = queue.length;
    // Double size if small; else grow by 50%
    int newCapacity = oldCapacity + ((oldCapacity < 64) ?
                                     (oldCapacity + 2) :
                                     (oldCapacity >> 1));
    // overflow-conscious code
    if (newCapacity - MAX_ARRAY_SIZE > 0)
        newCapacity = hugeCapacity(minCapacity);
    queue = Arrays.copyOf(queue, newCapacity);
}

private static int hugeCapacity(int minCapacity) {
    if (minCapacity < 0) // overflow
        throw new OutOfMemoryError();
    return (minCapacity > MAX_ARRAY_SIZE) ?
        Integer.MAX_VALUE :
        MAX_ARRAY_SIZE;
}
```

#### 扩容的时候，先判断当前队列容量是否小于64，如果是扩容一倍容量，如果不是，扩容原容量的1/2。而hugeCapacity方法我们也看到多次了，参考List的扩容就知道了，就是溢出检查，而拷贝的方法也还是Arrays.copyOf方法。

#### siftUp方法

#### 这个方法就是添加完元素之后进行的上浮调整方法

```java
private void siftUp(int k, E x) {
    // 是否有比较器
    if (comparator != null)
        siftUpUsingComparator(k, x);
    else
        siftUpComparable(k, x);
}

/*
 * 如果有比较器，调用该方法，内部实现和siftUpComparable类似
 */
private void siftUpUsingComparator(int k, E x) {
    while (k > 0) {
        int parent = (k - 1) >>> 1;
        Object e = queue[parent];
        if (comparator.compare(x, (E) e) >= 0)
            break;
        queue[k] = e;
        k = parent;
    }
    queue[k] = x;
}

/*
 * 如果没有比较器，调用该方法
 */
private void siftUpComparable(int k, E x) {
    Comparable<? super E> key = (Comparable<? super E>) x;
    // k是下标值，保证结点有父级结点
    while (k > 0) {
        // 通过无符号右移，计算父级结点下标，-> parent = (thisNode -1) / 2
        int parent = (k - 1) >>> 1;
        // 获取父级结点的值
        Object e = queue[parent];
        // 比较要插入结点与父级结点值，如果大于父级结点，不需要移动，结束
        if (key.compareTo((E) e) >= 0)
            break;
        // 如果小于父结点值，父结点值下沉
        queue[k] = e;
        // 改变下标值
        k = parent;
    }
    // 最终将当前结点值放到对应的位置上
    queue[k] = key;
}
```

#### 通过代码我们可以看到，调整的方式是判断是否有比较器，然后调用各自对应的处理方法。

#### ●没有比较器，会按照我们添加的数据类型的比较器，也就是所谓的自然顺序来进行比较。比如说，我们添加的数据类型是int，那么这里会按照Integer的Comparable来进行排序；

#### ●有比较器，按照对应的比较器的顺序来进行上浮操作。

#### peek方法和poll方法

```java
public E peek() {
    return (size == 0) ? null : (E) queue[0];
}

public E poll() {
    if (size == 0)
        return null;
    int s = --size;
    modCount++;
    // 堆中最小值
    E result = (E) queue[0];
    // 取数组中最后一个元素的值
    E x = (E) queue[s];
    // 将堆中最后一个值设置为null
    queue[s] = null;
    // 如果数组不是只有一个元素，执行下沉操作
    if (s != 0)
        // 下沉操作
        siftDown(0, x);
    return result;
}
```

#### 这两个都是出队的方法。其中，peek方法特别简单，就是取出堆中最小值元素，然后不移除数据，不用调整堆。

#### 而poll方法是取出堆中最小值的同时，移除该数据，同样，为了保证最小堆的性质，我们需要对堆进行另一种操作：下沉（siftDown）。

#### siftDown方法

```java
private void siftDown(int k, E x) {
    if (comparator != null)
        siftDownUsingComparator(k, x);
    else
        siftDownComparable(k, x);
}

private void siftDownComparable(int k, E x) {
    Comparable<? super E> key = (Comparable<? super E>)x;
    // 计算非叶子结点元素的最大位置，循环的终止条件（在最后一个非叶子节点处结束）
    int half = size >>> 1;        // loop while a non-leaf
    while (k < half) {
        // 计算k位置处的左子结点
        int child = (k << 1) + 1; // assume left child is least
        Object c = queue[child];
        // 右子结点等于左子结点下标加1
        int right = child + 1;
        // 获取左右孩子中值较小的值 
        if (right < size &&
            ((Comparable<? super E>) c).compareTo((E) queue[right]) > 0)
            c = queue[child = right];
        // 然后重新和父结点进行比较，如果大于父结点，不需要移动，结束
        if (key.compareTo((E) c) <= 0)
            break;
        // 父结点下移
        queue[k] = c;
        // 改变下标，循环此操作
        k = child;
    }
    queue[k] = key;
}
```

1. #### 先计算非叶子结点元素的最大位置；

2. #### 循环判断是否是叶子结点；

3. #### 计算根结点的左右子结点（也可以逻辑理解为将尾结点移动至根结点位置），然后循环比较根结点的左右子结点与尾结点值大小，直到尾结点的值小于其左右结点，或者尾结点处于叶子结点位置上时循环终止。

#### remove方法

```java
public boolean remove(Object o) {
    //获取元素的下标    
    int i = indexOf(o);
    if (i == -1)
        return false;
    else {
        removeAt(i);
        return true;
    }
}

/**
 * 执行移除操作
 */
private E removeAt(int i) {
    // assert i >= 0 && i < size;
    modCount++;
    int s = --size;
    // 如果要移除的就是最后一个元素，赋值为null
    if (s == i) // removed last element
        queue[i] = null;
    else {
        // 取队列尾元素
        E moved = (E) queue[s];
        // 将队尾元素置为null
        queue[s] = null;
        // 下沉操作
        siftDown(i, moved);
        // 如果下移后元素位置没发生变化，说明moved的左右子结点都大于moved，这时就需要上浮操作
        if (queue[i] == moved) {
            // 上浮操作
            siftUp(i, moved);
            // 如果上浮之后发生了元素位置
            if (queue[i] != moved)
                return moved;
        }
    }
    return null;
}
```

#### 通过代码，我们可以知道remove方法是从队列中移除指定元素，如果该元素有不止一个，则移除数组中的一个出现的元素。