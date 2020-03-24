package com.gupao.camunda;

import org.camunda.bpm.engine.HistoryService;
import org.camunda.bpm.engine.ProcessEngineConfiguration;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.history.HistoricVariableInstance;
import org.camunda.bpm.engine.test.Deployment;
import org.camunda.bpm.engine.test.ProcessEngineRule;
import org.camunda.bpm.engine.test.RequiredHistoryLevel;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MyBusinessProcessTest20 {

    @Rule
    public ProcessEngineRule processEngineRule = new ProcessEngineRule();

    @Test
    @Deployment
    @RequiredHistoryLevel(ProcessEngineConfiguration.HISTORY_ACTIVITY)
    public void ruleUsageExample() {
        RuntimeService runtimeService = processEngineRule.getRuntimeService();
        runtimeService.startProcessInstanceByKey("ruleUsage");

        HistoryService historyService = processEngineRule.getHistoryService();
        // requires history level >= "activity"
        HistoricVariableInstance variable = historyService
                .createHistoricVariableInstanceQuery()
                .singleResult();

        assertEquals("value", variable.getValue());
    }
}
