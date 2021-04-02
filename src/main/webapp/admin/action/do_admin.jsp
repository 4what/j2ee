<%@ page contentType="text/html; charset=UTF-8" %>

<%@ include file="/admin/_auth.jspf" %>

<%@ page import="$java.Utilities" %>

<%@ page import="com.example.domain.Log" %>

<%@ page import="com.fasterxml.jackson.databind.ObjectMapper" %>
<%@ page import="com.fasterxml.jackson.databind.node.ArrayNode" %>
<%@ page import="com.fasterxml.jackson.databind.node.ObjectNode" %>
<%@ page import="org.apache.commons.lang3.StringUtils" %>
<%@ page import="org.apache.commons.lang3.math.NumberUtils" %>
<%@ page import="org.springframework.security.authentication.encoding.ShaPasswordEncoder" %>
<%@ page import="org.springframework.security.core.userdetails.User" %>
<%@ page import="org.springframework.security.core.userdetails.UserDetails" %>
<%@ page import="org.springframework.security.provisioning.JdbcUserDetailsManager" %>
<%@ page import="org.springframework.transaction.TransactionStatus" %>
<%@ page import="org.springframework.transaction.support.TransactionCallbackWithoutResult" %>
<%@ page import="org.springframework.transaction.support.TransactionTemplate" %>

