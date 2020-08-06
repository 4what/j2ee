<%@ page contentType="text/html; charset=UTF-8" %>

<%@ include file="/admin/_auth.jspf" %>

<%@ page import="$java.util.Functions" %>

<%@ page import="com.example.domain.Log" %>

<%@ page import="com.fasterxml.jackson.databind.ObjectMapper" %>
<%@ page import="com.fasterxml.jackson.databind.node.ArrayNode" %>
<%@ page import="com.fasterxml.jackson.databind.node.ObjectNode" %>
<%@ page import="org.apache.commons.lang3.StringUtils" %>
<%@ page import="org.springframework.security.core.GrantedAuthority" %>
<%@ page import="org.springframework.security.core.authority.SimpleGrantedAuthority" %>
<%@ page import="org.springframework.security.provisioning.JdbcUserDetailsManager" %>
<%@ page import="org.springframework.transaction.TransactionStatus" %>
<%@ page import="org.springframework.transaction.support.TransactionCallbackWithoutResult" %>
<%@ page import="org.springframework.transaction.support.TransactionTemplate" %>

<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>

<%
	out.clear();

	request.setCharacterEncoding("UTF-8");

	String action = request.getParameter("action");
	if (StringUtils.isBlank(action)) {
		return;
	}

	WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(application);

	ObjectMapper mapper = ctx.getBean(ObjectMapper.class);

	ObjectNode result = mapper.createObjectNode();

	// ip
	String ip = Functions.ip(request);

	// log
	Log log = new Log();
	log.setAdminId(_admin.getAdminId());
	log.setIp(ip);

	// ...
	GenericRepository genericRepository = ctx.getBean(GenericRepository.class);

	TransactionTemplate transactionTemplate = ctx.getBean(TransactionTemplate.class);

	JdbcUserDetailsManager jdbcUserDetailsManager = ctx.getBean(JdbcUserDetailsManager.class);

	switch (action) {
		case "list":
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

			break;
		case "save":
			try {
				transactionTemplate.execute(new TransactionCallbackWithoutResult() {
					@Override
					protected void doInTransactionWithoutResult(TransactionStatus transactionStatus) {
						try {
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
						} catch (Exception e) {
							e.printStackTrace();

							throw new RuntimeException(e.getMessage());
						}
					}
				});
			} catch (Exception e) {
				//e.printStackTrace();

				result.put("msg", e.getMessage());
			}

			break;
		case "delete":
			try {
				transactionTemplate.execute(new TransactionCallbackWithoutResult() {
					@Override
					protected void doInTransactionWithoutResult(TransactionStatus transactionStatus) {
						try {
							String[] ids = request.getParameterValues("id");

							for (int i = 0, l = ids.length; i < l; i++) {
								jdbcUserDetailsManager.deleteGroup(ids[i]);
							}

							result.put("success", true);
						} catch (Exception e) {
							e.printStackTrace();

							throw new RuntimeException(e.getMessage());
						}
					}
				});
			} catch (Exception e) {
				//e.printStackTrace();

				result.put("msg", e.getMessage());
			}

			break;
		default:
			break;
	}

	out.println(result);
%>
