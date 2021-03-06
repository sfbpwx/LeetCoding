##红黑树
### 平衡二叉树的定义

#### 平衡二叉树的严格定义：
- 二叉树中的任意一个节点的左右子树的高度相差不能大于1。

严格符合平衡二叉查找树的定义的树是AVL树。

#### 如何理解平衡的意思

- 平衡二叉查找树中平衡的意思，其实就是让整棵树左右看起来比较“对称”，比较“平衡”，不要出现左子树很高，右子树很低的情况。这样就能让整颗树的高度相对来说低一些，相应的插入、删除、查找等操作的效率高一些。
#### 红黑树的定义
- 根节点是黑色的
- 每个叶子节点都是黑色的空节点(NIL)，也就说，叶子节点不存储数据。
- 任何相邻的节点不能同时为红色，也就是说，红色节点是被黑色节点隔开的。
- 每个节点，从该节点到达其可达叶子节点的所有路径，都包含相同数目的黑色节点
#### 这里着重注意
二叉树的很多操作性能都与树的高度成正比。一棵极其平衡的二叉树的高度大概是`$log_2$`n，所以要证明红黑树是近似平衡的，我们只需要分析，红黑树的高度是否比较稳定的趋近`$log_2$`n就好了。
#### 实现红黑树的基本思想
实际上，红黑树的平衡过程跟魔方复原非常神似，大致过程就是：遇到什么样的节点排布，我们就对应怎么去调整。只要按照这些固定的调整规则来操作，就能将一个非平衡的红黑树调整成平衡的。

红黑树有两个非常重要的操作，左旋和右旋。

左旋全称**围绕某个节点的左旋**

右旋全称**围绕某个节点的右旋**

#### 插入操作的平衡调整

**红黑树规定，插入的节点必须是红色的。而且，二叉查找树中新插入的节点都是放在叶子节点上**。所以，关于插入操作的平衡调整，有这样两种特殊情况，但是也都非常好处理。
- 如果插入节点的父节点是黑色的，那我们什么都不用做，它仍然满足红黑树的定义。
- 如果插入的节点是根节点，那我们直接改变它的颜色，把它变成黑色就可以了。

除此之外，其他情况都会违背红黑树的定义，于是我们就需要进行调整，调整的过程包含两种基础的操作：左右旋转和改变颜色。