package sample.spring.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.AbstractJsonpResponseBodyAdvice;

import java.util.Date;

@org.springframework.web.bind.annotation.ControllerAdvice
public class ControllerAdvice extends AbstractJsonpResponseBodyAdvice {
	public ControllerAdvice() {
		super("callback"); // for AbstractJsonpResponseBodyAdvice, Jackson
	}

	@InitBinder
	public void initBinder() {
		System.out.println("ControllerAdvice.initBinder()");
	}

	@ModelAttribute
	public void attribute(Model model) {
		model.addAttribute("global", new Date());
	}

	@ExceptionHandler/*(Exception.class)*/
	@ResponseBody
	public ResponseEntity<String> handleException(Exception e) {
		e.printStackTrace();
		return ResponseEntity
			.ok()
				//.contentType(MediaType.APPLICATION_JSON_UTF8)
				.body(e.toString())
		;
	}
}
