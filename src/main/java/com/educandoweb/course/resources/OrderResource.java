package com.educandoweb.course.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.educandoweb.course.entities.Order;
import com.educandoweb.course.services.OrderService;

@RestController
@RequestMapping (value = "/orders")
public class OrderResource {
	
	@Autowired
	private OrderService service;
	
	//endpoind para acessar os usuários
	@GetMapping //para indicar que este método responde à requisição do tipo Get do http
	public ResponseEntity<List<Order>> findAll() {
		
		/*Order u = new Order(1L, "Maria", "maria@gmail.com", "9999999", "12345");
		 * => feito somente para teste inicial*
		 * 
		 * return ResponseEntity.ok().body(u);*/
		List<Order> list = service.findAll();
		
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping (value = "/{id}")
	public ResponseEntity<Order> findById(@PathVariable Long id){
		
		Order obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}

}
