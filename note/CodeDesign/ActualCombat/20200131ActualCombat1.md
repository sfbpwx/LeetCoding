## 实战1代码实现
### 业务开发包括哪些工作？
分为三方面：接口设计、数据库设计和业务模型设计（业务逻辑）
### 针对积分系统，如何设计数据库
这里的数据库设计比较简单，只需要一账记录积分流水明细的表就可以。表中记录积分的赚取和消费流水，用户积分的各种统计数据
- id  |user_id |channel_id|event_id  |credit|create_time|expired_time
- 明细 |用户    |渠道      |相关事件ID|积分   |赚取消费时间|过期时间
### 如何设计积分系统的接口
1. 赚取积分
- 入参(userId,channelId,eventId,credit,expiredTime)
- 返回：积分明细ID
2. 消费积分
- 入参(userId,channelId,eventId,credit,expiredTime)
- 返回：积分明细ID
3. 查询积分
- 入参(userId)
- 返回：可用总积分
4. 查询总积分明细
- 入参(userId,分页参数)
- 返回：id,userId,channelId,eventId,credit,createTime,expiredTime
5. 查询赚取积分明细
- 入参(userId,分页参数)
- 返回：id,userId,channelId,eventId,credit,createTime,expiredTime
6. 查询消费积分明细
- 入参(userId,分页参数)
- 返回：id,userId,channelId,eventId,credit,createTime,expiredTime
### 业务模型设计
对于大多数系统来说，都分为Controller,Service,Repository，也就是业务模型
这个系统作为一个独立的系统开发或者合并到其他功能系统里都是可以的。注意做好代码的模块化和解耦即可。

#### 为什么要分MVC三层
1. 分层能起到代码复用的作用
同一个repository可以被多个service调用，同一个service可以被多个controller调用
2. 分层能起到隔离变化的作用
repository封装了对数据库的访问操作，提供了抽象的数据访问操作。每一层进行隔离，上层的service和controller的变动不会影响底层的repository变化
3. 分层能起到隔离关注点的作用
关注点更单一，职责分离更明确，service只需要关注业务逻辑，不关注数据来源。controller只关注封装校验格式转换等。内聚性更好
4. 分层能提高代码的可测试性
单元测试不依赖不可控的外部组件
5. 分层能应对系统的复杂性
所有代码都放在一个类里，类的代码就会因为迭代而无限膨胀。这里就需要进行拆分。拆分可以分为两种才分，水平拆分就是模块化，垂直拆分就是分层。
### BO、VO、Entity存在的意义
他们分别为VO(View Object)、BO(Business Object)、Entity。在实际开发中，三者可能包含大量的重复字段，甚至字段都一样

#### 相对于每层定义各自的数据对象来说，是不是定义一个共同的数据对象更好些呢？
推荐这样的设计思路基于以下三点原因
- VO,BO,Entity并非完全一样。比如VO中不能定义password，因为密码不能被暴露，但是其他两个可以
- 虽然代码重复，但是功能雨衣啊不同，从职责上来讲也不一样，所以不违反DRY原则。
- 为了尽量减少每层之间的耦合，把职责边界划分明确，每层都会维护自己的数据对象，层与层之间通过接口交互。数据从下一层传递到上一层的时候，将下一层的数据对象转化成上一层的数据对象，再继续处理。虽然这样的设计稍微有些繁琐，每层都需要定义各自的数据对象，需要做数据对象之间的转化，但是分层清晰。对于非常大的项目来说，结构清晰是第一位的！

#### 既然 VO、BO、Entity 不能合并，那如何解决代码重复的问题呢？
可以通过接口调用传递的方式，比如先获取生成Entity，然后转化成BO进行逻辑处理，然后再转化成VO进行展示

具体的转化方式有BeanUtils、Dozer等。
#### VO、BO、Entity 都是基于贫血模型的，而且为了兼容框架或开发库（比如 MyBatis、Dozer、BeanUtils），我们还需要定义每个字段的 set 方法。这些都违背 OOP 的封装特性，会导致数据被随意修改。那到底该怎么办好呢？
前面我们也提到过，Entity 和 VO 的生命周期是有限的，都仅限在本层范围内。而对应的 Repository 层和 Controller 层也都不包含太多业务逻辑，所以也不会有太多代码随意修改数据，即便设计成贫血、定义每个字段的 set 方法，相对来说也是安全的。

不过，Service 层包含比较多的业务逻辑代码，所以 BO 就存在被任意修改的风险了。但是，设计的问题本身就没有最优解，只有权衡。为了使用方便，我们只能做一些妥协，放弃 BO 的封装特性，由程序员自己来负责这些数据对象的不被错误使用。