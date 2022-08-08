package site.carrenov.app.repository.mysql;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import site.carrenov.app.model.mysql.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long>{

	Optional<User> findById(Long id);
	
	List<User> findAll();
	
}
