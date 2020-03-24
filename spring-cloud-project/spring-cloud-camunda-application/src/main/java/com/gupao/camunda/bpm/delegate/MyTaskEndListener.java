package com.gupao.camunda.bpm.delegate;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.ExecutionListener;
import org.springframework.stereotype.Component;

@Component
public class MyTaskEndListener  implements ExecutionListener {
    @Override
    public void notify(DelegateExecution execution) throws Exception {
//        execution.setVariable("variableSetInExecutionListener", "firstValue");
////        execution.setVariable("eventReceived", execution.getEventName());
        System.out.println("----------------------start 咚咚咚 MyTaskEndListener......");

    }
}