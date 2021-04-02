<%@ page contentType="text/html; charset=UTF-8" %>

<%@ include file="/admin/_auth.jspf" %>

<%@ page import="$java.Utilities" %>

<%@ page import="com.example.domain.Log" %>

<%@ page import="com.fasterxml.jackson.databind.ObjectMapper" %>
<%@ page import="com.fasterxml.jackson.databind.node.ArrayNode" %>
<%@ page import="com.fasterxml.jackson.databind.node.ObjectNode" %>
<%@ page import="org.apache.commons.lang3.StringUtils" %>
<%@ page import="org.apache.commons.lang3.math.NumberUtils" %>
<%@ page import="org.springframework.transaction.support.TransactionTemplate" %>

<%@ page import="java.text.ParseException" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>

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

	switch (action) {
		case "list":
			List list;

			Map<String, Object> params = new HashMap<>();

			String query = StringUtils.defaultString(request.getParameter("query"));
			if (StringUtils.isNotBlank(query)) {
				// (自定义)
				params.put("module", query);

				params.put("action", query);
				params.put("message", "%" + query + "%");

				query = "AND (module = :module OR action = :action OR message LIKE :message)";
				query += " ";
			}

			String adminId = request.getParameter("adminId");
			if (StringUtils.isNotBlank(adminId)) {
				params.put("adminid", Integer.parseInt(adminId));

				query += "AND adminid = :adminid";
				query += " ";
			}

			String status = request.getParameter("status");
			if (StringUtils.isNotBlank(status)) {
				params.put("status", Integer.parseInt(status));

				query += "AND status = :status";
				query += " ";
			}

			String startdate = request.getParameter("startdate");
			if (StringUtils.isNotBlank(startdate)) {
				try {
					startdate = new SimpleDateFormat("yyyy-MM-dd 00:00:00").format(new SimpleDateFormat("yyyy-MM-dd").parse(startdate));
					params.put("startdate", startdate);

					query += "AND createdate >= :startdate";
					query += " ";
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}

			String enddate = request.getParameter("enddate");
			if (StringUtils.isNotBlank(enddate)) {
				try {
					enddate = new SimpleDateFormat("yyyy-MM-dd 23:59:59").format(new SimpleDateFormat("yyyy-MM-dd").parse(enddate));
					params.put("enddate", enddate);

					query += "AND createdate <= :enddate";
					query += " ";
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}

			query = "WHERE 1 = 1 " + query + "ORDER BY createdate DESC";

			int limit = NumberUtils.toInt(request.getParameter("limit"));
			int start = NumberUtils.toInt(request.getParameter("start"));

			if (limit > 0) {
				int pageNum = start > 0 ? (start / limit + 1) : 1;
				int pageSize = limit;

				list = genericRepository.pagedList(Log.class, query, pageNum, pageSize, params);

				result.put("total", genericRepository.count(Log.class, query, params));
			} else {
				list = genericRepository.list(Log.class, query, params);
			}

			ArrayNode rows = mapper.createArrayNode();

			for (Object o : list) {
				ObjectNode item = mapper.valueToTree(o);

				// (自定义)
				String admin_username = null;
				try {
					Admin admin = genericRepository.get(Admin.class, item.get("adminId").asInt());

					admin_username = admin.getUsername();
				} catch (Exception e) {
					//e.printStackTrace();
				}
				item.put("admin_username", admin_username);

				rows.add(item);
			}

			result.set("rows", rows);

			break;
		default:
			break;
	}

	out.println(result);
%>
