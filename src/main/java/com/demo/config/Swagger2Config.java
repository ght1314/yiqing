package com.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class Swagger2Config {

    @Bean
    public Docket docket(Environment environment){
        //使用swagger 的生产环境
        Profiles   profiles = Profiles.of("dev","test");

        //获取项目的当前环境
        boolean flag = environment.acceptsProfiles(profiles);

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                //是否开启Swagger 默认true 开启,现在根据生产环境配置
                .enable(flag)
                //配置Api分组 多个分组配置多个Docket即可
                .groupName("Fluency")
                //使用select 后面就需要使用build，select和build是一套
                .select()
                .apis(RequestHandlerSelectors.any())
                //过滤的路径
                .paths(PathSelectors.any())
                .build();

    }

    //配置Swagger信息
    private ApiInfo apiInfo(){

        Contact contact = new Contact("Fluency","","755645457@qq.com");
        return  new ApiInfoBuilder()
                .title("社区流动人员管理系统")
                .description("用于疫情期间社区人员流动及体温管理")
                .contact(contact)
                .version("1.0")
                .build();

    };

}
