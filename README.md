# 工程笔记

## MyBatis:
* Mapper: tk.mybatis, util包下的MyMapper继承通用接口
* PageHelper
* Mybatis
* mybatis-generator-core
> 配合使用时, 所用版本用本项目中的版本

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
```javascript
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

### 7/25

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

> 小问题：展示所有告警信息时, 旧告警未解决, 新告警中没有包含旧报警(实际中, 每个报警在解决之前只出现一次)

下午：
1. 查询告警记录时按最近的时间先展示
2. 对总记录查询设置分页展示, 默认每页展示5条

### 8/2
上午:
1. 删除sys_device_items表中business_status字段; 在warn表中增加warn_level字段表示告警级别;
   新增sys_business表代表业务状态监控项; 同时修改db.migration对应sql
2. 修改对应model
下午：
1. 添加业务状态展示功能

### 8/3
上午：
1. 修改首页显示,将设备再分组
2. 通过``v-if``实现告警数变化时首页对应设备或者业务状况的背景色改变
下午：
优化前端显示: 系统状态, 告警计数等
   
