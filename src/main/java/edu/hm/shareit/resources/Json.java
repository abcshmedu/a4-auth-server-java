package edu.hm.shareit.resources;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public final class Json {

    private Json() {
    }

    /**
     * Serializes an object.
     *
     * @param obj to serialize.
     * @return json-formatted object.
     */
    public static String serializeObject(Object obj) {
        ObjectMapper mapper = new ObjectMapper();

        String json = "";

        try {
            json = mapper.writeValueAsString(obj);
        } catch (JsonProcessingException ignored) {
        }

        return json;
    }
}
