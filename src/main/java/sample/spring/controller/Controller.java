package sample.spring.controller;

import sample.hibernate.validator.Group;
import sample.spring.domain.Pojo;
import sample.spring.validator.Validator;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.Locale;
import java.util.Map;

@org.springframework.stereotype.Controller
//@RequestMapping("")
public class Controller {
	@Autowired
	private MessageSource messageSource;

	@InitBinder
	public void initBindTransactionaler(WebDataBinder binder) {
		//binder.addValidators(new Validator());
	}

	@ModelAttribute
	public void attribute(Model model) {
		model.addAttribute("local", new Date());
	}

/*
	@RequestMapping("/") // (!) .jsp
	public String home() {
		return
			"index"
			//"forward:/"
			//"redirect:/"
		;
	}
*/

	@RequestMapping("/200")
	public ResponseEntity<String> handle() {
		return ResponseEntity.ok("OK");
	}

	@RequestMapping("/text")
	@ResponseBody
	public String text() {
		return "中文";
	}

	@RequestMapping(
		value = "/action/{path}"
		//, method = RequestMethod.*
		//, params = "" // name | !name | name=value
		//, consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE}
		//, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE}
	)
	//@ResponseStatus(HttpStatus.OK)
	//@CrossOrigin(allowCredentials = "true", origins = {"*"})
	public String action(
		HttpServletRequest request,
		HttpServletResponse response,
		HttpSession session,

		@PathVariable/*("path")*/ String path,

		/*@RequestParam(
			//value = "param",
			//required = false
		)*/ String param,

		// POST
		//@RequestBody String data,
		//@RequestBody Map<String, String> params, // Content-Type = application/json

		@Validated/*(Group.class)*/ Pojo pojo,

		Model model
	) {
		System.out.println("request: " + request);
		System.out.println("response: " + response);
		System.out.println("session: " + session);

		System.out.println("path: " + path);

		System.out.println("param: " + param);

		//System.out.println("data: " + data);
		//System.out.println("params: " + params);

		System.out.println("pojo: " + pojo);

		model.addAttribute("data", new Date());

		return "view/action";
	}

	@RequestMapping("/message")
	@ResponseBody
	public String message(Locale locale) {
		System.out.println("locale: " + locale);

		return messageSource.getMessage("key", new Object[]{"Spring"}, locale);
	}

	@RequestMapping("/error")
	public String error() {
		throw new RuntimeException();
	}

	@ExceptionHandler/*(Exception.class)*/
	@ResponseBody
	public String errorHandler(Exception e) {
		return "errorHandler: " + e;
	}

	@ExceptionHandler(BindException.class)
	@ResponseBody
	public String handleBindException(BindException e) {
		for (FieldError error : e.getBindingResult().getFieldErrors()) {
			System.out.println("error.getDefaultMessage(): " + error.getDefaultMessage());

			System.out.println("error.getArguments(): " + Arrays.toString(error.getArguments()));
			System.out.println("error.getCode(): " + error.getCode());
			System.out.println("error.getField(): " + error.getField());
			System.out.println("error.getObjectName(): " + error.getObjectName());

			System.out.println("error.getRejectedValue(): " + error.getRejectedValue());
		}
		return "BindException: " + e;
	}

	@RequestMapping("/form")
	public String form(Model model) {
		model.addAttribute("pojo", new Pojo());

		return "view/form";
	}

	@RequestMapping("/submit")
	public String submit(@Validated Pojo pojo, BindingResult result) {
		System.out.println("result: " + result);

		return "view/form";
	}

	@RequestMapping("/jackson")
	@ResponseBody
	public JsonNode jackson() throws IOException {
		return new ObjectMapper().readTree("[{\"name\": \"value\"}]");
	}
}
