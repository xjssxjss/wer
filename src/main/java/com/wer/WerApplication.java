package com.wer;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableScheduling
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
@EnableTransactionManagement
@MapperScan("com.wer.dao")
public class WerApplication {
	private static Logger logger = LoggerFactory.getLogger(WerApplication.class);

	public static void main(String[] args) {
		logger.info("wer启动开始>>>>>>>>>>>>>>>>>>>>>>");
		SpringApplication.run(WerApplication.class, args);
		logger.info("wer启动结束>>>>>>>>>>>>>>>>>>>>>>");
	}
}
