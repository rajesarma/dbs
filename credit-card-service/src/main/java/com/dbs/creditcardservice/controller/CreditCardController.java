package com.dbs.creditcardservice.controller;

import com.dbs.creditcardservice.service.CreditCardService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CreditCardController {

    private CreditCardService creditCardService;

    private static final Logger log = LoggerFactory.getLogger(CreditCardController.class);

    @Value("${auth_token}")
    String auth_token;

    @Autowired
    CreditCardController(CreditCardService creditCardService) {
        this.creditCardService = creditCardService;
    }

//    @GetMapping(value = "/credit-card/{pan}/{salary}}", produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<String> findByPanNo(@RequestHeader("X-Auth-Token") String authToken, @PathVariable("pan") String pan, @PathVariable("salary") long salary) {

    @GetMapping(value = "/credit-card/{pan}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> findByPanNo(@RequestHeader("X-Auth-Token") String authToken, @PathVariable("pan") String pan) {
        if(auth_token != null && auth_token.equals(authToken)) {
            return new ResponseEntity<>(creditCardService.findByPanNo(pan), HttpStatus.OK);
        }

        log.info("<CREDIT-CARD-SERVICE><CREDIT-CARD-DATA-SERVICE:CREDIT CARD SERVICE><Bad Request with out auth token>");
        return new ResponseEntity<>("SERVICE NOT AVAILABLE", HttpStatus.BAD_REQUEST);
    }
}