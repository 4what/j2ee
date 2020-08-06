package com.example.controller.admin;

import com.example.dao.GenericRepository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/admin/role")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class RoleController {
	@Autowired
	private ObjectMapper mapper;

	@Autowired
	private GenericRepository genericRepository;

	@Autowired
	private JdbcUserDetailsManager jdbcUserDetailsManager;

	@RequestMapping("/list")
	@ResponseBody
	public String list(HttpServletRequest request) {
		ObjectNode result = mapper.createObjectNode();

		ArrayNode rows = mapper.createArrayNode();

		for (String group : jdbcUserDetailsManager.findAllGroups()) {
			ObjectNode item = mapper.createObjectNode();

			item.put("id", group);

			item.put("group", group);

			ArrayNode roles = mapper.createArrayNode();
			for (GrantedAuthority authority : jdbcUserDetailsManager.findGroupAuthorities(group)) {
				roles.add(authority.getAuthority());
			}
			item.set("roles", roles);

			rows.add(item);
		}

		result.set("rows", rows);

		return result.toString();
	}

	@RequestMapping("/save")
	@ResponseBody
	public String save(HttpServletRequest request) {
		ObjectNode result = mapper.createObjectNode();

		String id = StringUtils.defaultString(request.getParameter("id"));

		String data = request.getParameter("data");

		if (StringUtils.isNotBlank(id)) { // update
			String group = request.getParameter("group");

			jdbcUserDetailsManager.renameGroup(id, group);

			for (GrantedAuthority authority : jdbcUserDetailsManager.findGroupAuthorities(group)) {
				jdbcUserDetailsManager.removeGroupAuthority(group, authority);
			}

			for (String role : request.getParameter("roles").split(",")) {
				jdbcUserDetailsManager.addGroupAuthority(group, new SimpleGrantedAuthority(role));
			}
		} else if (StringUtils.isNotBlank(data)) { // update

		} else { // create
			List<GrantedAuthority> roles = new ArrayList<>();

			for (String role : request.getParameter("roles").split(",")) {
				roles.add(new SimpleGrantedAuthority(role));
			}

			jdbcUserDetailsManager.createGroup(request.getParameter("group"), roles);

			result.put("success", true);
		}

		result.put("success", true);

		return result.toString();
	}

	@RequestMapping("/delete")
	@ResponseBody
	public String delete(HttpServletRequest request) {
		ObjectNode result = mapper.createObjectNode();

		String[] ids = request.getParameterValues("id");

		for (int i = 0, l = ids.length; i < l; i++) {
			jdbcUserDetailsManager.deleteGroup(ids[i]);
		}

		result.put("success", true);

		return result.toString();
	}

	@ExceptionHandler
	@ResponseBody
	public String errorHandler(Exception e) {
		System.out.println("e: " + e);

		ObjectNode result = mapper.createObjectNode();

		result.put("msg", e.getMessage());

		return result.toString();
	}
}
