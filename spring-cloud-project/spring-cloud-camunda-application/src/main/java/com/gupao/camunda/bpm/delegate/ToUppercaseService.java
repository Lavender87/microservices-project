package com.gupao.camunda.bpm.delegate;

import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

@Component
public class ToUppercaseService implements JavaDelegate {
    @Override
    public void execute(DelegateExecution execution) throws Exception {
//        String var = (String) execution.getVariable("input");
//        var = var.toUpperCase();
//        execution.setVariable("input", var);

        System.out.println("----------------------噢啦拉啦啦啦啦阿拉蕾 ToUppercaseService.");


    }


    /**
     * Access Process Engine Services
     * It is possible to access the public API services (RuntimeService, TaskService, RepositoryService …) from the Delegation Code.
     * The following is an example showing how to access the TaskService from a JavaDelegate implementation.
     * @param execution
     * @throws Exception
     */
  /*  @Override
    public void execute(DelegateExecution execution) throws Exception {
        TaskService taskService = execution.getProcessEngineServices().getTaskService();
//        taskService.createTaskQuery()...;
    }*/
}
