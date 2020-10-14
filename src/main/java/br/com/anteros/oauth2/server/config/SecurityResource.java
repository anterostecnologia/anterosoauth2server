package br.com.anteros.oauth2.server.config;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.anteros.nosql.persistence.mongodb.query.MongoCriteria;
import br.com.anteros.nosql.persistence.mongodb.query.MongoQuery;
import br.com.anteros.nosql.persistence.session.NoSQLSessionFactory;
import br.com.anteros.nosql.persistence.session.query.NoSQLQuery;
import br.com.anteros.security.store.mongo.domain.User;

@RestController
@RequestMapping(value = "/api.v1/security")
public class SecurityResource {

	@Autowired
	private NoSQLSessionFactory sessionFactoryNoSQL;

	@RequestMapping(value = "/addNewUser", method = { RequestMethod.POST, RequestMethod.PUT })
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public void addNewUser(@RequestParam("description") String description, @RequestParam("login") String login,
			@RequestParam("password") String password, @RequestParam("name") String name) {

		MongoQuery consultaUser = MongoQuery.of(MongoCriteria.where(User.LOGIN).is(login));
		List<User> result = this.find(consultaUser);
		if (result.size() > 0) {
			User user = (User) result.get(0);
			user.setPassword(password);
			sessionFactoryNoSQL.getCurrentSession().save(user);
		} else {
			User user = new User();
			user.setBoAdministrator(true);
			user.setDescription(description);
			user.setLogin(login);
			user.setPassword(password);
			user.setName(name);
			sessionFactoryNoSQL.getCurrentSession().save(user);
		}
	}

	public List<User> find(NoSQLQuery query) {
		if (query == null) {
			return Collections.emptyList();
		}
		return sessionFactoryNoSQL.getCurrentSession().find(query, User.class);
	}

}
