package br.com.packapps.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Component;

import br.com.packapps.beans.User;

@Component
@SuppressWarnings("unchecked")
public class UserDAO extends HibernateDAO<User>{

	public UserDAO() {
		// Informa o tipo da entidade para o Hibernate
		super(User.class);
	}
	
	// Consulta um user pelo id
		public User getUserById(Long id) {
			// O Hibernate consulta automaticamente pelo id
			return super.get(id);
		}

		// Busca um user pelo nome
		public List<User> findByName(String firstName) {
			Query q = getSession().createQuery("from User where lower(firstName)  like lower(?)");
			q.setString(0, "%" + firstName +"%");
			return q.list();
		}
		
		public List<User> findByEmailAndOrigem(String email, String origem) {
			Query q = getSession().createQuery("from User where email=? and origem=?");
			q.setString(0, email);
			q.setString(1, origem);
			return q.list();
		}
	

		// Busca um user pelo tipo
		/*
		public List<User> findByTipo(String tipo) {
			Query q = getSession().createQuery("from User where tipo=?");
			q.setString(0, tipo);
			List<User> users = q.list();
			return users;
		}*/

		// Consulta todos os users
		public List<User> getUsers() {
			Query q = getSession().createQuery("from User");
			List<User> users = q.list();
			return users;
		}

		// Insere ou atualiza um user
		public void salvar(User u) {
			super.save(u);
		}

		// Deleta o user pelo id
		public boolean delete(Long id) {
			User u = get(id);
			delete(u);
			return true;
		}

		
	
	
	

}
