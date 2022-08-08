package site.carrenov.app.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import io.swagger.annotations.Api;
import site.carrenov.App;
import site.carrenov.app.mysql.model.User;
import site.carrenov.app.mysql.service.UserService;

@Controller
@RequestMapping("/app/v1/user")
@Api(value = "User api", tags = { App.USER_TAG })
public class UserController implements IUserController{

	private static final Logger LOGGER = Logger.getLogger(UserController.class.getName());
	
	@Autowired
	private UserService userService;

	@Override
	public ResponseEntity<List<User>> findAll() {
		try {
			return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
		}catch(Exception ex) {
			LOGGER.error(ex);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
