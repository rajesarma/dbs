package com.dbs.customerservice.controller;

import com.dbs.customerservice.model.Customer;
import com.dbs.customerservice.service.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * CustomerController
 *
 * Controller class for Customer Data
 *
 * @author lakshmirajeswararao.p
 * */

@RestController
@RequestMapping("/customer")
@EnableWebMvc
public class CustomerController {

    private CustomerService customerService;
    private static final Logger log = LoggerFactory.getLogger(CustomerController.class);

    @Autowired
    CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    /**
     * This post method takes the JSON input and gives a JSON String value
     * This method validates the customer for Credit Card.
     *
     * @param customer Customer data
     * @return A Response Entity of status
     */

    @PostMapping(value = "/data", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> postCustomerData(@RequestBody Customer customer) {

        log.info("<CUSTOMER><CUSTOMER:CUSTOMER SERVICE><Requesting for Customer validation>");
        return new ResponseEntity<>(customerService.validateCustomer(customer), HttpStatus.OK);
    }
}