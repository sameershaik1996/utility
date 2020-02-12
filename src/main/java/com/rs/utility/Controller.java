package com.rs.utility;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/")
public class Controller {
    private final Logger LOGGER = LoggerFactory.getLogger(Controller.class);
    @PostMapping("gateway")
    public ResponseEntity<?> processGateway(@RequestBody Object inputObject){
        LOGGER.info("gatewayUEObject {}", MapUtil.convertToMap(inputObject));
        return new ResponseEntity<>(inputObject, HttpStatus.OK);
    }

    @PostMapping("exclude")
    public ResponseEntity<?> exclude(@RequestBody Object inputObject){
        LOGGER.info("excludeUEObject {}", MapUtil.convertToMap(inputObject));
        return new ResponseEntity<>(inputObject, HttpStatus.OK);
    }


}
