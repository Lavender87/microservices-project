package com.gupao.camunda;

import com.gupao.camunda.bpm.CamundaSpringApplication;
import com.gupao.camunda.bpm.listener.InformAssigneeTaskListener;
import org.camunda.bpm.engine.ProcessEngineConfiguration;
import org.camunda.bpm.engine.RepositoryService;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.repository.ProcessDefinition;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.task.Task;
import org.camunda.bpm.engine.test.Deployment;
import org.camunda.bpm.engine.test.ProcessEngineRule;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = CamundaSpringApplication.class)
public class CamundaTest {

//    @Rule
//    public ProcessEngineRule processEngineRule = new ProcessEngineRule();

    @Autowired
    protected TaskService taskService;
    @Autowired
    protected RuntimeService runtimeService;

    @Autowired
    protected RepositoryService repositoryService;

//    @Before
//    public void init() {
//        taskService = processEngineRule.getTaskService();
//        runtimeService = processEngineRule.getRuntimeService();
//    }

    @Test
    @Deployment(resources = {"bpmnParseListenerOnUserTask.bpmn"})
    public void testBpmnParseListener() throws IOException {

        // start the process instance
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("bpmnParseListenerOnUserTask");
//                                            runtimeService.startProcessInstanceByKey("loanRequest2");
        // check if the first task listener was executed
        List<String> assigneeList = InformAssigneeTaskListener.assigneeList;
        assertThat(assigneeList.size(), is(1));
        assertThat(assigneeList.get(0), is("Kermit"));

        // complete first user task
        Task task = taskService.createTaskQuery().singleResult();
        taskService.complete(task.getId());

        // check if the second task lister was executed
        assertThat(assigneeList.size(), is(2));
        assertThat(assigneeList.get(1), is("Fozzie"));

        // reset the assignee
        task = taskService.createTaskQuery().singleResult();
        taskService.setAssignee(task.getId(), "Kermit");
        assertThat(assigneeList.size(), is(3));
        assertThat(assigneeList.get(2), is("Kermit"));

        // complete second user task
        taskService.complete(task.getId());

        // check if process instance ended
        processInstance = runtimeService
                .createProcessInstanceQuery()
                .processInstanceId(processInstance.getId())
                .singleResult();
        assertThat(processInstance, is(nullValue()));
    }

    @Test
    public void completeTasks(){

        List<Task> tasks = taskService.createTaskQuery().list();
        for(Task task:tasks){
            System.out.println(task.getId());
            taskService.complete(task.getId());
        }
//                .taskAssignee("kermit")
//                .processVariableValueEquals("orderId", "0815")
//                .orderByDueDate().asc()
//                .listPage(20, 50);
        System.out.println(tasks.size());
    }

    public void getAllProcessDefinitions(){
        List<ProcessDefinition> processDefinitions = repositoryService.createProcessDefinitionQuery()
                .processDefinitionKey("invoice")
                .orderByProcessDefinitionVersion()
                .asc()
                .list();
    }

}