package online.shixun.demo.elearning.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

	/**
	 * 配置各种静态资源访问目录
	 * 
	 * @param registry
	 */
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/js/**").addResourceLocations("classpath:/templates/js/").setCachePeriod(3600);
		registry.addResourceHandler("/fonts/**").addResourceLocations("classpath:/templates/fonts/")
				.setCachePeriod(3600);
		registry.addResourceHandler("/images/**").addResourceLocations("classpath:/templates/images/")
				.setCachePeriod(3600);
		registry.addResourceHandler("/css/**").addResourceLocations("classpath:/templates/css/").setCachePeriod(3600);
		registry.addResourceHandler("/plugins/**").addResourceLocations("classpath:/templates/plugins/")
				.setCachePeriod(3600);
		registry.addResourceHandler("/icon/**").addResourceLocations("classpath:/templates/icon/").setCachePeriod(3600);
	}

}
