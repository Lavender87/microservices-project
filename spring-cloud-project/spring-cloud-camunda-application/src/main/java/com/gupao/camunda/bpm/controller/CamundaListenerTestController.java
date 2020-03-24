package com.gupao.camunda.bpm.controller;

import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.test.Deployment;
import org.springframework.beans.factory.annotation.Autowired;
import org.junit.Assert;

import java.util.HashMap;
import java.util.Map;

public class CamundaListenerTestController {

    @Autowired
    private RuntimeService runtimeService;


    /**
     * 测试事件监听 ExampleFieldInjectedExecutionListener
     */
    @Deployment(resources = {
            "org/camunda/bpm/examples/bpmn/executionListener/ExecutionListenersFieldInjectionProcess.bpmn20.xml"
    })
    public void testExecutionListenerFieldInjection() {
        Map<String, Object> variables = new HashMap<String, Object>();
        variables.put("myVar", "listening!");

        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("executionListenersProcess", variables);

        Object varSetByListener = runtimeService.getVariable(processInstance.getId(), "var");
        Assert.assertNotNull(varSetByListener);
        Assert.assertTrue(varSetByListener instanceof String);

        // Result is a concatenation of fixed injected field and injected expression
        Assert.assertEquals("Yes, I am listening!", varSetByListener);
    }

}
