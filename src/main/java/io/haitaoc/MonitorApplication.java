package io.haitaoc;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@EnableAutoConfiguration
@ComponentScan
// 扫描对应的Dao类
@MapperScan(basePackages = "io.haitaoc.dao")
@EnableScheduling       // 启动定时任务
@SpringBootApplication
@EnableSwagger2         // 启用Swagger2
public class MonitorApplication {
    public static void main(String[] args) {
        SpringApplication.run(MonitorApplication.class,args);
    }

}
