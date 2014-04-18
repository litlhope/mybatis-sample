package hejairi.sample.spring.mybatis;

import com.googlecode.flyway.core.Flyway;
import com.googlecode.flyway.core.api.FlywayException;
import com.googlecode.flyway.core.api.MigrationVersion;
import hejairi.sample.spring.mybatis.core.mybatis.Mapper;
import org.apache.commons.dbcp.BasicDataSource;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**
 * Created by litlhope on 2014. 4. 15..
 */
@Configuration
@EnableTransactionManagement
@MapperScan(basePackages = "hejairi.sample.spring.mybatis.module", annotationClass = Mapper.class)
public class DatabaseConfig implements InitializingBean {

	private final static Logger log = LoggerFactory.getLogger(DatabaseConfig.class);

	@Autowired
	private ConnectionSettings connectionSettings;

//	@Autowired
//	private DataSource dataSource;

	@Bean(destroyMethod = "close")
	public DataSource dataSource() {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName(connectionSettings.getDriver());
		dataSource.setUrl(connectionSettings.getUrl());
		dataSource.setUsername(connectionSettings.getUsername());
		dataSource.setPassword(connectionSettings.getPassword());
		dataSource.setMaxIdle(connectionSettings.getMaxIdle());
		dataSource.setMaxActive(connectionSettings.getMaxActive());
		dataSource.setMaxWait(connectionSettings.getMaxWait());
		dataSource.setValidationQuery(connectionSettings.getValidationQuery());

		return dataSource;
	}

	@Bean
	public DataSourceTransactionManager transactionManager() {
		return new DataSourceTransactionManager(dataSource());
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		log.info("afterPropertiesSet");
		Flyway flyway = new Flyway();
		flyway.setDataSource(dataSource());
		flyway.setLocations("classpath:db/migration");
		flyway.setSqlMigrationPrefix("V");
		flyway.setSqlMigrationSuffix(".sql");
		flyway.setEncoding("UTF-8");
		// Init
		if (!flyway.isInitOnMigrate()) {
			log.info("isInitOnMigrate: false");
			try {
				flyway.init();
				log.info("flyway init");
			} catch (FlywayException ex) {
				log.warn("", ex);
			}
		}
		
		// Migration
		MigrationVersion version = flyway.getTarget();
		log.info("compareTo: " + version.compareTo(flyway.getInitVersion()));
		if (version.compareTo(flyway.getInitVersion()) > 0) {
			try {
				flyway.migrate();
			} catch (FlywayException ex) {
				log.warn("", ex);
			}
		}
	}
}
