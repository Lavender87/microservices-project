package com.gupao.camunda.springboot.controller;

import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.variable.Variables;
import org.camunda.bpm.engine.variable.value.ObjectValue;
import org.springframework.beans.factory.annotation.Autowired;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


/**
 *
 * https://docs.camunda.org/manual/7.12/user-guide/data-formats/xml/
 * https://docs.camunda.org/manual/7.12/user-guide/data-formats/json/
 */
public class XmlElementController {

    /**
     * Serializing Process Variables
     *
     *
     */

    @XmlRootElement(namespace = "http://camunda.org/example")
    public class Customer {

        public Customer(String name, Address address) {
            this.name = name;
            this.address = address;
        }

        protected String name;
        protected Address address;

        @XmlAttribute
        public String getName() { return "";  }

        @XmlElement(namespace = "http://camunda.org/example")
        public Address getAddress() { return null;  }

        /* constructor and setters omitted for brevity */
    }

    public class Address {
        protected String street;
        protected int postCode;

        public Address(String street, int postCode) {
            this.street = street;
            this.postCode = postCode;
        }

        @XmlElement(namespace = "http://camunda.org/example")
        public String getStreet() { return "";  }

        @XmlElement(namespace = "http://camunda.org/example")
        public int getPostCode() { return 0;  }

        /* constructor and setters omitted for brevity */
    }

    @Autowired
    private RuntimeService runtimeService;

    /**
     * ObjectValue customer = runtimeService.getVariableTyped(processInstance.getId(), "customer");
     * String customerXml = customer.getValueSerialized();
     */
    public void addNode(){
        Address address = new Address("12 High Street", 1234);
        Customer customer = new Customer("jonny", address);


        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("aProcess");

        ObjectValue typedCustomerValue =
                Variables.objectValue(customer).serializationDataFormat("application/xml").create();

        runtimeService.setVariable(processInstance.getId(), "customer", typedCustomerValue);
    }


        /*
        customerXml matches:
        <?xml version="1.0" encoding="UTF-8"?>
        <customer xmlns="http://camunda.org/example" name="Jonny">
          <address>
            <street>12 High Street</street>
            <postCode>1234</postCode>
          </address>
        </customer>
        */

}
