package sample.json.gson;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateSerializer implements JsonSerializer<Date> {
	private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	@Override
	public JsonElement serialize(Date date, Type type, JsonSerializationContext jsonSerializationContext) {
		return new JsonPrimitive(dateFormat.format(date));
	}
}
