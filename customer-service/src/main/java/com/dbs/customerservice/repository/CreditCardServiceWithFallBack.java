package com.dbs.customerservice.repository;

import java.util.Date;

import com.dbs.customerservice.model.Customer;
import com.dbs.customerservice.service.CustomerService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;


/**
 * CreditCardServiceWithFallBack
 *
 * Component class for Customer Data
 *
 * @author lakshmirajeswararao.p
 * */

@Component
public class CreditCardServiceWithFallBack {

    private CreditCardServiceProxy creditCardServiceProxy;

    private static final Logger log = LoggerFactory.getLogger(CustomerService.class);

    @Value("${auth_token}")
    String auth_token;

    @Autowired
    CreditCardServiceWithFallBack(final CreditCardServiceProxy creditCardServiceProxy) { //
        this.creditCardServiceProxy = creditCardServiceProxy;
    }

    /**
     * This method takes the Customer Object as input and gives a String as status
     * This method call the Salary eligibility by using Credit Card Service Feign Client Proxy
     *
     * @param customer Customer data
     * @return A String value of status
     */

    @HystrixCommand(fallbackMethod = "checkCustomerSalaryEligibility_Fallback")
    public String checkCustomerSalaryEligibility(Customer customer) {

        log.info("<CUSTOMER><CUSTOMER:CREDIT CARD SERVICE><Requesting for Credit Card Service for validation>");

//        ResponseEntity<String> responseEntity = creditCardServiceProxy.findCreditCardEligibility(auth_token, customer.getPan(), customer.getSalary());
        ResponseEntity<String> responseEntity = creditCardServiceProxy.findCreditCardEligibility(auth_token, customer.getPan());
        return responseEntity.getBody();
    }

    /**
     * This method takes the Customer Object as input and gives a String as status
     * This method is used as a fallback method if the Credit Card Service fails
     *
     * @param customer Customer data
     * @return A String value of status
     */
    @SuppressWarnings("unused")
    public String checkCustomerSalaryEligibility_Fallback(Customer customer) {

        log.info("<CUSTOMER><CUSTOMER:CREDIT CARD SERVICE><Credit Card Service is Down>");

        return "[ " + new Date() + " ] - Credit Card Service is down.";
    }
}