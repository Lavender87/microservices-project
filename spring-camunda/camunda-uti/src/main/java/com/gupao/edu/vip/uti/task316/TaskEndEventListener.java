package com.gupao.edu.vip.uti.task316;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.ExecutionListener;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import java.util.logging.Logger;

public class TaskEndEventListener implements ExecutionListener {

    private final Logger LOGGER = Logger.getLogger(TaskEndEventListener.class.getName());

    @Override
    public void notify(DelegateExecution execution) throws Exception {
        System.out.println("----------------------结束标志标志......");
        DataIn dataIn  = (DataIn) execution.getVariable("dataEntity");
        System.out.println("获取上一个task输入的参数："+dataIn.getNum()+dataIn.getMsg());
//        if(!"second".equals(dataIn.getMsg())){
//            LOGGER.info("\n\n  ... LoggerDelegate invoked by "
//                    + "processDefinitionId=" + execution.getProcessDefinitionId()
//                    + ", activityId=" + execution.getCurrentActivityId()
//                    + ", activityName='" + execution.getCurrentActivityName().replaceAll("\n", " ") + "'"
//                    + ", processInstanceId=" + execution.getProcessInstanceId()
//                    + ", businessKey=" + execution.getProcessBusinessKey()
//                    + ", executionId=" + execution.getId()
//                    + ", modelName=" + execution.getBpmnModelInstance().getModel().getModelName()
//                    + ", elementId" + execution.getBpmnModelElementInstance().getId()
//                    + " \n\n");
//        }

    }
}