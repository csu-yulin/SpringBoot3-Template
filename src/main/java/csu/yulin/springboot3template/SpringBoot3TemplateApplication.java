package csu.yulin.springboot3template;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("csu.yulin.springboot3template.mapper")
public class SpringBoot3TemplateApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBoot3TemplateApplication.class, args);
    }

}
