package sample.spring.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/rest")
public class RestController {
	@RequestMapping(method = RequestMethod.POST)
	public String create() {
		return "create";
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public String delete(@PathVariable int id) {
		return "delete" + id;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public String get(@PathVariable int id) {
		return "get: " + id;
	}

	@RequestMapping(method = RequestMethod.GET)
	public String list() {
		return "list";
	}

	@RequestMapping(method = RequestMethod.PUT)
	public String update() {
		return "update";
	}
}
