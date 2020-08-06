package com.example.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class Jackson extends Json {
	@Autowired
	private ObjectMapper mapper;

	@Override
	public <T> String toJson(T o) {
		try {
			return mapper.writeValueAsString(o);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public <T> T toObject(Class<T> type, String json) {
		try {
			return mapper.readValue(json, type);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
