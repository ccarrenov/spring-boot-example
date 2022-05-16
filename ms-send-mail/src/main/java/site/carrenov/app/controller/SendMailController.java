package site.carrenov.app.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import site.carrenov.App;
import site.carrenov.app.service.SendMailService;

@Controller
@RequestMapping("/app/v1/send-mail")
@Api(value = "Eleccion Flujo", tags = { App.SEND_MAIL })
public class SendMailController {

	private static final Logger LOGGER = Logger.getLogger(SendMailController.class.getName());
	
	@Autowired
	private SendMailService sendMailService;

	@ApiOperation(value = "success-mail", tags = { App.SEND_MAIL })
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = String.class),
			@ApiResponse(code = 500, message = "Failure") })
	@GetMapping(value = "ssuccess-mail/{toList}", consumes = {}, produces = { MediaType.TEXT_PLAIN_VALUE })
	public ResponseEntity<String> sendSuccessMail(@PathVariable String toList) {

		LOGGER.info("INIT sendSuccessMail");
		LOGGER.info("EMAILs -> " + toList);
		String subject = "POC SEND SUCCESS MAIL ";
		//OVERRIDE TEMPLATE
		String bodyMessage = sendMailService.readMailTemplate("mail/template/success-mail.html");
		bodyMessage = bodyMessage.replace("#PERSON_NAME", "John W.");
		String[] array = new String[]{"element 1", "element 2", "element 3", "element 4"};		
		StringBuilder list = new StringBuilder();
		for (String element : array) {
			list = list.append("<tr><td>").append(element).append("</td></tr>");
		}
		bodyMessage = bodyMessage.replace("#LIST", list);
		
		sendMailService.buildAndSend(toList, subject, bodyMessage);
		
		ResponseEntity<String> response = new ResponseEntity<>(subject, HttpStatus.OK);
		LOGGER.info("response -> " + response);
		LOGGER.info("FINISH sendSuccessMail");
		return response;
	}

}
