package br.com.anteros.oauth2.server.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import br.com.anteros.nosql.persistence.session.NoSQLSessionFactory;
import br.com.anteros.nosql.spring.web.converter.AnterosNoSQLHttpMessageConverter;
import br.com.anteros.security.spring.config.AbstractSpringMvcConfiguration;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = { "br.com.anteros.oauth2" })
public class ServerMvcConfiguration extends AbstractSpringMvcConfiguration {

	@Autowired
	private NoSQLSessionFactory sessionFactoryNoSQL;

	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		converters.add(new AnterosNoSQLHttpMessageConverter(sessionFactoryNoSQL));
	}

}
