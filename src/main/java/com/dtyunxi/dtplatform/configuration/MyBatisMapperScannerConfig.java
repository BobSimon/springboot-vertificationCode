package com.dtyunxi.dtplatform.configuration;

import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author 作者 E-mail: tan.jie@dtyunxi.com
 * @version 创建时间：2017年8月10日 下午10:09:17 扫描mybatis的接口
 */

@Configuration
@AutoConfigureAfter(MybatisConfiguration.class)
public class MyBatisMapperScannerConfig {
	@Bean
	public MapperScannerConfigurer mapperScannerConfigurer() {
		MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();

		mapperScannerConfigurer.setSqlSessionFactoryBeanName("sqlSessionFactory");
		//指定接口位置
		mapperScannerConfigurer.setBasePackage("com.dtyunxi.dtplatform.dao");
		return mapperScannerConfigurer;
	}
}
