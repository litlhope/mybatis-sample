package hejairi.sample.spring.mybatis;

import hejairi.sample.spring.mybatis.core.mybatis.Mapper;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.spi.LoggerFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.io.IOException;

/**
 * Created by litlhope on 2014. 4. 16..
 */
@Configuration
@EnableTransactionManagement
@MapperScan(basePackages = "hejairi.sample.spring.mybatis.module", annotationClass = Mapper.class)
public class MyBatisConfig {
	
	@Bean
	public SqlSessionFactoryBean sqlSessionFactoryBean(
			DataSource dataSource, ApplicationContext applicationContext) throws IOException {
		final SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();

		// DataSource 등록
		factoryBean.setDataSource(dataSource);

		// MyBatis 설정파일 위치 설정
		factoryBean.setConfigLocation(applicationContext.getResource("classpath:META-INF/mybatis/configuration.xml"));

		// hejairi.sample.spring.mybatis.model 패키지 이하의 model 클래스 이름을 짧은 별칭으로 등록
		factoryBean.setTypeAliasesPackage("hejairi.sample.spring.mybatis.model");

		// META-INF/mybatis/mappers 이하의 모든 XML을 Mapper로 등록
		factoryBean.setMapperLocations(applicationContext.getResources("classpath:META-INF/mybatis/mappers/**/*.xml"));

		return factoryBean;
	}

	@Bean
	public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
		return new SqlSessionTemplate(sqlSessionFactory);
	}

}
