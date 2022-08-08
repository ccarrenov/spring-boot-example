package site.carrenov.app.service;

import java.util.List;

import site.carrenov.app.model.User;

public interface IUserService {


	public User findById(Long id);
	
	public List<User> findAll();
	
	public User createUser(User user);
}
