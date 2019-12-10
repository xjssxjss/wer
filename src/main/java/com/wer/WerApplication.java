package com.wer;

import com.wer.interceptor.WxInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@EnableScheduling
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
@EnableTransactionManagement
@MapperScan("com.wer.dao")
public class WerApplication extends SpringBootServletInitializer implements WebMvcConfigurer{
	private static Logger logger = LoggerFactory.getLogger(WerApplication.class);

	public static void main(String[] args) {
		logger.info("wer启动开始>>>>>>>>>>>>>>>>>>>>>>");
		SpringApplication.run(WerApplication.class, args);
		logger.info("wer启动结束>>>>>>>>>>>>>>>>>>>>>>");
	}

	/**
	 * 全局拦截器
	 * @param registry
	 */
	public void addInterceptors(InterceptorRegistry registry) {
		//添加拦截器
		InterceptorRegistration registration = registry.addInterceptor(new WxInterceptor());
		// 多个拦截器组成一个拦截器链
		// addPathPatterns 用于添加拦截规则
		// excludePathPatterns 用户排除拦截
		//排除的路径
		registration.excludePathPatterns("/indexController");

		//静态文件不拦截
		registration.excludePathPatterns("/index.html","/","/**/*.css",
				"/**/*.js", "/**/*.png", "/**/*.jpg",
				"/**/*.jpeg", "/**/*.gif", "/**/fonts/*");

		//拦截全部
		registration.addPathPatterns("/**");
	}

	@Override
	protected final SpringApplicationBuilder configure(final SpringApplicationBuilder application) {
		return application.sources(WerApplication.class);
	}

	/**
	 * springboot 解决跨域访问问题
	 * @return
	 */
	@Bean
	public FilterRegistrationBean corsFilter(){
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		CorsConfiguration config = new CorsConfiguration();
		config.setAllowCredentials(true);
		ArrayList<String> objects = new ArrayList<>();
		objects.add("*");
		config.setAllowedOrigins(objects);
		config.setAllowedHeaders(objects);
		config.setAllowedMethods(objects);
//        source.registerCorsConfiguration("/**", config);
		Map<String, CorsConfiguration> corsConfigurations = new HashMap<>();
		corsConfigurations.put("/**",config);
		source.setCorsConfigurations(corsConfigurations);
		FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter(source));
		bean.setOrder(0);
		return bean;
	}
}
