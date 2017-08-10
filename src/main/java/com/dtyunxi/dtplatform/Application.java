package com.dtyunxi.dtplatform;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

/**
 * @author 作者 E-mail: tan.jie@dtyunxi.com
 * @version 创建时间：2017年8月10日 下午9:36:32 类说明
 */
@SpringBootApplication
@ImportResource(locations = { "classpath:spring/spring-captcha.xml"})
public class Application {
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
