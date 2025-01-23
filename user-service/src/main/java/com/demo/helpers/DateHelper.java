package com.demo.helpers;

import java.lang.reflect.Type;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

public class DateHelper implements JsonDeserializer<Date> {
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    @Override
    public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
            throws JsonParseException {
        try {
            // Parse chuỗi thành java.util.Date
            java.util.Date utilDate = dateFormat.parse(json.getAsString());

            // Chuyển đổi java.util.Date thành java.sql.Date
            return new Date(utilDate.getTime());
        } catch (ParseException e) {
            throw new JsonParseException("Failed to parse date: " + json.getAsString(), e);
        }
    }
}