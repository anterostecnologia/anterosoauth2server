package br.com.anteros.oauth2.server.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.annotation.Order;
import org.springframework.transaction.PlatformTransactionManager;

import br.com.anteros.nosql.persistence.session.NoSQLSessionFactory;
import br.com.anteros.nosql.persistence.session.ShowCommandsType;
import br.com.anteros.nosql.persistence.session.configuration.PackageScanEntity;
import br.com.anteros.nosql.spring.config.AbstractSpringNoSQLPersistenceConfiguration;
import br.com.anteros.nosql.spring.config.NoSQLDataSourceConfiguration;
import br.com.anteros.nosql.spring.config.NoSQLSessionFactoryConfiguration;
import br.com.anteros.nosql.spring.transaction.AnterosNoSQLTransactionManager;

@Configuration
@PropertySource("WEB-INF/anterosConfiguration.properties")
public class SecurityPersistenceConfiguration extends AbstractSpringNoSQLPersistenceConfiguration {

	@Value("${system.name}")
	private String systemName;

	@Value("${system.description}")
	private String description;

	@Value("${system.version}")
	private String version;

	@Value("${admin.needs.permission}")
	private String adminNeedsPermission;

	@Value("${package.scan.security}")
	private String packageToScanSecurity;
	
	@Value("${mongo.hostname}")
	private String hostName;
	
	@Value("${mongo.port}")
	private Integer port;

	@Value("${mongo.databasename}")
	private String databaseName;

	@Value("${mongo.username}")
	private String userName;

	@Value("${mongo.password}")
	private String password;
	
	@Value("${mongo.dialect}")
	private String dialect;

	@Override
	public NoSQLDataSourceConfiguration getDataSourceConfiguration() {
		return NoSQLDataSourceConfiguration.create().host(hostName).port(port).databaseName(databaseName)
				.userName(userName).password(password);
	}

	@Override
	public NoSQLSessionFactoryConfiguration getSessionFactoryConfiguration() {
		return NoSQLSessionFactoryConfiguration.create()
				.dialect(dialect).databaseName(databaseName)	
				.withoutTransactionControl(true)
				.packageScanEntity(PackageScanEntity.of(packageToScanSecurity)).includeSecurityModel(true)
				.showCommands(ShowCommandsType.ALL).formatCommands(true);
	}

	@Autowired
	@Bean(name = "transactionManagerNoSQL")
	public PlatformTransactionManager txManager(NoSQLSessionFactory sessionFactoryNoSQL) {
		if (sessionFactoryNoSQL != null) {
			AnterosNoSQLTransactionManager txManager = new AnterosNoSQLTransactionManager();
			txManager.setSessionFactory(sessionFactoryNoSQL);
			return txManager;
		}
		return null;
	}

}
