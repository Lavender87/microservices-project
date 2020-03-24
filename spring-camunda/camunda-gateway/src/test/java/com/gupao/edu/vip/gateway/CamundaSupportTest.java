package com.gupao.edu.vip.gateway;

import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.task.Task;
import org.camunda.bpm.engine.test.Deployment;
import org.camunda.bpm.engine.test.ProcessEngineTestCase;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CamundaSupportTest extends ProcessEngineTestCase {


    @Test
    @Deployment(resources = { "process/support.bpmn" })
    public void testBpmnParseListener() throws IOException {

        // start the process instance

        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("Process_Support");

        // check if the first task listener was executed
//        List<String> assigneeList = InformAssigneeTaskListener.assigneeList;
//        assertThat(assigneeList.size(), is(1));
//        assertThat(assigneeList.get(0), is("Kermit"));

        // complete first user task
//        Task task = taskService.createTaskQuery().singleResult();
//        taskService.complete(task.getId());

//        // check if the second task lister was executed
//        assertThat(assigneeList.size(), is(2));
//        assertThat(assigneeList.get(1), is("Fozzie"));
//
//        // reset the assignee
//        task = taskService.createTaskQuery().singleResult();
//        taskService.setAssignee(task.getId(), "Kermit");
//        assertThat(assigneeList.size(), is(3));
//        assertThat(assigneeList.get(2), is("Kermit"));

        // complete second user task
//        taskService.complete(task.getId());

        //查找发送给kermit的任务
        List<Task> tasks = taskService.createTaskQuery().taskAssignee("demo").list();
        Map<String, Object> variables = new HashMap<>();
        variables.put("do","respond");
        for(Task task:tasks){
            //完成一个任务
            System.out.println("demo完成的任务---"+task.getId());
            taskService.complete(task.getId(),variables);
        }


        // check if process instance ended
//        processInstance = runtimeService
//                .createProcessInstanceQuery()
//                .processInstanceId(processInstance.getId())
//                .singleResult();
//        assertThat(processInstance, is(nullValue()));
    }

}
