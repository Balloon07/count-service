# count-service 计次服务
提供通用的计次能力，支持活动、奖品、任务等场景。


## 一、目标
计次服务支持多维度（用户id/手机号/身份证号/设备号等）、多周期（相对/绝对周期、2天3次/每周1次等）的计次能力。

以活动上奖品领取限制举例，原型设计如下：
![](resource/计次-原型设计图.jpg)

## 二、整体架构
![](resource/计次-服务架构图.jpg)

## 三、存储设计
B测计次配置count_config 和 C测计次流水count_record、count_cycle：
- count_config 计次配置表：主要存储计次id和计次规则；
- count_record 计次流水表：用于存储计次流水，和做幂等判断；
- count_cycle 计次周期表：是用户在某计次下流水的汇总，用于计次查询和准入的判断；

其中，count_record表和count_cycle表以dimension_id字段进行分库分表，来满足大数据量的存储。
![](resource/计次-数据模型设计v2.jpg)
Refer：https://www.processon.com/v/6571657440125a40fd13310b


## 四、流程设计
计次服务包括的核心功能：创建计次配置、计次查询、计次准入和计次逆向。
### 1. 创建计次配置
![](resource/计次-创建计次配置流程.jpg)
### 2. 计次查询
![](resource/计次-计次查询流程.jpg)
### 3. 计次准入
![](resource/计次-计次准入流程.jpg)
### 4. 计次逆向
![](resource/计次-计次逆向流程.jpg)


## 五、接口定义

B侧接口定义：CountConfigApi
```
/**
 * 生成计次配置id
 *
 * @param countType
 * @return
 */
Result<String> generateCountId(String countType);

/**
 * 创建计次配置
 *
 * @param param
 * @return
 */
Result<String> createCountConfig(CountConfigParam param);

/**
 * 查询计次配置
 *
 * @param countId
 * @return
 */
Result<CountConfigDto> queryCountConfig(String countId);
```

C侧接口定义：CountServiceApi
```
/**
 * 计次查询
 *
 * @param param
 * @return
 */
Result<CountQueryDto> queryCount(CountQueryParam param);

/**
 * 计次准入
 *
 * @param param
 * @return
 */
Result<CountInsertDto> insertCount(CountInsertParam param);

/**
 * 计次逆向
 *
 * @param param
 * @return
 */
Result<CountRollbackDto> rollbackCount(CountRollbackParam param);

/**
 * 用户计次纪录查询
 *
 * @param param
 * @return
 */
Result<CountUserRecordDto> queryUserCountRecord(CountUserRecordParam param);
```

## 六、压力测试
todo

## 七、快速开始
count-service 计次服务快速开始：
1. clone当前项目到本地；
2. 本地执行`resource/计次-mysql.sql`sql脚本文件；
2. 修改`application.properties`中mysql配置；
3. 启动本地项目； 
4. 进行`InnerCountConfigController`、`BizCountServiceController`BC侧功能测试。


## 八、开发进度

B测功能：✅
- count-api/app项目分层设计  ✈️
- 数据库实体类和对应mapper  ✈️
- B侧api定义  ✈️
- 生成计次配置id  ✈️
- count_config数据源配置  ✈️
- repository层语义转换  ✈️
- 创建计次配置实现  ✈️
- B侧api功能自测 ✈️


C测功能：
- C侧api定义 ✈️
- count_user数据源配置
- repository语义转换
- sharding分库分表
- 计次查询
- 计次准入
- 计次逆向
- 用户计次纪录查询
- C侧功能自测
- 压力测试

