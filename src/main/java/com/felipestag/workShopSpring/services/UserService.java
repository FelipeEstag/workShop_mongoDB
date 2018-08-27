package com.felipestag.workShopSpring.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.felipestag.workShopSpring.domain.User;
import com.felipestag.workShopSpring.dto.UserDTO;
import com.felipestag.workShopSpring.repository.UserRepository;
import com.felipestag.workShopSpring.services.exepction.ObjectNotFoundException;

@Service // spring injetar em outras classes
public class UserService {

	@Autowired // Spring ja gerencia a instancia dessa classe e injeta.
	UserRepository repository;

	public List<User> findAll() {
		return repository.findAll(); // find All é uma operacao do Spring data.. pesquisar sobre spring data...
	}

	public User findById(String id) {
		Optional<User> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
	}

	public User insert(User user) {
		return repository.insert(user);
	}

	public void delete(String id) {
		findById(id);
		repository.deleteById(id);

	}
	
	public User update (User user) {
		User newObj = findById(user.getId());
		updateData(newObj, user);
		return repository.save(newObj);	
	}

	private void updateData(User newObj, User user) {
		newObj.setName(user.getName());
		newObj.setEmail(user.getEmail());
		
	}

	public User fromDTO(UserDTO dto) {
		return new User(dto.getId(), dto.getName(), dto.getEmail());
	}

}
