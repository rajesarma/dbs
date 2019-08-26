package com.dbs.customerservice.repository;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "credit-card-service")
@RibbonClient("credit-card-service")
public interface CreditCardServiceProxy {

    @GetMapping("/credit-card/{pan}")
//    ResponseEntity<String> findCreditCardEligibility(@RequestHeader("X-Auth-Token") String authToken, @PathVariable("pan") String pan, @PathVariable("salary") long salary);
    ResponseEntity<String> findCreditCardEligibility(@RequestHeader("X-Auth-Token") String authToken, @PathVariable("pan") String pan);
}