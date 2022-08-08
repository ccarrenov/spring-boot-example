package site.carrenov.app.controller;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import site.carrenov.App;
import site.carrenov.app.mysql.model.User;

public interface IUserController {

	@ApiOperation(value = "find-all", tags = { App.USER_TAG })
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = User[].class),
			@ApiResponse(code = 500, message = "Failure") })
	@GetMapping(value = "find-all", consumes = {}, produces = { MediaType.TEXT_PLAIN_VALUE })
	ResponseEntity<List<User>> findAll();
}
