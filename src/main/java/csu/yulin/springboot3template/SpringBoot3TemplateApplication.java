package csu.yulin.springboot3template;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.transaction.annotation.EnableTransactionManagement;

// 这里禁用redis会影响到RedisConfig类
//@SpringBootApplication(exclude = {RedisAutoConfiguration.class})
@SpringBootApplication
@MapperScan("csu.yulin.springboot3template.mapper")
@EnableTransactionManagement
@EnableCaching
public class SpringBoot3TemplateApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBoot3TemplateApplication.class, args);
    }
}
