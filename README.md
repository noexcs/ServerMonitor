# 云服务器性能监控项目

基于大数据的云服务器性能监测及告警项目主要实现Linux主机应用性能及联通性验证。编写主机监控Shell脚本，获取Linux系统的：CPU使用率，内存⼤⼩及使用率，磁盘总⼤⼩及使用率，配置定时任务所有脚本10秒采集⼀次数据，Shell脚本将获取到的信息封装为JSON存入Kafka流处理集群(削峰填谷)，进而存入Mysql数据库中。通过后端技术栈（Spring Boot等）实现用户系统进行报表查看以及告警规则设置。前端页面通过后端处理的数据进行页面渲染和Echarts数据可视化图表展示，进而实现Linux主机性能监测展示及联通性验证。

## 架构总览

![image](https://github.com/noexcs/ServerMonitor/raw/master/image/Architecture.jpg)

