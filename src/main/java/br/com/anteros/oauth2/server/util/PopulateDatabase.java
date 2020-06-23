package br.com.anteros.oauth2.server.util;

import java.util.Arrays;
import java.util.HashSet;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.com.anteros.nosql.persistence.metadata.configuration.AnterosNoSQLProperties;
import br.com.anteros.nosql.persistence.mongodb.session.MongoSession;
import br.com.anteros.nosql.persistence.session.NoSQLSessionFactory;
import br.com.anteros.nosql.persistence.session.configuration.impl.AnterosNoSQLPersistenceConfiguration;
import br.com.anteros.security.store.mongo.domain.Client;
import br.com.anteros.security.store.mongo.domain.User;

public class PopulateDatabase {

	
	public static void main(String[] args) throws Exception {
		NoSQLSessionFactory sessionFactory = AnterosNoSQLPersistenceConfiguration
				.newConfiguration()
				.addProperty(AnterosNoSQLProperties.DIALECT, "br.com.anteros.nosql.persistence.mongodb.dialect.MongoDialect")
//				.addProperty(AnterosNoSQLProperties.CONNECTION_HOST, "ec2-54-233-133-27.sa-east-1.compute.amazonaws.com")
//				.addProperty(AnterosNoSQLProperties.CONNECTION_HOST, "vps4657.publiccloud.com.br")
//				.addProperty(AnterosNoSQLProperties.CONNECTION_HOST, "10.8.0.1")
				.addProperty(AnterosNoSQLProperties.CONNECTION_HOST, "10.0.223.235")
				.addProperty(AnterosNoSQLProperties.CONNECTION_PORT, "27017")
				.addProperty(AnterosNoSQLProperties.DATABASE_NAME, "anteros_oauth_server")
				.addProperty(AnterosNoSQLProperties.CONNECTION_USER, "anteros")
				.addProperty(AnterosNoSQLProperties.CONNECTION_PASSWORD, "Anteros@a1b2c3d4e5")
//				.addProperty(AnterosNoSQLProperties.CONNECTION_USER, "produtec")
//				.addProperty(AnterosNoSQLProperties.CONNECTION_PASSWORD, "Prd@sales890035!77oauth2")
				.packageScanEntity("br.com.anteros.security.store.mongo.domain").includeSecurityModel(true)
				.withoutTransactionControl(true)
				.buildSessionFactory();
		
		MongoSession session = (MongoSession) sessionFactory.getCurrentSession();
		
		session.getTransaction().begin();
		
		Client client = new Client();
		
		String password = "senha_secreta";
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String hashedPassword = passwordEncoder.encode(password);
		
//		client.setClientSecret(hashedPassword);
//		client.setClientDescription("Versatil Parking");
//		client.setClientId("versatil-parking");
//		client.setAuthorizedGrantTypes(new HashSet<String>(Arrays.asList(new String[] {"password",
//                        "refresh_token", "implicit", "client_credentials", "authorization_code"})));
//		client.setScope(new HashSet<String>(Arrays.asList(new String[] {"read",
//                "write"})));
//		session.save(client);
		
		
		User user = new User();
		user.setBoAdministrator(true);
		user.setDescription("Administrador");
		user.setLogin("admin");
		user.setPassword("admin1234");
		user.setName("admin");
		session.save(user);
//		
//		user = new User();
//		user.setBoAdministrator(true);
//		user.setDescription("Administrador");
//		user.setLogin("AMARILDOCARAPELLI");
//		user.setPassword("ef3iRT");
//		user.setName("AMARILDO CARAPELLI");
//		session.save(user);
//		
//		user = new User();
//		user.setBoAdministrator(true);
//		user.setDescription("Administrador");
//		user.setLogin("IRACILIMA");
//		user.setPassword("aMJi8T");
//		user.setName("IRACI LIMA");
//		session.save(user);
//		
//		user = new User();
//		user.setBoAdministrator(true);
//		user.setDescription("Administrador");
//		user.setLogin("ADERBALPERI");
//		user.setPassword("GAUS4J");
//		user.setName("ADERBAL PERI");
//		session.save(user);
//		
//		user = new User();
//		user.setBoAdministrator(true);
//		user.setDescription("Administrador");
//		user.setLogin("ADAOLUIZ");
//		user.setPassword("8pWAk9");
//		user.setName("ADAO LUIZ");
//		session.save(user);
//		
//		user = new User();
//		user.setBoAdministrator(true);
//		user.setDescription("Administrador");
//		user.setLogin("LUIZFONDAZZI");
//		user.setPassword("N2QmCw");
//		user.setName("LUIZ FONDAZZI");
//		session.save(user);
	}
}
