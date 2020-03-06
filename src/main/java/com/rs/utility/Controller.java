package com.rs.utility;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
   @Autowired
    private  ObjectMapper mapper;
    @PostMapping("gateway")
    public ResponseEntity<?> processGateway(@RequestBody Object inputObject) throws JsonProcessingException, JSONException {

        JSONObject input=new JSONObject( mapper.writeValueAsString(inputObject));
        JSONObject map=new JSONObject();

        map.put("PaymentResponseStatusId","Success");
        JSONObject extended=input.getJSONObject("PaymentMethod").getJSONArray("PaymentTransaction").getJSONObject(0).getJSONObject("Extended");
        if(extended!=null)
            input.getJSONObject("PaymentMethod").getJSONArray("PaymentTransaction").getJSONObject(0).put("PaymentResponseStatus",map);

        LOGGER.info("===================================================================================");
        LOGGER.info("gatewayUEObject {}",input);
       LOGGER.info("===================================================================================");
       HashMap<String,Object> result = mapper.readValue(input.toString(),new TypeReference<HashMap<String, Object>>(){});
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("exclude")
    public ResponseEntity<?> exclude(@RequestBody Object inputObject) throws JsonProcessingException, JSONException {
        JSONObject input=new JSONObject( mapper.writeValueAsString(inputObject));
        LOGGER.info("===================================================================================");
        LOGGER.info("excludeUEObject {}", input.toString());
        LOGGER.info("===================================================================================");

        Map<String,Object> map=new HashMap<>();
        map.put("Keys",new ArrayList<>());
        return new ResponseEntity<>(map, HttpStatus.OK);
    }


}
