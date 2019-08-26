package com.dbs.creditcardservice.controller;

import com.dbs.creditcardservice.common.Constants;
import com.dbs.creditcardservice.service.CreditCardService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CustomerControllerTest {

    @InjectMocks
    CreditCardController creditCardController;
    @Mock
    CreditCardService creditCardService;

    String auth_token = "aW5mdXNpb25zb2Z0OnBhc3N3b3Jk";

    @Test
    public void getCreditCardTestSelect(){

        String panNo = "CUJFG9454E";

        ReflectionTestUtils.setField(creditCardController, "auth_token", "aW5mdXNpb25zb2Z0OnBhc3N3b3Jk");

        when(creditCardService.findByPanNo(panNo)).thenReturn(Constants.STATUS_SELECT);
        ResponseEntity<String> response = creditCardController.findByPanNo(auth_token, panNo);
        assertEquals(HttpStatus.OK,((ResponseEntity) response).getStatusCode());

        assertEquals(Constants.STATUS_SELECT,((ResponseEntity) response).getBody());
    }

    @Test
    public void getCustomerDataTestForSelect(){

        String panNo = "ASHDE7232C";
        ReflectionTestUtils.setField(creditCardController, "auth_token", "aW5mdXNpb25zb2Z0OnBhc3N3b3Jk");

        when(creditCardService.findByPanNo(panNo)).thenReturn(Constants.STATUS_SELECT);
        ResponseEntity<String> response = creditCardController.findByPanNo(auth_token, panNo);

//        assertEquals(Constants.STATUS_SELECT,((ResponseEntity) response).getBody());
    }

    @Test
    public void getCustomerDataTestForSalaryNotMet(){

        String panNo = "DVKGH1565F";
        ReflectionTestUtils.setField(creditCardController, "auth_token", "aW5mdXNpb25zb2Z0OnBhc3N3b3Jk");

        when(creditCardService.findByPanNo(panNo)).thenReturn(Constants.STATUS_SALARY_NOT_MET);
        ResponseEntity<String> response = creditCardController.findByPanNo(auth_token, panNo);
//        assertEquals(HttpStatus.OK,((ResponseEntity) response).getStatusCode());

        assertEquals(Constants.STATUS_SALARY_NOT_MET,((ResponseEntity) response).getBody());
    }

    @Test
    public void getCustomerDataTestForReject(){
        String panNo = "BTIEF8343D";
        ReflectionTestUtils.setField(creditCardController, "auth_token", "aW5mdXNpb25zb2Z0OnBhc3N3b3Jk");

        when(creditCardService.findByPanNo(panNo)).thenReturn(Constants.STATUS_REJECT);
        ResponseEntity<String> response = creditCardController.findByPanNo(auth_token, panNo);
//        assertEquals(HttpStatus.OK,((ResponseEntity) response).getStatusCode());

        assertEquals(Constants.STATUS_REJECT,((ResponseEntity) response).getBody());
    }
}
