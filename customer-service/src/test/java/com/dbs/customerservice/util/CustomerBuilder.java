package com.dbs.customerservice.util;

import com.dbs.customerservice.model.Customer;
import org.springframework.test.util.ReflectionTestUtils;

public class CustomerBuilder {

    private Customer model;

    public CustomerBuilder() {
        model = new Customer();
    }

    public CustomerBuilder id(Long id) {
        ReflectionTestUtils.setField(model, "id", id);
        return this;
    }

    public CustomerBuilder name(String name) {
        model.setName(model.getName());
        return this;
    }

    public CustomerBuilder age(int age) {
        model.setAge(age);
        return this;
    }

    public CustomerBuilder pan(String pan) {
        model.setPan(pan);
        return this;
    }

    public Customer build() {
        return model;
    }
}
