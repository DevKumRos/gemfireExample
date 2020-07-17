package com.hello;

import java.io.Serializable;
import java.util.Objects;

public class Customer{

    private String key;
    private String firstName;
    private String lastName;
    private Integer customerNumber;

//Mandatory for PDXSerializable
    public Customer() {

    }

    public Customer(String firstName, String lastName, Integer customerNumber, String key) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.customerNumber = customerNumber;
        this.key =key;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(Integer customerNumber) {
        this.customerNumber = customerNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return firstName.equals(customer.firstName) &&
                lastName.equals(customer.lastName) &&
                customerNumber.equals(customer.customerNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, customerNumber);
    }
}
