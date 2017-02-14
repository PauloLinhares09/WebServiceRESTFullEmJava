package br.com.packapps.rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.packapps.beans.User;
import br.com.packapps.delegate.UserService;
import br.com.packapps.util.Response;

@Path("/users")
@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
@Consumes(MediaType.APPLICATION_JSON + ";charset=utf-8")
@Component
public class UserRest {
	
	@Autowired
	private UserService userservice;
	
	@GET
	public Response get() {
		List<User> users = userservice.getUsers();
		if(users != null && users.size() != 0){
			return Response.Ok("Encontrados com sucesso", 200, users);
		}else{
			return Response.Error("Não encontradas", 404);
		}
	}

	@GET
	@Path("{id}")
	public Response get(@PathParam("id") long id) {
		User u = userservice.getUser(id);
		if(u != null){
			return Response.Ok("Encontrado com sucesso", 200, u);
		}else{
			return Response.Error("Não encontrado", 404);
		}
	}

	/*
	@GET
	@Path("/tipo/{tipo}")
	public List<User> getByTipo(@PathParam("tipo") String tipo) {
		List<User> users = userservice.findByTipo(tipo);
		return users;
	}*/

	@GET
	@Path("/nome/{nome}")
	public Response getByNome(@PathParam("nome") String nome) {
		List<User> users = userservice.findByName(nome);
		if(users != null && users.size() > 0){
			return Response.Ok("Encontrados com sucesso", 200, users);
		}else{
			return Response.Error("Não encontrado", 404);
		}
	}

	@DELETE
	@Path("{id}")
	public Response delete(@PathParam("id") long id) {
		if( userservice.delete(id)){
			return Response.Ok("Deletado com sucesso", 200, null);
		}else{
			return Response.Error("Erro ao deletar", 403);
		}
	}

	@POST
	public Response post(User u) {
		//verifica se já existe
		List<User> userList = userservice.findByEmailAndOrigem(u.getEmail(), u.getOrigem());
		if(userList == null ){
			//save
			User user = mSave(u);
			if(user != null){
				return Response.Ok("Salvo com sucesso", 200, user);
			}
		}else if(userList != null && userList.size() == 0){
			//save
			User user = mSave(u);
			if(user != null){
				return Response.Ok("Salvo com sucesso", 200, user);
			}
		}else if(userList != null && userList.size() > 0){//user existe, fazer update
			for(User u2 : userList){
				if(u2.getEmail().equals(u.getEmail()) && u2.getOrigem().equals(u.getOrigem()) ){
					User user = mSave(u2);
					if(user != null){
						return Response.Ok("Atualizado Com Sucesso", 200, user);
					}
				}
			}
		}
		//Retornar este user ao APP
		
		return Response.Error("Erro ao salvar Usuário", 503);
	}

	
	/**
	 * Metodo alternativo para salvar usuário e já buscar o retorno deste user para enviar aos APPs
	 * @param u
	 * @return
	 */
	private User mSave(User u) {
		// TODO Auto-generated method stub
		
		userservice.save(u);
		User user = null;
		//pesquisa por email e origem
		List<User> uList = userservice.findByEmailAndOrigem(u.getEmail(), u.getOrigem());
		if(uList != null){
			if(uList.size() > 0){
				user = uList.get(uList.size() - 1);
			}
		}
		
		return user;
	}

	@PUT
	public Response put(User u) {
		userservice.save(u);
		return Response.Ok("Atualizado com sucesso", 200, null);
	}
	
	

}
