package com.dtyunxi.dtplatform.configuration;

import java.io.IOException;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

/**
 * @author 作者 E-mail: tan.jie@dtyunxi.com
 * @version 创建时间：2017年8月10日 下午10:00:33 类说明
 */
@Configuration
@EnableTransactionManagement
public class MybatisConfiguration implements TransactionManagementConfigurer {
	@Autowired
	private DataSource dataSource;

	@Override
	public PlatformTransactionManager annotationDrivenTransactionManager() {
		return new DataSourceTransactionManager(dataSource);
	}

	@Bean(name = "sqlSessionFactory")
	public SqlSessionFactory sqlSessionFactory() {
		SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
		bean.setDataSource(dataSource);
		bean.setConfigLocation(new ClassPathResource("mybatis/mybatisConfig.xml"));
		bean.setTypeAliasesPackage("com.dtyunxi.dtplatform.domain");
		bean.setMapperLocations(new ClassPathResource[]{new ClassPathResource("mapper/UserMapper.xml")});
		try {
			return bean.getObject();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	@Bean
	public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
		return new SqlSessionTemplate(sqlSessionFactory);
	}
	
	public static void main(String[] args) throws IOException {
		Resource configResource = new ClassPathResource("classpath:mybatisConfig.xml");
		Resource[] mapperResources ={ new ClassPathResource("classpath:UserMapper.xml")};
		

		System.out.println(configResource.getURL());
		System.out.println(mapperResources.toString());
		
	}
}
