package com.os.console.api.search.model.fields;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

public class FieldSerializer extends StdSerializer<Field>{

	private static final long serialVersionUID = -5994885046100873703L;

	public FieldSerializer() {
		this(null);
	}

	protected FieldSerializer(Class<Field> vc) {
		super(vc);
	}

	@Override
	public void serialize(Field value, JsonGenerator gen, SerializerProvider provider) throws IOException {
		gen.writeStartObject();
		gen.writeStringField(value.getFieldName(), value.getFieldValue());
		gen.writeEndObject();
	}

}
