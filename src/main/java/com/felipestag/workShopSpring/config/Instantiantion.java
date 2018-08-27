package com.felipestag.workShopSpring.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.felipestag.workShopSpring.domain.Post;
import com.felipestag.workShopSpring.domain.User;
import com.felipestag.workShopSpring.dto.AuthorDTO;
import com.felipestag.workShopSpring.repository.PostRepository;
import com.felipestag.workShopSpring.repository.UserRepository;

@Configuration
public class Instantiantion implements CommandLineRunner {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PostRepository postRepository;

	@Override
	public void run(String... args) throws Exception {
		//carga inicial na base de dados
		
		postRepository.deleteAll();
		userRepository.deleteAll();
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		
		
		User usertest = new User(null, "teste pra iniciar a base de dados", "iniacao@gmail.com");
		userRepository.saveAll(Arrays.asList(usertest));

		
		
		Post post = new Post(null, dateFormat.parse("26/08/2018"), "meu primeiro post", "isso é um teste", new AuthorDTO(usertest));
		postRepository.saveAll(Arrays.asList(post));
		
		usertest.getPosts().addAll(Arrays.asList(post));
		userRepository.save(usertest);
	}

}
