package com.dbs.creditcardservice.service;

import java.util.Optional;

import com.dbs.creditcardservice.common.Constants;
import com.dbs.creditcardservice.model.CreditCardData;
import com.dbs.creditcardservice.repository.CreditCardRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreditCardService {

    private CreditCardRepository creditCardRepository;

    private static final Logger log = LoggerFactory.getLogger(CreditCardService.class);

    @Autowired
    CreditCardService(CreditCardRepository creditCardRepository) {
        this.creditCardRepository = creditCardRepository;
    }

    public String findByPanNo(String panNo) {
//    public String findByPanNo(String panNo, long salary) {

        log.info("<CREDIT-CARD-SERVICE><CREDIT-CARD-DATA-SERVICE:CREDIT CARD SERVICE><Requesting for Credit Card Service for validation>");

        Optional<CreditCardData> creditCardData = creditCardRepository.findByPanNo(panNo);

        if(creditCardData.isPresent()) {

            if(creditCardData.get().getSalary() < Constants.MIN_SALARY) {
                log.info("<CREDIT-CARD-SERVICE><CREDIT-CARD-DATA-SERVICE:CREDIT CARD SERVICE><Customer has not met for minimum salary, So Reject>");

                return Constants.STATUS_SALARY_NOT_MET;
            }

            log.info("<CREDIT-CARD-SERVICE><CREDIT-CARD-DATA-SERVICE:CREDIT CARD SERVICE><Credit Card Data available for the customer>");

//            if(checkDataWithSalary(creditCardData.get(), salary)) {
            if(checkDataWithSalary(creditCardData.get()) ) {
                return Constants.STATUS_SELECT;
            } else {

                log.info("<CREDIT-CARD-SERVICE><CREDIT-CARD-DATA-SERVICE:CREDIT CARD SERVICE><Customer not eligible for Credit Card>");
                return Constants.STATUS_REJECT;
            }
        }

        log.info("<CREDIT-CARD-SERVICE><CREDIT-CARD-DATA-SERVICE:CREDIT CARD SERVICE><Customer has not taken for any Credit Card, So Select>");
        return Constants.STATUS_SELECT;
    }

    private boolean checkDataWithSalary(CreditCardData creditCardData) {

        if(!creditCardData.isLoans()) {
            log.info("<CREDIT-CARD-SERVICE><CREDIT-CARD-DATA-SERVICE:CREDIT CARD SERVICE><Loans not available for the Customer, So Select>");
            return true;
        } else {
            return creditCardData.getEmi() <= (creditCardData.getSalary() / 10);
        }
    }

}
