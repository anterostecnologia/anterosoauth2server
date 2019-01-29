package br.com.anteros.oauth2.server.config;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;

import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

import br.com.anteros.nosql.spring.web.config.AbstractSpringWebAppInitializer;
import br.com.anteros.nosql.spring.web.config.AnterosCorsFilter;


public class ApplicationWebInitializer extends AbstractSpringWebAppInitializer{

	@Override
	public Class<?> oauth2ServerConfigurationClass() {
		return AuthServerOAuth2Config.class;
	}

	@Override
	public Class<?> resourceServerConfigurationClass() {
		return null;
	}

	@Override
	public Class<?> globalMethodConfigurationClass() {
		return null;
	}

	@Override
	public Class<?> securityConfigurationClass() {
		return ServerSecurityConfig.class;
	}

	@Override
	public Class<?> mvcConfigurationClass() {
		return ServerMvcConfiguration.class;
	}

	@Override
	public Class<?>[] persistenceConfigurationClass() {
		return new Class<?>[]{SecurityPersistenceConfiguration.class};
	}

	@Override
	public Class<?>[] registerFirstConfigurationClasses() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Class<?>[] registerLastConfigurationClasses() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addListener(ServletContext servletContext) {		
		
		
	}

	@Override
	public void addServlet(ServletContext servletContext, AnnotationConfigWebApplicationContext appContext) {
		// TODO Auto-generated method stub
		
	}
	
	
	
}