package com.rs.utility;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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
    public ResponseEntity<?> processGateway(@RequestBody Object inputObject) throws JsonProcessingException {


        LOGGER.info("gatewayUEObject {}", mapper.writeValueAsString(inputObject));
        return new ResponseEntity<>(inputObject, HttpStatus.OK);
    }

    @PostMapping("exclude")
    public ResponseEntity<?> exclude(@RequestBody Object inputObject) throws JsonProcessingException {
        LOGGER.info("excludeUEObject {}", mapper.writeValueAsString(inputObject));
        Map<String,Object> map=new HashMap<>();
        map.put("Keys",new ArrayList<>());
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @GetMapping("get")
    @CrossOrigin("*")
    public Map<String,Object> get(){
        Map<String,Object> map=new HashMap<>();
        map.put("Pick notification",10);
        map.put("Pack notification",20);
        map.put("sorting",30);
        map.put("DO updates",40);
        map.put("inventory sync",40);
        map.put("inventory adjustment",40);
        map.put("ASN",40);
        return map;
    }
}
