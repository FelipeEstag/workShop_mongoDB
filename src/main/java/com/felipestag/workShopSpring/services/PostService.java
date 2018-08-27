package com.felipestag.workShopSpring.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.felipestag.workShopSpring.domain.Post;
import com.felipestag.workShopSpring.repository.PostRepository;
import com.felipestag.workShopSpring.services.exepction.ObjectNotFoundException;

@Service 
public class PostService {

	@Autowired 
	PostRepository postRepository;


	public Post findById(String id) {
		Optional<Post> obj = postRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
	}

}
