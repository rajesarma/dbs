package com.dbs.customerservice.service;

import com.dbs.customerservice.common.Constants;
import com.dbs.customerservice.model.Customer;
import com.dbs.customerservice.repository.CreditCardServiceWithFallBack;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * CustomerService
 *
 * Service class for Customer Data
 *
 * @author lakshmirajeswararao.p
 * */

@Service
public class CustomerService {

    private CreditCardServiceWithFallBack creditCardServiceWithFallBack;

    private static final Logger log = LoggerFactory.getLogger(CustomerService.class);

    @Autowired
    CustomerService(CreditCardServiceWithFallBack creditCardServiceWithFallBack) {
        this.creditCardServiceWithFallBack = creditCardServiceWithFallBack;
    }

    /**
     * This method takes the Customer Object as input and gives a String as status
     * This method validates the customer for age, if he satisfies then it will call the Salary Eligibility
     *
     * @param customer Customer data
     * @return A String value of status
     */

    public String validateCustomer(Customer customer) {

        if(customer.getAge() > Constants.MIN_AGE && customer.getAge() < Constants.MAX_AGE) {
            log.info("<CUSTOMER><CUSTOMER:VALIDATE><Age met for the specified criteria>");

            return creditCardServiceWithFallBack.checkCustomerSalaryEligibility(customer);
        }

        log.info("<CUSTOMER><CUSTOMER:VALIDATE><Age not met for the specified criteria>");

        return Constants.STATUS_AGE_NOT_MET;
    }
}
