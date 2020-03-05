package com.rs.utility;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("api/")
public class Controller {
    private final Logger LOGGER = LoggerFactory.getLogger(Controller.class);
    private final ObjectMapper mapper=new ObjectMapper();
    @PostMapping("gateway")
    public ResponseEntity<?> processGateway(@RequestBody Object inputObject) throws JsonProcessingException, JSONException {

        JSONObject input=new JSONObject( mapper.writeValueAsString(inputObject));
        Map<String,String> map=new HashMap<>();
        map.put("PaymentResponseStatus","Success");
        input.getJSONObject("PaymentMethod").getJSONArray("PaymentTransaction").getJSONObject(0).put("PaymentResponseStatus",map);

        LOGGER.info("gatewayUEObject {}", input.toString(2));
        return new ResponseEntity<>(inputObject, HttpStatus.OK);
    }

    @PostMapping("exclude")
    public ResponseEntity<?> exclude(@RequestBody Object inputObject) throws JsonProcessingException, JSONException {
        JSONObject input=new JSONObject( mapper.writeValueAsString(inputObject));
        LOGGER.info("excludeUEObject {}", input.toString(2));
        Map<String,Object> map=new HashMap<>();
        map.put("Keys",new ArrayList<>());
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

   
}
