package site.carrenov.app.mysql.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import site.carrenov.app.mysql.model.User;
import site.carrenov.app.mysql.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepo;
	
	public User findById(Long id) {
		return userRepo.findById(id).get();
	}
	
	public List<User> findAll(){
		return userRepo.findAll();
	}
}
