package sample.json;

import sample.java.Bean;

import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.Criteria;
import com.jayway.jsonpath.Filter;
import com.jayway.jsonpath.ReadContext;

import java.util.List;

public class JsonPath {


	public static void main(String[] args) {
		String json = "{ \"name\": \"value\", \"data\": [ { \"id\": 1, \"date\": \"1970-01-01 00:00:00\" }, { \"id\": 2 } ] }";

		String name = com.jayway.jsonpath.JsonPath.read(json, "$.name");
		System.out.println("name: " + name);


		/**/
		Object document = Configuration.defaultConfiguration().jsonProvider().parse(json);

		List data = com.jayway.jsonpath.JsonPath.read(document, "$.data[*]");
		System.out.println("data: " + data);


		/**/
		ReadContext readContext = com.jayway.jsonpath.JsonPath.parse(json);

		int id = readContext.read("$.data[0].id");
		System.out.println("id: " + id);

		List ids = readContext.read("$.data..id", List.class);
		System.out.println("ids: " + ids);

		/**/
		Bean bean = readContext.read("$.data[0]", Bean.class);
		System.out.println("bean: " + bean);

		/**/
		List result = readContext.read(
			//"$.data[?(@.id==1)]"

			"$.data[?]", Filter.filter(
				Criteria.where("id").is(1)
			)
		);
		System.out.println("result: " + result);
	}
}
