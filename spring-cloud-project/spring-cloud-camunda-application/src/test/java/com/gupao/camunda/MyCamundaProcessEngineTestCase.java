package com.gupao.camunda;

import org.camunda.bpm.engine.task.Task;
import org.camunda.bpm.engine.test.Deployment;
import org.camunda.bpm.engine.test.ProcessEngineTestCase;
import org.junit.Test;


/**
 * 需要有文件 camunda.cfg.xml
 */
public class MyCamundaProcessEngineTestCase extends ProcessEngineTestCase {



//    @Deployment
    @Test
    public void testSimpleProcess() {
        runtimeService.startProcessInstanceByKey("simpleProcess");

        Task task = taskService.createTaskQuery().singleResult();
        assertEquals("My Task", task.getName());

        taskService.complete(task.getId());
        assertEquals(0, runtimeService.createProcessInstanceQuery().count());
    }
}
