package online.shixun.demo.elearning;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import online.shixun.demo.elearning.util.IdUtil;

@SpringBootApplication
@MapperScan("online.shixun.demo.elearning.mapper")
public class StartWebApplication {
	public static void main(String[] args) {
		SpringApplication.run(StartWebApplication.class, args);
	}
	
	@Bean
	public IdUtil getIdUtil() {
		return new IdUtil();
	}

}
