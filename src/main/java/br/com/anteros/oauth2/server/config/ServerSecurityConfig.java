package br.com.anteros.oauth2.server.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

import br.com.anteros.nosql.persistence.session.NoSQLSessionFactory;
import br.com.anteros.security.spring.AnterosSecurityManager;

@Configuration
@EnableWebSecurity
@Order(Integer.MAX_VALUE-7)
@Import({Encoders.class})
@ComponentScan({"br.com.anteros.security.spring", "br.com.anteros.security.store.mongo"})
public class ServerSecurityConfig extends WebSecurityConfigurerAdapter {
	
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
        auth.userDetailsService(authenticationManager).passwordEncoder(userPasswordEncoder);
    }
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	http.authorizeRequests().anyRequest().permitAll();
    }   
	

	@Bean(name="securitySessionFactory")
	public NoSQLSessionFactory getSecuritySessionFactory(@Autowired @Qualifier("sessionFactoryNoSQL") NoSQLSessionFactory sessionFactoryNoSQL) {
		return sessionFactoryNoSQL;
	}
	
	
}



