package sample.json.jackson;

import sample.java.Bean;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Jackson {
	private static ObjectMapper mapper = new ObjectMapper()
		//.registerModule(new SimpleModule().addDeserializer(Date.class, new DateDeserializer()).addSerializer(new DateSerializer()))

		.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"))
	;


	public static void main(String[] args) throws IOException {
		// json
		ObjectNode objectNode = (ObjectNode) mapper.readTree("{\"name\": \"value\"}");
		System.out.println("objectNode: " + objectNode);

		ArrayNode arrayNode = (ArrayNode) mapper.readTree("[{\"name\": \"value\"}]");
		System.out.println("arrayNode: " + arrayNode);


		// bean
		Bean bean = mapper.readValue("{\"id\": 1, \"date\": \"1970-01-01 00:00:00\"}", Bean.class);
		System.out.println("bean: " + bean);
		System.out.println("json: " + mapper.writeValueAsString(bean));


		// list
		ObjectNode result = mapper.createObjectNode();

		List list = new ArrayList();
		list.add(bean);

		result.put("total", list.size());

		ArrayNode rows = mapper.createArrayNode();
		for (Object o : list) {
			//rows.add(mapper.valueToTree(o));

			ObjectNode item = mapper.valueToTree(o);
			item.put("foo", "bar");
			rows.add(item);
		}
		result.set("rows", rows);

		System.out.println("result: " + result);
	}
}
