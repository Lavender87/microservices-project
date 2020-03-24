package com.gupao.camunda.springboot.controller;

import org.camunda.bpm.engine.ManagementService;
import org.camunda.bpm.engine.RepositoryService;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.management.JobDefinition;
import org.camunda.bpm.engine.repository.ProcessDefinition;
import org.camunda.bpm.engine.runtime.ActivityInstance;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.runtime.ProcessInstanceQuery;
import org.camunda.bpm.engine.runtime.ProcessInstanceWithVariables;
import org.camunda.bpm.model.bpmn.BpmnModelInstance;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class CamundaProcessDefinitionsController {

    @Autowired
    private RepositoryService repositoryService;

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private ManagementService managementService;


    public void startProcessInstance(){
        Map<String, Object> variables = new HashMap<String,Object>();
        variables.put("creditor", "Nice Pizza Inc.");
        ProcessInstance instance = runtimeService.startProcessInstanceByKey("invoice", variables);
    }


    /**
     * Start a Process Instance at Any Set of Activities
     * The following starts a process instance before the activity SendInvoiceReceiptTask and the embedded sub process DeliverPizzaSubProcess:
     *
     */
    public void startProcessInstance2(){
        ProcessInstance instance = runtimeService.createProcessInstanceByKey("invoice")
                .startBeforeActivity("SendInvoiceReceiptTask")
                .setVariable("creditor", "Nice Pizza Inc.")
                .startBeforeActivity("DeliverPizzaSubProcess")
                .setVariableLocal("destination", "12 High Street")
                .execute();
    }


    /**
     * Variables in Return
     */
    public void startProWithVariableRetuan(){
        ProcessInstanceWithVariables instance = runtimeService.createProcessInstanceByKey("invoice")
                .startBeforeActivity("SendInvoiceReceiptTask")
                .setVariable("creditor", "Nice Pizza Inc.")
                .startBeforeActivity("DeliverPizzaSubProcess")
                .setVariableLocal("destination", "12 High Street")
                .executeWithVariablesInReturn();
    }


    /**
     * Query for Process Instances
     *
     * You can query for all currently running process instances using the ProcessInstanceQuery offered by the RuntimeService:
     */
    public List queryProcessInstances(){
        List list = runtimeService.createProcessInstanceQuery()
                .processDefinitionKey("invoice")
                .variableValueEquals("creditor", "Nice Pizza Inc.")
                .list();
        return list;
    }


    /**
     * The above query returns all deployed process definitions for the key invoice ordered by their version property.
     * @return
     */
    public List processList(){
        List<ProcessDefinition> processDefinitions = repositoryService.createProcessDefinitionQuery()
                .processDefinitionKey("invoice")
                .orderByProcessDefinitionVersion()
                .asc()
                .list();
        return processDefinitions;
    }


    /**
     * Each activity instance is assigned a unique ID. The ID is persistent,
     * if you invoke this method multiple times, the same activity instance IDs will be returned for the same activity instances.
     * (However, there might be different executions assigned, see below)
     * @param processInstance
     */
    public void retrieveActivityInstance(ProcessInstance processInstance){
        ActivityInstance rootActivityInstance = runtimeService
                .getActivityInstance(processInstance.getProcessInstanceId());
    }

    /**
     * Query for Job Definitions
     */
    public void QueryJobDefinitions(){
        managementService.createJobQuery()
                .duedateHigherThan(new Date())
                .list();

        managementService.createJobDefinitionQuery()
                .processDefinitionKey("orderProcess")
                .list();

        List<JobDefinition> jobDefinitions = managementService.createJobDefinitionQuery()
                .processDefinitionKey("orderProcess")
                .activityIdIn("processPayment")
                .list();
        for(JobDefinition jobDefinition:jobDefinitions){
            managementService.suspendJobDefinitionById(jobDefinition.getId(), true);
        }
    }



    public void testRepositoryService() {
        runtimeService.startProcessInstanceByKey("PROCESS_KEY");
        String processDefinitionId = repositoryService.createProcessDefinitionQuery()
                .processDefinitionKey("PROCESS_KEY").singleResult().getId();
        BpmnModelInstance modelInstance = repositoryService.getBpmnModelInstance(processDefinitionId);
    }





}
