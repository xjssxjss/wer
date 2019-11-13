package com.wer;

import com.wer.interceptor.WxInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@EnableScheduling
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
@EnableTransactionManagement
@MapperScan("com.wer.dao")
public class WerApplication implements WebMvcConfigurer{
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
		// 多个拦截器组成一个拦截器链
		// addPathPatterns 用于添加拦截规则
		// excludePathPatterns 用户排除拦截
		registry.addInterceptor(new WxInterceptor()).addPathPatterns("/**");
	}
}
