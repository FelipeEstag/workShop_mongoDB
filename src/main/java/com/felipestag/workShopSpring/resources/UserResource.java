package com.felipestag.workShopSpring.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.felipestag.workShopSpring.domain.Post;
import com.felipestag.workShopSpring.domain.User;
import com.felipestag.workShopSpring.dto.UserDTO;
import com.felipestag.workShopSpring.services.UserService;

@RestController
@RequestMapping(value = "/users")
public class UserResource {

	@Autowired
	private UserService service;

	@GetMapping
	public ResponseEntity<List<UserDTO>> findAll() {

		List<User> list = service.findAll();
		List<UserDTO> listDto = list.stream().map(x -> new UserDTO(x)).collect(Collectors.toList());

		return ResponseEntity.ok().body(listDto);

	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<UserDTO> findById(@PathVariable String id) { // para o id ser a referencia do controller

		User user = service.findById(id);

		return ResponseEntity.ok().body(new UserDTO(user));

	}
	
	@GetMapping(value = "/{id}/posts")
	public ResponseEntity<List<Post>> findPosts(@PathVariable String id) { 

		User user = service.findById(id);

		return ResponseEntity.ok().body(user.getPosts());

	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable String id) { // para o id ser a referencia do controller
		service.delete(id);

		return ResponseEntity.noContent().build();

	}

	@PostMapping
	public ResponseEntity<Void> insert(@RequestBody UserDTO dto) { // para o id ser a referencia do controller
		User obj = service.fromDTO(dto);
		obj = service.insert(obj);
		// para retornar o codigo http 201, que significa que foi criado oo objeto da
		// requisicao
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri(); // cabecario
																														// aone
																														// esta
																														// o
																														// novo
																														// recurso
																														// criado
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<Void> update(@RequestBody UserDTO dto, @PathVariable String id) { // para o id ser a referencia do controller
		User obj = service.fromDTO(dto);
		obj.setId(id);
		obj = service.update(obj);
		
		return ResponseEntity.noContent().build();
	}

}
