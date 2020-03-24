package com.gupao.camunda.bpm.listener;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.ExecutionListener;
import org.camunda.bpm.engine.delegate.Expression;

public class ExampleFieldInjectedExecutionListener  implements ExecutionListener {

    private Expression fixedValue;

    private Expression dynamicValue;

    @Override
    public void notify(DelegateExecution execution) throws Exception {
        String value =
                fixedValue.getValue(execution).toString() +
                        dynamicValue.getValue(execution).toString();

        execution.setVariable("var", value);
    }
}


//<process id="executionListenersProcess">
//<extensionElements>
//<camunda:executionListener class="org.camunda.bpm.examples.bpmn.executionListener.ExampleFieldInjectedExecutionListener" event="start">
//<camunda:field name="fixedValue" stringValue="Yes, I am " />
//<camunda:field name="dynamicValue" expression="${myVar}" />
//</camunda:executionListener>
//</extensionElements>
//
//<startEvent id="theStart" />
//<sequenceFlow sourceRef="theStart" targetRef="firstTask" />
//
//<userTask id="firstTask" />
//<sequenceFlow sourceRef="firstTask" targetRef="theEnd" />
//
//<endEvent id="theEnd" />
//</process>
