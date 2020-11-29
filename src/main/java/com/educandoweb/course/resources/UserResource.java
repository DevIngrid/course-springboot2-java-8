package com.educandoweb.course.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.educandoweb.course.entities.User;
import com.educandoweb.course.services.UserService;

@RestController
@RequestMapping (value = "/users")
public class UserResource {
	
	@Autowired
	private UserService service;
	
	//endpoind para acessar os usuários
	@GetMapping //para indicar que este método responde à requisição do tipo Get do http
	public ResponseEntity<List<User>> findAll() {
		
		/*User u = new User(1L, "Maria", "maria@gmail.com", "9999999", "12345");
		 * => feito somente para teste inicial*
		 * 
		 * return ResponseEntity.ok().body(u);*/
		List<User> list = service.findAll();
		
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping (value = "/{id}")
	public ResponseEntity<User> findById(@PathVariable Long id){
		
		User obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@PostMapping
	public ResponseEntity<User> insert(@RequestBody User obj) {
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).body(obj);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<User> update(@PathVariable Long id, @RequestBody User obj) {
		User objnew = service.update(id, obj);
		return ResponseEntity.ok().body(objnew);
	}
	/*aqui eu fiz um pouco diferente do professor para eu entender melhor
	 * o professor usou o proprio obj para chamar o service.update 
	 * eu coloquei um objnew*/

}
