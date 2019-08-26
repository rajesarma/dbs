package com.dbs.creditcardservice.service;

import java.util.Optional;

import com.dbs.creditcardservice.common.Constants;
import com.dbs.creditcardservice.model.CreditCardData;
import com.dbs.creditcardservice.repository.CreditCardRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * CreditCardService
 *
 * Service class for Credit Card Service
 *
 * @author lakshmirajeswararao.p
 * */

@Service
public class CreditCardService {

    private CreditCardRepository creditCardRepository;

    private static final Logger log = LoggerFactory.getLogger(CreditCardService.class);

    @Autowired
    CreditCardService(CreditCardRepository creditCardRepository) {
        this.creditCardRepository = creditCardRepository;
    }

    /**
     * This method takes the Pan as input and gives a String as status
     * This method validates the customer whether he is eligible for credit card or not.
     * Checks if the customer with the specified Pan No. is present in the credit card Dataase. If not returns SELECT
     * Checks if the salary meets the minimum salary condition, If yes SELECT otherwise REJECT
     * Checks if the customer data found in the Credit Card database then check for existing loans.
     * If loans are present then check if the EMI is less than 0 percent of the salary. If yes SELECT otherwise REJECT
     *
     * @param panNo PAN No.
     * @return A String value of status
     */

    public String findByPanNo(String panNo) {

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
