## 实战2
### 项目背景
我们希望设计开发一个小的框架，能够获取接口调用的各种统计信息，比如，响应时间的最大值（max）、最小值（min）、平均值（avg）、百分位值（percentile）、接口调用次数（count）、频率（tps） 等，并且支持将统计结果以各种显示格式（比如：JSON 格式、网页格式、自定义显示格式等）输出到各种终端（Console 命令行、HTTP 网页、Email、日志文件、自定义输出终端等），以方便查看。
### 需求分析
从以下两方面来做需求分析
1. 功能型需求分析
- 接口统计信息：包括接口响应时间的统计信息，以及接口调用次数的统计信息等。
- 统计信息的类型：max、min、avg、percentile、count、tps 等。
- 统计信息显示格式：Json、Html、自定义显示格式。
- 统计信息显示终端：Console、Email、HTTP 网页、日志、自定义显示终端。

除此之外，还有几个隐藏需求
- 统计触发方式：包括主动和被动两种。主动表示以一定的频率定时统计数据，并主动推送到显示终端，比如邮件推送。被动表示用户触发统计，比如用户在网页中选择要统计的时间区间，触发统计，并将结果显示给用户。
- 统计时间区间：框架需要支持自定义统计时间区间，比如统计最近 10 分钟的某接口的 tps、访问次数，或者统计 12 月 11 日 00 点到 12 月 12 日 00 点之间某接口响应时间的最大值、最小值、平均值等。
- 统计时间间隔：对于主动触发统计，我们还要支持指定统计时间间隔，也就是多久触发一次统计显示。比如，每间隔 10s 统计一次接口信息并显示到命令行中，每间隔 24 小时发送一封统计信息邮件。
2. 非功能性需求分析
- 易用性：是否容易集成，易插拔，松解耦，接口是否灵活等
- 性能：低延迟，内存消耗不能过大
- 扩展性：使用者不用在修改框架源码的基础上为框架扩展新功能。
- 容错性：对可能出现的异常和错误进行拦截捕获，前面考虑各种异常情况
- 通用性：灵活运用到其他场景中

### 框架设计
可以借鉴TDD(测试驱动开发)和prototype(最小原型)思想，先聚焦一个简单的场景，再继续迭代。

对于性能计数器来说，先聚焦到一个非常具体的、简单的应用场景，实现起来难度就降低很多。

应用场景可以粗略的规划为：注册接口和登录接口

然后是最小原型的代码实现。
```
public class Metrics {
  // Map的key是接口名称，value对应接口请求的响应时间或时间戳；
  private Map<String, List<Double>> responseTimes = new HashMap<>();
  private Map<String, List<Double>> timestamps = new HashMap<>();
  private ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();

  //记录接口请求的响应时间
  public void recordResponseTime(String apiName, double responseTime) {
    responseTimes.putIfAbsent(apiName, new ArrayList<>());
    responseTimes.get(apiName).add(responseTime);
  }
  //记录接口请求的访问时间
  public void recordTimestamp(String apiName, double timestamp) {
    timestamps.putIfAbsent(apiName, new ArrayList<>());
    timestamps.get(apiName).add(timestamp);
  }
  //指定的频率统计数据并输出结果
  public void startRepeatedReport(long period, TimeUnit unit){
    executor.scheduleAtFixedRate(new Runnable() {
      @Override
      public void run() {
        Gson gson = new Gson();
        Map<String, Map<String, Double>> stats = new HashMap<>();
        for (Map.Entry<String, List<Double>> entry : responseTimes.entrySet()) {
          String apiName = entry.getKey();
          List<Double> apiRespTimes = entry.getValue();
          stats.putIfAbsent(apiName, new HashMap<>());
          stats.get(apiName).put("max", max(apiRespTimes));
          stats.get(apiName).put("avg", avg(apiRespTimes));
        }
  
        for (Map.Entry<String, List<Double>> entry : timestamps.entrySet()) {
          String apiName = entry.getKey();
          List<Double> apiTimestamps = entry.getValue();
          stats.putIfAbsent(apiName, new HashMap<>());
          stats.get(apiName).put("count", (double)apiTimestamps.size());
        }
        System.out.println(gson.toJson(stats));
      }
    }, 0, period, unit);
  }

  private double max(List<Double> dataset) {//省略代码实现}
  private double avg(List<Double> dataset) {//省略代码实现}
}
```
下面是如何用上面的功能来实现最简单的统计注册、登陆接口的响应时间和访问次数
```
//应用场景：统计下面两个接口(注册和登录）的响应时间和访问次数
public class UserController {
  private Metrics metrics = new Metrics();
  
  public UserController() {
    metrics.startRepeatedReport(60, TimeUnit.SECONDS);
  }

  public void register(UserVo user) {
    long startTimestamp = System.currentTimeMillis();
    metrics.recordTimestamp("regsiter", startTimestamp);
    //...
    long respTime = System.currentTimeMillis() - startTimestamp;
    metrics.recordResponseTime("register", respTime);
  }

  public UserVo login(String telephone, String password) {
    long startTimestamp = System.currentTimeMillis();
    metrics.recordTimestamp("login", startTimestamp);
    //...
    long respTime = System.currentTimeMillis() - startTimestamp;
    metrics.recordResponseTime("login", respTime);
  }
}
```
整个框架就可以分为四个模块
- 数数据采集：采集数据的接口，需要尽可能多的考虑异常情况，直接暴露给开发者，需要考虑易用性
- 存储：负责将采集的数据存储到数据库
- 聚合统计：原始数据进行聚合统计，而且为了支持更多规则，需要代码尽可能的灵活、可扩展
- 显示：负责将统计数据按照格式展示

