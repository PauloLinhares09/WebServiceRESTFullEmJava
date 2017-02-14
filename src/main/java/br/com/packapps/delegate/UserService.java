package br.com.packapps.delegate;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import br.com.packapps.beans.User;
import br.com.packapps.dao.UserDAO;

@Component
public class UserService {
	
	@Autowired
	private UserDAO db;
	
	// Lista todos os users do banco de dados
	public List<User> getUsers() {
		List<User> users = db.getUsers();
		return users;
	}

	// Busca um user pelo id
	public User getUser(Long id) {
		return db.getUserById(id);
	}

	// Deleta o user pelo id
	@Transactional(rollbackFor = Exception.class)
	public boolean delete(Long id) {
		return db.delete(id);
	}

	// Salva ou atualiza o user
	@Transactional(rollbackFor = Exception.class)
	public boolean save(User user) {
		db.salvar(user);
		return true;
	}

	// Busca o user pelo nome
	public List<User> findByName(String name) {
		return db.findByName(name);
	}
	
	// Busca o user pelo nome
	public List<User> findByEmailAndOrigem(String email, String origem) {
		return db.findByEmailAndOrigem(email, origem);
	}

	/*
	public List<User> findByTipo(String tipo) {
		return db.findByTipo(tipo);
	}*/

}
