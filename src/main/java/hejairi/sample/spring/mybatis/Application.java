package hejairi.sample.spring.mybatis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by litlhope on 2014. 4. 15..
 */
@Configuration
@EnableAutoConfiguration
@ComponentScan
public class Application implements CommandLineRunner {
	private static Logger log = LoggerFactory.getLogger(Application.class);

	@Autowired
	private ConnectionSettings connectionSettings;

	@Override
	public void run(String... args) throws Exception {

		log.info("Driver: " + connectionSettings.getDriver());
		log.info("URL: " + connectionSettings.getUrl());
	}

	public static void main(String[] args) throws Exception {
		SpringApplication.run(Application.class, args);
		log.info("mybatis-sample started!");
	}
}
