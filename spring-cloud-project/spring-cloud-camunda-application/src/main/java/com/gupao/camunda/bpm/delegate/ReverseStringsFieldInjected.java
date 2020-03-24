package com.gupao.camunda.bpm.delegate;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.Expression;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class ReverseStringsFieldInjected implements JavaDelegate {

    private Expression text1;
    private Expression text2;

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        String value1 = (String) text1.getValue(execution);
        execution.setVariable("var1", new StringBuffer(value1).reverse().toString());

        String value2 = (String) text2.getValue(execution);
        execution.setVariable("var2", new StringBuffer(value2).reverse().toString());
    }
}


/*
<serviceTask id="javaService" name="Java service invocation"
        camunda:class="org.camunda.bpm.examples.bpmn.servicetask.ReverseStringsFieldInjected">

<extensionElements>
<camunda:field name="text1">
<camunda:expression>${genderBean.getGenderString(gender)}</camunda:expression>
</camunda:field>
<camunda:field name="text2">
<camunda:expression>Hello ${gender == 'male' ? 'Mr.' : 'Mrs.'} ${name}</camunda:expression>
</camunda:field>
</extensionElements>
</serviceTask>
*/
