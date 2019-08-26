package com.dbs.customerservice.controller;

import com.dbs.customerservice.util.CustomerBuilder;
import com.dbs.customerservice.common.Constants;
import com.dbs.customerservice.model.Customer;
import com.dbs.customerservice.service.CustomerService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.mockito.Mockito.when;
import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class CustomerControllerTest {

    @InjectMocks
    CustomerController customerController;
    @Mock
    CustomerService customerService;

    @Test
    public void postCustomerDataTestAgeNotMet(){

        Customer customer = new CustomerBuilder()
                .id(1L)
                .name("Raj")
                .age(19)
                .pan("CUJFG9454E")
                .build();

        when(customerService.validateCustomer(customer)).thenReturn(Constants.STATUS_AGE_NOT_MET);
        ResponseEntity<String> response = customerController.postCustomerData(customer);
        assertEquals(HttpStatus.OK,((ResponseEntity) response).getStatusCode());

        assertEquals(Constants.STATUS_AGE_NOT_MET,((ResponseEntity) response).getBody());
    }

    @Test
    public void postCustomerDataTestForAgeMet(){

        Customer customer = new CustomerBuilder()
                .id(1L)
                .name("Raj")
                .age(22)
                .pan("CUJFG9454E")
                .build();

        when(customerService.validateCustomer(customer)).thenReturn(Constants.STATUS_SELECT);
        ResponseEntity<String> response = customerController.postCustomerData(customer);
        assertEquals(HttpStatus.OK,((ResponseEntity) response).getStatusCode());

        assertEquals(Constants.STATUS_SELECT,((ResponseEntity) response).getBody());
    }

    @Test
    public void postCustomerDataTestForSelect(){

        Customer customer = new CustomerBuilder()
                .id(1L)
                .name("Raj")
                .age(24)
                .pan("ASHDE7232C")
                .build();

        when(customerService.validateCustomer(customer)).thenReturn(Constants.STATUS_SELECT);
        ResponseEntity<String> response = customerController.postCustomerData(customer);
        assertEquals(HttpStatus.OK,((ResponseEntity) response).getStatusCode());

        assertEquals(Constants.STATUS_SELECT,((ResponseEntity) response).getBody());
    }

    @Test
    public void postCustomerDataTestForSalaryNotMet(){

        Customer customer = new CustomerBuilder()
                .id(1L)
                .name("Raj")
                .age(24)
                .pan("DVKGH1565F")
                .build();

        when(customerService.validateCustomer(customer)).thenReturn(Constants.STATUS_SALARY_NOT_MET);
        ResponseEntity<String> response = customerController.postCustomerData(customer);
        assertEquals(HttpStatus.OK,((ResponseEntity) response).getStatusCode());

        assertEquals(Constants.STATUS_SALARY_NOT_MET,((ResponseEntity) response).getBody());
    }

    @Test
    public void postCustomerDataTestForReject(){

        Customer customer = new CustomerBuilder()
                .id(1L)
                .name("Raj")
                .age(22)
                .pan("BTIEF8343D")
                .build();

        when(customerService.validateCustomer(customer)).thenReturn(Constants.STATUS_REJECT);
        ResponseEntity<String> response = customerController.postCustomerData(customer);
        assertEquals(HttpStatus.OK,((ResponseEntity) response).getStatusCode());

        assertEquals(Constants.STATUS_REJECT,((ResponseEntity) response).getBody());
    }
}