<%@ page import="java.util.*" %>

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

	/* ip */
	String ip = Utilities.ip(request);

	/* log */
	Log log = new Log();
	log.setAdminId(_admin.getAdminId());
	log.setIp(ip);

	/* ... */
	GenericRepository genericRepository = ctx.getBean(GenericRepository.class);

	TransactionTemplate transactionTemplate = ctx.getBean(TransactionTemplate.class);

	JdbcUserDetailsManager jdbcUserDetailsManager = ctx.getBean(JdbcUserDetailsManager.class);

	switch (action) {
		case "list": {
			List list;

			Map<String, Object> params = new HashMap<>();

			String query = StringUtils.defaultString(request.getParameter("query"));
			if (StringUtils.isNotBlank(query)) {
				// (自定义)
				params.put("adminid", query);
				params.put("username", "%" + query + "%");

				query = "AND (adminid = :adminid OR username LIKE :username)";
				query += " ";
			}

			String status = request.getParameter("status");
			if (StringUtils.isNotBlank(status)) {
				params.put("status", Integer.parseInt(status));

				query += "AND status = :status";
				query += " ";
			}

			query = "WHERE 1 = 1 " + query + "ORDER BY createdate DESC";

			int limit = NumberUtils.toInt(request.getParameter("limit"));
			int start = NumberUtils.toInt(request.getParameter("start"));

			if (limit > 0) {
				int pageNum = start > 0 ? (start / limit + 1) : 1;
				int pageSize = limit;

				list = genericRepository.pagedList(Admin.class, query, pageNum, pageSize, params);

				result.put("total", genericRepository.count(Admin.class, query, params));
			} else {
				list = genericRepository.list(Admin.class, query, params);
			}

			ArrayNode rows = mapper.createArrayNode();

			for (Object o : list) {
				ObjectNode item = mapper.valueToTree(o);

				// (自定义)
				for (String group : jdbcUserDetailsManager.findAllGroups()) {
					for (String username : jdbcUserDetailsManager.findUsersInGroup(group)) {
						if (username.equals(((Admin) o).getUsername())) {
							item.put("group", group);

							break;
						}
					}
				}

				rows.add(item);
			}

			result.set("rows", rows);

			break;
		}
		case "form":
			int id = Integer.parseInt(request.getParameter("id"));

			Admin admin = genericRepository.get(Admin.class, id);

			result.put("success", true);
			result.set("data", mapper.valueToTree(admin));

			break;
		case "save":
			try {
				transactionTemplate.execute(new TransactionCallbackWithoutResult() {
					@Override
					protected void doInTransactionWithoutResult(TransactionStatus transactionStatus) {
						try {
							int id = NumberUtils.toInt(request.getParameter("adminId"));

							String data = request.getParameter("data");

							if (id > 0) { // update
/*
								Admin admin = genericRepository.get(Admin.class, id);

								// (自定义)

								genericRepository.update(admin);
*/

								/* log */
/*
								log.setModule(Admin.class.getSimpleName());
								log.setAction("Update");
								log.setMessage("id: " + id); // (自定义)
								log.setCreateDate(new Date());
								genericRepository.create(log);
*/
							} else if (StringUtils.isNotBlank(data)) { // update
								ArrayNode items = (ArrayNode) mapper.readTree(data);

								for (Object o : items) {
									ObjectNode item = mapper.valueToTree(o);

									int adminId = item.get("adminId").asInt();

									Admin admin = genericRepository.get(Admin.class, adminId);

									// (自定义)
/*
									admin.setPassword(DigestUtils.sha1Hex(item.get("password").asText()));

									genericRepository.update(admin);
*/

									/* security */
									String password = item.get("password").asText();

									if (StringUtils.isNotBlank(password)) {
										UserDetails user = jdbcUserDetailsManager.loadUserByUsername(admin.getUsername());

										jdbcUserDetailsManager.updateUser(new User(user.getUsername(), new ShaPasswordEncoder().encodePassword(password, user.getUsername()), user.getAuthorities()));
									}

									for (String group : jdbcUserDetailsManager.findAllGroups()) {
										for (String username : jdbcUserDetailsManager.findUsersInGroup(group)) {
											if (username.equals(admin.getUsername())) {
												jdbcUserDetailsManager.removeUserFromGroup(username, group);
											}
										}
									}

									jdbcUserDetailsManager.addUserToGroup(admin.getUsername(), item.get("group").asText());

									/* log */
									log.setModule(Admin.class.getSimpleName());
									log.setAction("Update");
									log.setMessage("id: " + adminId); // (自定义)
									log.setCreateDate(new Date());
									genericRepository.create(log);
								}
							} else { // create
								Admin admin = new Admin();

								// (自定义)
								String username = request.getParameter("username");

								if (genericRepository.count(Admin.class, "WHERE username = ?", username) > 0) {
									throw new RuntimeException("重复提交");
								}

								admin.setUsername(username);
								//admin.setPassword(DigestUtils.sha1Hex(request.getParameter("password")));

								admin.setCreateDate(new Date());

								int adminId = genericRepository.create(admin);

								/* security */
								if (jdbcUserDetailsManager.userExists(username)) {
									throw new RuntimeException("重复提交");
								}

								jdbcUserDetailsManager.createUser(new User(username, new ShaPasswordEncoder().encodePassword(request.getParameter("password"), username), new ArrayList<>()));

								jdbcUserDetailsManager.addUserToGroup(username, request.getParameter("group"));

								/* log */
								log.setModule(Admin.class.getSimpleName());
								log.setAction("Create");
								log.setMessage("id: " + adminId); // (自定义)
								log.setCreateDate(new Date());
								genericRepository.create(log);
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
								int id = Integer.parseInt(ids[i]);

								Admin admin = genericRepository.get(Admin.class, id);

								genericRepository.delete(admin);

								/* tombstone */
/*
								admin.setStatus(-1);
								genericRepository.update(admin);
*/

								/* security */
								for (String group : jdbcUserDetailsManager.findAllGroups()) {
									for (String username : jdbcUserDetailsManager.findUsersInGroup(group)) {
										if (username.equals(admin.getUsername())) {
											jdbcUserDetailsManager.removeUserFromGroup(username, group);
										}
									}
								}

								jdbcUserDetailsManager.deleteUser(admin.getUsername());

								/* log */
								log.setModule(Admin.class.getSimpleName());
								log.setAction("Delete");
								log.setMessage("id: " + id); // (自定义)
								log.setCreateDate(new Date());
								genericRepository.create(log);
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
		case "changePassword":
			try {
				int adminId = _admin.getAdminId();

				transactionTemplate.execute(new TransactionCallbackWithoutResult() {
					@Override
					protected void doInTransactionWithoutResult(TransactionStatus transactionStatus) {
						try {
							Admin admin = genericRepository.get(Admin.class, adminId);

/*
							if (!admin.getPassword().equals(DigestUtils.sha1Hex(request.getParameter("oldPassword")))) {
								throw new RuntimeException("密码错误");
							}

							admin.setPassword(DigestUtils.sha1Hex(request.getParameter("password")));

							genericRepository.update(admin);
*/

							/* security */
							jdbcUserDetailsManager.changePassword(request.getParameter("oldPassword"), new ShaPasswordEncoder().encodePassword(request.getParameter("password"), admin.getUsername()));

							/* log */
							log.setModule(Admin.class.getSimpleName());
							log.setAction("Password");
							log.setMessage("id: " + adminId); // (自定义)
							log.setCreateDate(new Date());
							genericRepository.create(log);

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
		case "listGroup": {
			ArrayNode rows = mapper.createArrayNode();

			for (String group : jdbcUserDetailsManager.findAllGroups()) {
				ObjectNode item = mapper.createObjectNode();

				item.put("value", group);

				rows.add(item);
			}

			result.set("rows", rows);

			break;
		}
		default:
			break;
	}

	out.println(result);
%>
