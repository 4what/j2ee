package sample.json.gson;

import sample.java.Bean;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Gson {
	private static com.google.gson.Gson gson =
		new
			//Gson()

			GsonBuilder()
				//.registerTypeAdapter(Date.class, new DateDeserializer())
				//.registerTypeAdapter(Date.class, new DateSerializer())

				.setDateFormat("yyyy-MM-dd HH:mm:ss")

				.disableHtmlEscaping()

				.serializeNulls()

				.create()
		;


	public static void main(String[] args) {
		// json
		JsonObject jsonObject = new JsonParser().parse("{\"name\": \"value\"}").getAsJsonObject();
		System.out.println("jsonObject: " + jsonObject);

		JsonArray jsonArray = new JsonParser().parse("[{\"name\": \"value\"}]").getAsJsonArray();
		System.out.println("jsonArray: " + jsonArray);


		// bean
		Bean bean = gson.fromJson("{\"id\": 1, \"date\": \"1970-01-01 00:00:00\"}", Bean.class);
		System.out.println("bean: " + bean);
		System.out.println("json: " + gson.toJson(bean));


		// list
		JsonObject result = new JsonObject();

		List list = new ArrayList();
		list.add(bean);

		result.addProperty("total", list.size());

		JsonArray rows = new JsonArray();
		for (Object o : list) {
			//rows.add(gson.toJsonTree(o));

			JsonObject item = gson.toJsonTree(o).getAsJsonObject();
			item.addProperty("foo", "bar");
			rows.add(item);
		}
		result.add("rows", rows);

		System.out.println("result: " + result);
	}
}
