package sample.json.jackson;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/*
public class DateDeserializer extends JsonDeserializer<Date> {
	private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	@Override
	public Date deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
		try {
			return dateFormat.parse(p.getValueAsString());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Class<Date> handledType() {
		return Date.class;
	}
}
*/

public class DateDeserializer extends StdDeserializer<Date> {
	private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	public DateDeserializer() {
		super(Date.class);
	}

	@Override
	public Date deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
		try {
			return dateFormat.parse(p.getValueAsString());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

}
