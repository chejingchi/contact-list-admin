package com.michael.contactlist;

import org.nutz.plugin.spring.boot.config.SqlManagerProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author chejingchi
 *         创建时间:2017/4/1 下午4:42
 *         项目名称:contactList
 * @author 车竞驰
 * @version 1.0
 * @since JDK 1.8
 * 类说明:
 */

@SpringBootApplication
@EnableAutoConfiguration
@EnableConfigurationProperties(SqlManagerProperties.class)
@RestController
public class App {
    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(App.class);
        application.run(args);
    }
}
