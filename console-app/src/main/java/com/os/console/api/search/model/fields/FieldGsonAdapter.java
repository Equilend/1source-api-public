package com.os.console.api.search.model.fields;

import java.lang.reflect.Type;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

public class FieldGsonAdapter implements JsonSerializer<Field> {

	@Override
	public JsonElement serialize(final Field field, final Type typeOfSrc, final JsonSerializationContext context) {
		JsonObject jsonObject = new JsonObject();
		jsonObject.add(field.getFieldName(), new JsonPrimitive(field.getFieldValue()));
		
		return jsonObject;
	}

}