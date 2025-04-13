package com.demo.helpers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class JsonConverter implements AttributeConverter<JsonNode, String> {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(JsonNode attribute) {
        if (attribute == null) {
            return "{}";
        }
        String result = attribute.toString();
        return result;
    }

    @Override
    public JsonNode convertToEntityAttribute(String dbData) {
        if (dbData == null || dbData.isEmpty()) {
            return objectMapper.createObjectNode();
        }
        try {
            JsonNode result = objectMapper.readTree(dbData);
            return result;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return objectMapper.createObjectNode();
        }
    }

}
