package com.hello;

import org.apache.geode.pdx.PdxReader;
import org.apache.geode.pdx.PdxSerializable;
import org.apache.geode.pdx.PdxSerializer;
import org.apache.geode.pdx.PdxWriter;

public class CustomerPdx implements PdxSerializer {

    //Mandatory constructor.
    public CustomerPdx() {

    }


    @Override
    public boolean toData(Object objectToSerialize, PdxWriter writer) {
        if (!(objectToSerialize instanceof Customer)) {
            return false;
        }

        Customer customer = (Customer) objectToSerialize;
        writer.writeString("key", customer.getKey());
        writer.writeString("firstName", customer.getFirstName());
        writer.writeString("lastName", customer.getLastName());
        writer.writeInt("customerNumber", customer.getCustomerNumber());
        writer.markIdentityField("key");
        return true;
    }

    @Override
    public Object fromData(Class<?> clazz, PdxReader in) {
        if (!clazz.equals(Customer.class)) {
            return null;
        }
        Customer instance = new Customer();
        instance.setFirstName(in.readString("firstName"));
        instance.setLastName(in.readString("lastName"));
        instance.setCustomerNumber(in.readInt("customerNumber"));
        return instance;
    }
}
