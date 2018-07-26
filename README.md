# 工程笔记

## MyBatis:
* Mapper: tk.mybatis, util包下的MyMapper继承通用接口
* PageHelper
* Mybatis
* mybatis-generator-core
> 陪着使用时, 所用版本用本项目中的版本

## 解决跨域请求
* 只需配置: io.haitaoc.config.CrosConfig

## FlyWay:
* 需要在pom中添加``flyway-core``的依赖并在yaml中配置

## JSON结果处理:
* util 包下的JSONResult

## 定时任务:
* cron表达式: cron.qqe2.com

## Redis:
* Redis Desktop Manager

# 项目进度
### 7/16-7/20 
1. 完成后台框架搭建和数据库设计
2. 完成对系统表, 分类表等表格的查询操作并封装成JSON结果返回
3. 完成首页设计, 根据分类划分系统, 并在每个系统下显示所有设备

### 7/23

上午： 
完成首页对应于设备的告警数显示(前端和后台)
下午： 
详情页显示，根据IP找出所有对应的告警信息

### 7/24

上午： 点击侧边栏不同系统过滤出对应系统对应的设备信息
> 碰到问题：同一个组件传不同参数, 组件内容不刷新, 解决方式：
```html
<router-view :key="key"></router-view>
```
```
    computed: {
      key(){
        return this.$route.name !== undefined? this.$route.name +new Date(): this.$route +new Date();
      }
    }
    // 这种方式也有个小问题，如果连续点击，new Date()相同，导致不刷新
    // 所以可以在data里设置一个count,每次点击都让count改变
    //改为： 
    key(){
            return this.$route.name !== undefined? this.$route.name +(this.count++): this.$route +(this.count++)
          }
```
下午：
 完成手动修复时warn表格的更新以及对应sys_device_items表的插入记录

###7/25

上午：
1. LocalDatetime等新时间包中的类的精确值是纳秒(nano second)
1 毫秒 = 1*10^6 纳秒
2. 换算时通过LocalDatetime.withNano(要精确的毫秒*10^6)得到毫秒精确到毫秒的LocalDatetime
3. 通过PrintWriter往文件中追加写内容, 需要将FileOutputStream(f,true)中的第二个参数设置为true, 表示追加

下午：
1. 完成LocalDateTime与String互转
2. 完成定时写入文件告警与定时读取告警
3. 完成在定时任务中将告警信息以及设备监控信息插入到对应表中

### 7/26
上午：
1. 优化定时任务, 重置批量操作的条件: **未读到文件结尾 且 列表大小不为批量插入大小**
2. 随机化插入告警异常数据
3. 通过flyway插入正常记录
