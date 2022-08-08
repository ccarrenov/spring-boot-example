package site.carrenov.app.service.mysql;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import site.carrenov.app.model.annotation.MapperConvert;
import site.carrenov.app.model.mysql.User;
import site.carrenov.app.repository.mysql.UserRepository;
import site.carrenov.app.service.IUserService;

@Component("service.mysql")
@Service
public class UserService implements IUserService {

	private static final Logger LOGGER = Logger.getLogger(UserService.class.getName());

	@Autowired
	private UserRepository userRepo;

	private MapperConvert<site.carrenov.app.model.User, User> converter = new MapperConvert<>(site.carrenov.app.model.User.class, User.class);

	@Override
	public site.carrenov.app.model.User findById(Long id) {
		site.carrenov.app.model.User userJ = null;
		try {
			userJ =  converter.convertToOrigin(userRepo.findById(id).get());
		} catch (Exception ex) {
			LOGGER.error(ex);
		}
		return userJ;
	}

	@Override
	public List<site.carrenov.app.model.User> findAll() {
		List<site.carrenov.app.model.User> usersJ = new ArrayList<>();
		try {
			return converter.convertListToOrigin(userRepo.findAll());
		} catch (Exception ex) {
			LOGGER.error(ex);
		}
		return usersJ;
	}

	@Override
	public site.carrenov.app.model.User createUser(site.carrenov.app.model.User userJ) {
		try {
			User user = converter.convertToDestiny(userJ);
			userRepo.save(converter.convertToDestiny(userJ));
			userJ.setId(user.getId());
		} catch (Exception ex) {
			LOGGER.error(ex);
			userJ = null;
		}
		return userJ;
	}
}
