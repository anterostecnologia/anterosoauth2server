package br.com.anteros.oauth2.server.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.ResourceHttpMessageConverter;
import org.springframework.http.converter.ResourceRegionHttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.support.AllEncompassingFormHttpMessageConverter;
import org.springframework.http.converter.xml.SourceHttpMessageConverter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import br.com.anteros.persistence.session.SQLSessionFactory;
import br.com.anteros.security.spring.config.AbstractSpringMvcConfiguration;
import br.com.anteros.spring.web.converter.AnterosHttpMessageConverter;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = { "br.com.anteros.oauth2" })
public class ServerMvcConfiguration extends AbstractSpringMvcConfiguration {

	@Autowired
	private SQLSessionFactory sessionFactorySQL;

	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		StringHttpMessageConverter stringHttpMessageConverter = new StringHttpMessageConverter();
		stringHttpMessageConverter.setWriteAcceptCharset(false); // see SPR-7316
		converters.add(new ByteArrayHttpMessageConverter());
		converters.add(stringHttpMessageConverter);
		converters.add(new ResourceHttpMessageConverter());
		converters.add(new ResourceRegionHttpMessageConverter());
		converters.add(new SourceHttpMessageConverter<>());
		converters.add(new AllEncompassingFormHttpMessageConverter());
		converters.add(new AnterosHttpMessageConverter(sessionFactorySQL));

	}

}
