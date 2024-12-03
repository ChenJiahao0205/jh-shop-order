package pers.jhshop.order;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@MapperScan(basePackages = "pers.jhshop.order.mapper")
@SpringBootApplication
public class JhShopOrderServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(JhShopOrderServiceApplication.class, args);
    }

}
