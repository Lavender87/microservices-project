package com.gupao.camunda.springboot.controller;

import org.camunda.bpm.engine.ManagementService;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.impl.persistence.entity.VariableInstanceEntity;
import org.camunda.bpm.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class CamundaTaskController {

    @Autowired
    private TaskService taskService;

    @Autowired
    private ManagementService managementService;

    public void startTask(){
        List<Task> tasks = taskService.createTaskQuery()
                .taskAssignee("kermit")
                .processVariableValueEquals("orderId", "0815")
                .orderByDueDate().asc()
                .list();
    }

    /**
     *
     * Pagination allows configuring the maximum results retrieved by a query
     *  as well as the position (index) of the first result.
     *  The query shown above retrieves 50 results starting at the result with the index 20.
     * @return
     */
    public List queryTaskList(){
        List<Task> tasks = taskService.createTaskQuery()
                .taskAssignee("kermit")
                .processVariableValueEquals("orderId", "0815")
                .orderByDueDate().asc()
                .listPage(20, 50);

        return tasks;
    }

    /**
     * The query above retrieves all tasks which are assigned to “John Munda” and simultaneously either named “Approve Invoice” or given the fifth degree of priority
     * (assignee = "John Munda" AND (name = "Approve Invoice" OR priority = 5)
     * SELECT DISTINCT *
     * FROM   act_ru_task RES
     * WHERE  RES.assignee_ = 'John Munda'
     *        AND ( Upper(RES.name_) = Upper('Approve Invoice')
     *              OR RES.priority_ = 5 );
     * @return
     */

    public List queryWithOr(){
        List<Task> tasks = taskService.createTaskQuery()
                .taskAssignee("John Munda")
                .or()
                .taskName("Approve Invoice")
                .taskPriority(5)
                .endOr()
                .list();
//
//        List<Task> tasks = taskService.createTaskQuery()
//                .or()
//                .taskCandidateGroup("sales")
//                .taskCandidateGroup("controlling")
//                .endOr()
//                .list();

        return tasks;

    }


    public void nativeQueries(){
        List<Task> tasks = taskService.createNativeTaskQuery()
                .sql("SELECT count(*) FROM " + managementService.getTableName(Task.class) + " T WHERE T.NAME_ = #{taskName}")
                .parameter("taskName", "aOpenTask")
                .list();

        long count = taskService.createNativeTaskQuery()
                .sql("SELECT count(*) FROM " + managementService.getTableName(Task.class) + " T1, "
                        + managementService.getTableName(VariableInstanceEntity.class) + " V1 WHERE V1.TASK_ID_ = T1.ID_")
                .count();
    }


}
