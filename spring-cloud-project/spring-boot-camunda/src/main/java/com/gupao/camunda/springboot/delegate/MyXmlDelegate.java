package com.gupao.camunda.springboot.delegate;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import javax.xml.bind.annotation.XmlValue;


public class MyXmlDelegate implements JavaDelegate {
    @Override
    public void execute(DelegateExecution execution) throws Exception {
        String xml = "<customer xmlns=\"http:\\/\\/camunda.org/example\" name=\"Jonny\">"
                + "<address>"
                + "<street>12 High Street</street>"
                + "<postCode>1234</postCode>"
                + "</address>"
                + "</customer>";
        XmlValue xmlValue = null;//SpinValues.xmlValue(xml).create();
        execution.setVariable("customerJonny", xmlValue);
    }
}



/**
 * <?xml version="1.0" encoding="UTF-8"?>
 * <customer xmlns="http://camunda.org/example" name="Jonny">
 *   <address>
 *     <street>12 High Street</street>
 *     <postCode>1234</postCode>
 *   </address>
 * </customer>
 *
 *.......
 * <sequenceFlow>
 *   <conditionExpression xsi:type="tFormalExpression">
 *     ${XML(customer).xPath("/customer/address/postCode").element().textContent() == "1234"}
 *   </conditionExpression>
 * </sequenceFlow>
 * ...
 *
 *
 * ...
 * <sequenceFlow>
 *   <conditionExpression xsi:type="tFormalExpression">
 *     ${customer.xPath("/customer/address/postCode").element().textContent() == "1234"}
 *   </conditionExpression>
 * </sequenceFlow>
 * ...
 *
 *
 * ...
 * <scriptTask id="task" name="Script Task" scriptFormat="javascript">
 *   <script>
 *     <![CDATA[
 *     var address = S(customer).element("address");
 *     var city = XML("<city>New York</city>");
 *     address.append(city);
 *     execution.setVariable("address", address.toString());
 *     ]]>
 *   </script>
 * </scriptTask>
 * ...
 */