package br.com.anteros.oauth2.server.config;

import static br.com.anteros.oauth2.server.config.OAuth2Constants.ANTEROS_DATABASE_DDL_GENERATION;
import static br.com.anteros.oauth2.server.config.OAuth2Constants.ANTEROS_DDL_APPLICATION_LOCATION;
import static br.com.anteros.oauth2.server.config.OAuth2Constants.ANTEROS_DDL_CREATETABLES_FILENAME;
import static br.com.anteros.oauth2.server.config.OAuth2Constants.ANTEROS_DDL_DROPTABLES_FILENAME;
import static br.com.anteros.oauth2.server.config.OAuth2Constants.ANTEROS_DDL_OUTPUT_MODE;
import static br.com.anteros.oauth2.server.config.OAuth2Constants.ANTEROS_DEFAULT_SCHEMA;
import static br.com.anteros.oauth2.server.config.OAuth2Constants.ANTEROS_DIALECT;
import static br.com.anteros.oauth2.server.config.OAuth2Constants.ANTEROS_FORMATSQL;
import static br.com.anteros.oauth2.server.config.OAuth2Constants.ANTEROS_LOCK_TIMEOUT;
import static br.com.anteros.oauth2.server.config.OAuth2Constants.ANTEROS_SCRIPT_DDL_GENERATION;
import static br.com.anteros.oauth2.server.config.OAuth2Constants.ANTEROS_SHOWSQL;
import static br.com.anteros.oauth2.server.config.OAuth2Constants.ANTEROS_USE_BEAN_VALIDATION;
import static br.com.anteros.oauth2.server.config.OAuth2Constants.JDBC_DRIVER_CLASS_NAME;
import static br.com.anteros.oauth2.server.config.OAuth2Constants.JDBC_PASSWORD;
import static br.com.anteros.oauth2.server.config.OAuth2Constants.JDBC_URL;
import static br.com.anteros.oauth2.server.config.OAuth2Constants.JDBC_USERNAME;
import static br.com.anteros.oauth2.server.config.OAuth2Constants.SECURITY_PACKAGE_SCAN_ENTITY;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import br.com.anteros.cloud.integration.filesharing.CloudFileManager;
import br.com.anteros.persistence.session.configuration.PackageScanEntity;
import br.com.anteros.persistence.session.query.ShowSQLType;
import br.com.anteros.spring.config.AbstractSQLPersistenceConfiguration;
import br.com.anteros.spring.config.PooledDataSourceConfiguration;
import br.com.anteros.spring.config.SQLSessionFactoryConfiguration;
import br.com.anteros.spring.config.SingleDataSourceConfiguration;
import br.com.anteros.spring.config.ViburDataSourceConfiguration;



@Configuration
@PropertySource("WEB-INF/anterosConfiguration.properties")
public class SecurityPersistenceConfiguration extends AbstractSQLPersistenceConfiguration {

	@Value(JDBC_DRIVER_CLASS_NAME)
	private String driverClass;

	@Value(JDBC_URL)
	private String jdbcUrl;

	@Value(JDBC_USERNAME)
	private String user;

	@Value(JDBC_PASSWORD)
	private String password;

	@Value(ANTEROS_DIALECT)
	private String dialect;

	@Value(ANTEROS_SHOWSQL)
	private String showSql = "false";

	@Value(ANTEROS_FORMATSQL)
	private String formatSql = "true";

	@Value(ANTEROS_DEFAULT_SCHEMA)
	private String defaultSchema = "";

	@Value(ANTEROS_DATABASE_DDL_GENERATION)
	private String databaseDDLGeneration = "none";

	@Value(ANTEROS_SCRIPT_DDL_GENERATION)
	private String scriptDDLGeneration = "none";

	@Value(ANTEROS_DDL_OUTPUT_MODE)
	private String ddlOutputMode = "none";

	@Value(ANTEROS_DDL_APPLICATION_LOCATION)
	private String applicationLocation = "";

	@Value(ANTEROS_DDL_CREATETABLES_FILENAME)
	private String createTablesFileName = "";

	@Value(ANTEROS_DDL_DROPTABLES_FILENAME)
	private String dropTablesFileName = "";

	@Value(ANTEROS_LOCK_TIMEOUT)
	private Long lockTimeout = 0L;

	@Value(ANTEROS_USE_BEAN_VALIDATION)
	private Boolean useBeanValidation = true;


	@Override
	public PooledDataSourceConfiguration getPooledDataSourceConfiguration() {
		ViburDataSourceConfiguration result = (ViburDataSourceConfiguration) ViburDataSourceConfiguration
				.of(driverClass, jdbcUrl, user, password).maxPoolSize(Integer.valueOf(100))
				.minPoolSize(Integer.valueOf(10)).preferredTestQuery("SELECT 1");
		return result;
	}

	@Override
	public SingleDataSourceConfiguration getSingleDataSourceConfiguration() {
		return null;
	}

	@Override
	public SQLSessionFactoryConfiguration getSQLSessionFactoryConfiguration() {
		return SQLSessionFactoryConfiguration.create()
				.packageScanEntity(new PackageScanEntity(SECURITY_PACKAGE_SCAN_ENTITY)).ddlOutputMode(ddlOutputMode)
				.dialect(dialect).formatSql(Boolean.valueOf(formatSql)).applicationLocation(applicationLocation)
				.createTablesFileName(createTablesFileName).dropTablesFileName(dropTablesFileName)
				.lockTimeout(lockTimeout).includeSecurityModel(true).jdbcSchema(defaultSchema)
				.showSql(ShowSQLType.parse(showSql)).databaseDDLGeneration(databaseDDLGeneration)
				.useBeanValidation(useBeanValidation)
				.scriptDDLGeneration(scriptDDLGeneration).enableImageCompression();
				
	}

}
