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
* 7/16-7/20 
1. 完成后台框架搭建和数据库设计
2. 完成对系统表, 分类表等表格的查询操作并封装成JSON结果返回
3. 完成首页设计, 根据分类划分系统, 并在每个系统下显示所有设备

* 7/23
上午： 完成首页对应于设备的告警数显示(前端和后台)
下午： 详情页显示，根据IP找出所有对应的告警信息

* 7/24
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
下午： 完成手动修复时warn表格的更新以及对应sys_device_items表的插入记录
