package com.dbs.creditcardservice.repository;

import com.dbs.creditcardservice.model.CreditCardData;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * CreditCardData Repository class
 */

@Repository
public class CreditCardRepository {

    private static List<CreditCardData> creditCardData;

    static {

        // Dummy Data
        creditCardData = Arrays.asList(
                new CreditCardData("ASHDE7232C", false,0.0, 65000),
                new CreditCardData("BTIEF8343D", true, 6500, 62000),
                new CreditCardData("CUJFG9454E", true, 6800,69000),
                new CreditCardData("DVKGH1565F", false, 0.0, 45000),
                new CreditCardData("EWLHI2676G", true, 9900,86000)
        );
    }

    public Optional<CreditCardData> findByPanNo(String panNo) {
        return creditCardData.stream()
                .filter(c -> c.getPanNo().equalsIgnoreCase(panNo))
                .findAny();
    }
}
