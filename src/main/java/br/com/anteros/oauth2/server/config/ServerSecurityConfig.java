package br.com.anteros.oauth2.server.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import br.com.anteros.nosql.persistence.session.NoSQLSessionFactory;
import br.com.anteros.security.spring.AnterosSecurityManager;

@Configuration
@EnableWebSecurity
@Order(Integer.MAX_VALUE - 7)
@Import({ Encoders.class })
@ComponentScan({ "br.com.anteros.security.spring", "br.com.anteros.security.store.mongo" })
@PropertySource("WEB-INF/anterosConfiguration.properties")
public class ServerSecurityConfig extends WebSecurityConfigurerAdapter {

	@Value("${system.name}")
	private String systemName;

	@Value("${system.description}")
	private String description;

	@Value("${system.version}")
	private String version;

	@Autowired
	private PasswordEncoder userPasswordEncoder;

	@Autowired
	private AnterosSecurityManager authenticationManager;

	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		authenticationManager.setSystemName(systemName);
		authenticationManager.setDescription(description);
		authenticationManager.setVersion(version);
		auth.userDetailsService(authenticationManager).passwordEncoder(userPasswordEncoder);
	}


	@Bean(name = "securitySessionFactory")
	public NoSQLSessionFactory getSecuritySessionFactory(
			@Autowired @Qualifier("sessionFactoryNoSQL") NoSQLSessionFactory sessionFactoryNoSQL) {
		return sessionFactoryNoSQL;
	}
	
	@Override
    protected void configure(HttpSecurity http) throws Exception {
    	http.cors().and()
		.csrf().disable().authorizeRequests().anyRequest().permitAll();	    	
    }

	@Override
	public void configure(WebSecurity web) throws Exception {
		super.configure(web);
		web.ignoring()
        .antMatchers(HttpMethod.OPTIONS);
	}
	
	

}
