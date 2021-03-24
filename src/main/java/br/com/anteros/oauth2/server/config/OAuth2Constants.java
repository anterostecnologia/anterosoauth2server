package br.com.anteros.oauth2.server.config;

public class OAuth2Constants {

	public static final String OAUTH2_CONFIGURATION_PROPERTIES = "WEB-INF/anterosConfiguration.properties";
	
	public static  final String ANTEROS_USE_BEAN_VALIDATION = "${anteros.use.bean.validation}";

	public static  final String ANTEROS_LOCK_TIMEOUT = "${anteros.lock.timeout}";

	public static  final String ANTEROS_DDL_DROPTABLES_FILENAME = "${anteros.ddl.droptables.filename}";

	public static  final String ANTEROS_DDL_CREATETABLES_FILENAME = "${anteros.ddl.createtables.filename}";

	public static  final String ANTEROS_DDL_APPLICATION_LOCATION = "${anteros.ddl.application.location}";

	public static  final String ANTEROS_DDL_OUTPUT_MODE = "${anteros.ddl.output.mode}";

	public static  final String ANTEROS_SCRIPT_DDL_GENERATION = "${anteros.script.ddl.generation}";

	public static  final String ANTEROS_DATABASE_DDL_GENERATION = "${anteros.database.ddl.generation}";

	public static  final String ANTEROS_DEFAULT_SCHEMA = "${anteros.defaultSchema}";

	public static  final String ANTEROS_FORMATSQL = "${anteros.formatsql}";

	public static  final String ANTEROS_SHOWSQL = "${anteros.showsql}";

	public static  final String ANTEROS_DIALECT = "${anteros.dialect}";
	
	public static  final String EJABBERD_HOSTNAME = "${ejabberd.hostname}";
	
	public static  final String EJABBERD_USERNAME = "${ejabberd.username}";
	
	public static  final String EJABBERD_PASSWORD = "${ejabberd.password}";
	
	public static  final String EJABBERD_HOSTNAME_CHAT = "sendsales.com.br";

	public static  final String JDBC_PASSWORD = "${jdbc.password}";

	public static  final String JDBC_USERNAME = "${jdbc.username}";

	public static  final String JDBC_URL = "${jdbc.url}";

	public static  final String JDBC_DRIVER_CLASS_NAME = "${jdbc.driverClassName}";
	
	public static  final String PACKAGE_SCAN_ENTITY = "br.com.anteros.security.store.sql.domain*";
	
	public static  final String SECURITY_PACKAGE_SCAN_ENTITY = "br.com.anteros.security.store.sql.domain*";

	public static  final String TRANSACTION_MANAGER = "transactionManager";

	public static  final String ADMIN_NEEDS_PERMISSION = "${admin.needs.permission}";

	public static  final String PACKAGE_SCAN_SECURITY = "${package.scan.security}";

	public static  final String SYSTEM_VERSION = "${system.version}";

	public static  final String SYSTEM_DESCRIPTION = "${system.description}";

	public static  final String SYSTEM_NAME = "${system.name}";

	public static  final String SECURITY_STORE_COMPONENTS = "br.com.anteros.security.store.sql";

	public static  final String SECURITY_COMPONENTS = "br.com.anteros.security.spring";
	
	public static  final String SESSION_FACTORY_SQL = "sessionFactorySQL";

	public static  final String SECURITY_SESSION_FACTORY = "securitySessionFactory";

	public static  final String TRANSACTION_MANAGER_SQL = "transactionManagerSQL";
}
