package br.com.anteros.oauth2.server.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.anteros.persistence.dsl.osql.OSQLQuery;
import br.com.anteros.persistence.session.SQLSession;
import br.com.anteros.persistence.session.SQLSessionFactory;
import br.com.anteros.security.store.sql.domain.TUser;
import br.com.anteros.security.store.sql.domain.User;

@RestController
@RequestMapping(value = "/api.v1/security")
public class SecurityResource {

	@Autowired
	private SQLSessionFactory sessionFactorySQL;

	@RequestMapping(value = "/addNewUser", method = { RequestMethod.POST, RequestMethod.PUT })
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public void addNewUser(@RequestParam("description") String description, @RequestParam("login") String login,
			@RequestParam("password") String password, @RequestParam("name") String name) {

		SQLSession session = null;
		try {
			session = sessionFactorySQL.openSession();
		} catch (Exception e) {
			throw new OAuth2ServerException("Ocorreu um erro obtendo a sessão corrente.");
		}

		try {
			session.getTransaction().begin();
			TUser tUser = new TUser("USR");
			OSQLQuery consultaUser = new OSQLQuery(session);
			User user = consultaUser.from(tUser).where(tUser.login.equalsIgnoreCase(login)).singleResult(tUser);
			if (user != null) {
				user.setPassword(password);
				session.save(user);
			} else {
				user = new User();
				user.setBoAdministrator(true);
				user.setDescription(description);
				user.setLogin(login);
				user.setPassword(password);
				user.setName(name);
				session.save(user);
			}
			session.getTransaction().commit();
			session.close();
		} catch (Exception e) {
			try {
				session.getTransaction().rollback();
			} catch (Exception e1) {
			}
			try {
				session.close();
			} catch (Exception e1) {
			}
			throw new OAuth2ServerException(e);
		}
	}

}
