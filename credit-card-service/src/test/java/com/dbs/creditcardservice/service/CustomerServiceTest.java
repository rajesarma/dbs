package com.dbs.creditcardservice.service;

import com.dbs.creditcardservice.common.Constants;
import com.dbs.creditcardservice.model.CreditCardData;
import com.dbs.creditcardservice.repository.CreditCardRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CustomerServiceTest {

    @InjectMocks
    CreditCardService creditCardService;
    @Mock
    CreditCardRepository creditCardRepository;

    @Test
    public void postValidateCustomerTestNoLoansSelect(){

        String panNo = "CUJFG9454E";

        Optional<CreditCardData> creditCardData = Optional.of(new CreditCardData("CUJFG9454E", true, 6800,69000));

        when(creditCardRepository.findByPanNo(panNo)).thenReturn(creditCardData);
        String response = creditCardService.findByPanNo(panNo);
        assertEquals(Constants.STATUS_SELECT, response);
    }

    @Test
    public void postValidateCustomerTestForSelect(){

        String panNo = "ASHDE7232C";

        Optional<CreditCardData> creditCardData = Optional.of(new CreditCardData("ASHDE7232C", false,0.0, 65000));

        when(creditCardRepository.findByPanNo(panNo)).thenReturn(creditCardData);
        String response = creditCardService.findByPanNo(panNo);
        assertEquals(Constants.STATUS_SELECT, response);
    }

    @Test
    public void postValidateCustomerTestForSalaryNotMet(){

        String panNo = "DVKGH1565F";

        Optional<CreditCardData> creditCardData = Optional.of(new CreditCardData("DVKGH1565F", false, 0.0, 45000));

        when(creditCardRepository.findByPanNo(panNo)).thenReturn(creditCardData);
        String response = creditCardService.findByPanNo(panNo);
        assertEquals(Constants.STATUS_SALARY_NOT_MET, response);

    }

    @Test
    public void postValidateCustomerTestForReject(){

        String panNo = "BTIEF8343D";

        Optional<CreditCardData> creditCardData = Optional.of(new CreditCardData("BTIEF8343D", true, 6500, 62000));

        when(creditCardRepository.findByPanNo(panNo)).thenReturn(creditCardData);
        String response = creditCardService.findByPanNo(panNo);
        assertEquals(Constants.STATUS_REJECT, response);
    }
}
