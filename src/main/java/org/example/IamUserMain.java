package org.example;

import com.google.common.base.Predicate;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import springfox.documentation.RequestHandler;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
@EnableSwagger2
public class IamUserMain {
    public static void main(String[] args){
        SpringApplication.run(IamUserMain.class,args);
        System.out.println("Hello Spring Security!");
    }

    @Bean
    public Docket allApis() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select().apis((Predicate<RequestHandler>) RequestHandlerSelectors.basePackage("org.example"))
                .build();
    }
}