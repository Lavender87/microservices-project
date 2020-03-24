package com.gupao.camunda.springboot.delegate;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import javax.xml.bind.annotation.XmlValue;

public class AddDataDelegate implements JavaDelegate {
    @Override
    public void execute(DelegateExecution execution) throws Exception {
//        XmlValue customer = execution.getVariableTyped("customerJonny");
//        SpinXmlElement xmlElement = customer.getValue().append(Spin.XML("<creditLimit>1000.00</creditLimit>"));
//        customer = SpinValues.xmlValue(xmlElement).create();
//        execution.setVariable("customerJonny", customer);
        //<?xml version="1.0" encoding="UTF-8"?>
        // <customer xmlns="http:\/\/camunda.org/example" name="Jonny">
        // <address>
        // <street>12 High Street</street><postCode>1234</postCode>
        // </address>
        // <creditLimit xmlns="">1000.00</creditLimit>
        // </customer>

    }
}
