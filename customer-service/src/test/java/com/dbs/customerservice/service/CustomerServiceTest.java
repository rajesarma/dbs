package com.dbs.customerservice.service;

import com.dbs.customerservice.util.CustomerBuilder;
import com.dbs.customerservice.common.Constants;
import com.dbs.customerservice.model.Customer;
import com.dbs.customerservice.repository.CreditCardServiceWithFallBack;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CustomerServiceTest {

    @InjectMocks
    CustomerService customerService;
    @Mock
    CreditCardServiceWithFallBack creditCardServiceWithFallBack;

    @Test
    public void postValidateCustomerTestAgeNotMet(){

        Customer customer = new CustomerBuilder()
                .id(1L)
                .name("Raj")
                .age(19)
                .pan("CUJFG9454E")
                .build();

//        when(creditCardServiceWithFallBack.checkCustomerSalaryEligibility(customer)).thenReturn(Constants.STATUS_AGE_NOT_MET);
        String response = customerService.validateCustomer(customer);
        assertEquals(Constants.STATUS_AGE_NOT_MET, response);
    }

    @Test
    public void postValidateCustomerTestForAgeMet(){

        Customer customer = new CustomerBuilder()
                .id(1L)
                .name("Raj")
                .age(22)
                .pan("CUJFG9454E")
                .build();

        when(creditCardServiceWithFallBack.checkCustomerSalaryEligibility(customer)).thenReturn(Constants.STATUS_SELECT);
        String response = customerService.validateCustomer(customer);
        assertEquals(Constants.STATUS_SELECT, response);
    }

    @Test
    public void postValidateCustomerTestForSelect(){

        Customer customer = new CustomerBuilder()
                .id(1L)
                .name("Raj")
                .age(24)
                .pan("ASHDE7232C")
                .build();

        when(creditCardServiceWithFallBack.checkCustomerSalaryEligibility(customer)).thenReturn(Constants.STATUS_SELECT);
        String response = customerService.validateCustomer(customer);
        assertEquals(Constants.STATUS_SELECT, response);

    }

    @Test
    public void postValidateCustomerTestForSalaryNotMer(){

        Customer customer = new CustomerBuilder()
                .id(1L)
                .name("Raj")
                .age(24)
                .pan("DVKGH1565F")
                .build();

        when(creditCardServiceWithFallBack.checkCustomerSalaryEligibility(customer)).thenReturn(Constants.STATUS_SALARY_NOT_MET);
        String response = customerService.validateCustomer(customer);
        assertEquals(Constants.STATUS_SALARY_NOT_MET, response);
    }

    @Test
    public void postValidateCustomerTestForReject(){

        Customer customer = new CustomerBuilder()
                .id(1L)
                .name("Raj")
                .age(22)
                .pan("BTIEF8343D")
                .build();

        when(creditCardServiceWithFallBack.checkCustomerSalaryEligibility(customer)).thenReturn(Constants.STATUS_REJECT);
        String response = customerService.validateCustomer(customer);
        assertEquals(Constants.STATUS_REJECT, response);
    }
}
