package br.com.anteros.oauth2.server.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
	public void addNewUser(@RequestBody UserDTO userDTO) {

		SQLSession session = null;
		try {
			session = sessionFactorySQL.openSession();
		} catch (Exception e) {
			throw new OAuth2ServerException("Ocorreu um erro obtendo a sessão corrente.");
		}

		try {
			session.getTransaction().begin();
			session.setTenantId(userDTO.getOwner());
			TUser tUser = new TUser("USR");
			OSQLQuery consultaUser = new OSQLQuery(session);
			User user = consultaUser.from(tUser)
					.where(tUser.login.equalsIgnoreCase(userDTO.getLogin()).and(tUser.owner.eq(userDTO.getOwner())))
					.singleResult(tUser);
			if (user != null) {
				user.setPassword(userDTO.getPassword());
				session.save(user);
			} else {
				user = new User();
				user.setOwner(userDTO.getOwner());
				user.setBoAdministrator(true);
				user.setDescription(userDTO.getDescription());
				user.setLogin(userDTO.getLogin());
				user.setPassword(userDTO.getPassword());
				user.setName(userDTO.getName());
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
