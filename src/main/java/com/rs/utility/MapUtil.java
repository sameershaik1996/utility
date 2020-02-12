package com.rs.utility;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

@Component
public class MapUtil {
    private static ObjectMapper mapper = new ObjectMapper();

    public static Map<String, Object> convertToMap(Object response) {
        JSONObject jsonObject = null;
        try {
            if (!response.getClass().getSimpleName().equals("String")) {
                String rs = mapper.writeValueAsString(response);
                jsonObject = new JSONObject(rs);
            } else {
                jsonObject = new JSONObject(response.toString());
            }
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        JsonNode jsonNode = JsonUtil.convertJsonFormat(jsonObject);
        return mapper.convertValue(jsonNode, Map.class);
    }
}
