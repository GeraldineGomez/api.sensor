package com.simm.thingsboard.service.util;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;


public class ToUpperCaseDeserializer extends StdDeserializer<String> {

    private static final long serialVersionUID = 7527542687158493910L;

    public ToUpperCaseDeserializer() {
        super(String.class);
    }

    @Override
    public String deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        return _parseString(p, ctxt).toUpperCase();
    }

}
