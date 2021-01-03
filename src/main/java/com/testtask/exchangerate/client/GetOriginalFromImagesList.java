package com.testtask.exchangerate.client;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.testtask.exchangerate.model.Original;

import java.io.IOException;

public class GetOriginalFromImagesList extends JsonDeserializer<Original> {

    @Override
    public Original deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode node = p.getCodec().readTree(p);
        JsonNode jsonNode = node.get("original");
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(jsonNode.toString(), Original.class);
    }
}
