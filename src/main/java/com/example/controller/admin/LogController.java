package com.example.controller.admin;

import com.example.dao.GenericRepository;
import com.example.domain.Admin;
import com.example.domain.Log;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin/log")
public class LogController {
	@Autowired
	private ObjectMapper mapper;

	@Autowired
	private GenericRepository genericRepository;

	@RequestMapping("/list")
	@ResponseBody
	public String list(HttpServletRequest request) {
		ObjectNode result = mapper.createObjectNode();

		List list;

		Map<String, Object> params = new HashMap<>();

		String query = StringUtils.defaultString(request.getParameter("query"));
		if (StringUtils.isNotBlank(query)) {
			// 自定义
			params.put("action", query);
			params.put("module", query);
			params.put("detail", "%" + query + "%");

			query = "AND (action = :action OR module = :module OR detail LIKE :detail)";
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

			// 自定义
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
