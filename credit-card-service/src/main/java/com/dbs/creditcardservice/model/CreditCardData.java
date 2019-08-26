package com.dbs.creditcardservice.model;

import java.util.Objects;

public class CreditCardData {

    public CreditCardData() {

    }

    public CreditCardData(String panNo, boolean loans, double emi, long salary) {
        this.panNo = panNo;
        this.loans = loans;
        this.emi = emi;
        this.salary = salary;
    }

    private String panNo;
    private boolean loans;
    private double emi;
    private long salary;

    public long getSalary() {
        return salary;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CreditCardData)) return false;
        CreditCardData that = (CreditCardData) o;
        return isLoans() == that.isLoans() &&
                Double.compare(that.getEmi(), getEmi()) == 0 &&
                getSalary() == that.getSalary() &&
                Objects.equals(getPanNo(), that.getPanNo());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPanNo(), isLoans(), getEmi(), getSalary());
    }

    @Override
    public String toString() {
        return "CreditCardData{" +
                "panNo='" + panNo + '\'' +
                ", loans=" + loans +
                ", emi=" + emi +
                ", salary=" + salary +
                '}';
    }



    public void setSalary(long salary) {
        this.salary = salary;
    }

    public String getPanNo() {
        return panNo;
    }

    public void setPanNo(String panNo) {
        this.panNo = panNo;
    }

    public boolean isLoans() {
        return loans;
    }

    public void setLoans(boolean loans) {
        this.loans = loans;
    }

    public double getEmi() {
        return emi;
    }

    public void setEmi(double emi) {
        this.emi = emi;
    }


}
