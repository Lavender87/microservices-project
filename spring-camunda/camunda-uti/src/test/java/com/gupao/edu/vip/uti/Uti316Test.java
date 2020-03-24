package com.gupao.edu.vip.uti;

import com.gupao.edu.vip.uti.task316.DataOut;
import org.camunda.bpm.engine.management.JobDefinition;
import org.camunda.bpm.engine.runtime.Job;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.test.Deployment;
import org.camunda.bpm.engine.test.ProcessEngineTestCase;
import org.camunda.bpm.engine.variable.VariableMap;
import org.camunda.bpm.engine.variable.Variables;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.camunda.bpm.engine.test.assertions.ProcessEngineTests.*;

public class Uti316Test extends ProcessEngineTestCase {

    @Test
    @Deployment(resources = { "process/utl0316.bpmn" })
    public void testUtl316(){

        DataOut dataOut = new DataOut();
        dataOut.setNum("zero");
        dataOut.setMsg("let's go .....");

        VariableMap variables = Variables.createVariables()
                .putValueTyped("dataEntity", Variables.objectValue(dataOut)
                        .serializationDataFormat(Variables.SerializationDataFormats.JAVA).create());

        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("Process_1khz2ve", variables);

    }

    @Test
    @Deployment(resources = { "process/utl319.bpmn" })
    public void testUtl319(){
//
        ProcessInstance processInstance =
                runtimeService.startProcessInstanceByKey("timer_err_process");
        List<Job> jobs = managementService().createJobQuery().activityId("oneMinutes").list();

        //2 如果在启动时候已经激活事件，不需要再次激活了
//        ProcessInstance processInstance = runtimeService()
//                .createProcessInstanceByKey("timer_err_process")
//                .startAfterActivity("oneMinutes")
//                .setVariables(withVariables("shouldFail", true))
//                .execute();
//        //只是把事件执行了以下，保证在当前程序运行期间，能运行完
//        List<Job> jobs = managementService().createJobQuery().list();

        jobs.forEach(job -> {
            managementService().executeJob(job.getId());
        });

        //3
//        ProcessInstance processInstance = runtimeService().startProcessInstanceByKey("timer_err_process");
//
//        Job nonInterrruptingRepeatableTimer = jobQuery().timers().singleResult();
//        execute(nonInterrruptingRepeatableTimer);


    }




}
