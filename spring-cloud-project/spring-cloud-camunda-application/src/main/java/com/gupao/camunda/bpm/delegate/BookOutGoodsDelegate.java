package com.gupao.camunda.bpm.delegate;

import org.camunda.bpm.engine.ProcessEngineException;
import org.camunda.bpm.engine.delegate.BpmnError;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class BookOutGoodsDelegate implements JavaDelegate {

    /**
     * Set Business Key from Delegation Code
     * The option to set a new value of business key to already running process instance is shown in the example below:
     * @param execution
     * @throws Exception
     */
    @Override
    public void execute(DelegateExecution execution) throws Exception {
        try {

            String recalculatedKey = (String) execution.getVariable("recalculatedKeyVariable");
            execution.setProcessBusinessKey(recalculatedKey);

        } catch (ProcessEngineException ex) {//NotOnStockException
            throw new BpmnError("NOT_ON_STOCK_ERROR");
        }
    }
}
