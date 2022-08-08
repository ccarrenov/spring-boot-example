package site.carrenov.app.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import site.carrenov.app.App;
import site.carrenov.app.model.User;
import site.carrenov.app.service.IUserService;

@CrossOrigin(origins = { "http://localhost:4200", "*" })
@RestController
@RequestMapping("/app/v1/user")
@Api(value = "User api", tags = { App.USER_TAG })
public class UserController {

	private static final Logger LOGGER = Logger.getLogger(UserController.class.getName());

	@Autowired
	@Qualifier("service.${engine}")
	private IUserService userService;

	@ApiOperation(value = "", tags = { App.USER_TAG })
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = User[].class),
			@ApiResponse(code = 500, message = "Failure") })
	@GetMapping(value = "", consumes = {}, produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<List<User>> findAll() {
		try {
			return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
		} catch (Exception ex) {
			LOGGER.error(ex);
			return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@ApiOperation(value = "", tags = { App.USER_TAG })
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = User.class),
			@ApiResponse(code = 500, message = "Failure") })
	@PostMapping(value = "", consumes = {}, produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<User> save(@RequestBody User user) {
		try {
			return new ResponseEntity<>(userService.createUser(user), HttpStatus.OK);
		} catch (Exception ex) {
			LOGGER.error(ex);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
