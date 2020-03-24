package com.gupao.edu.vip.uti.task316;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.variable.Variables;
import org.camunda.bpm.engine.variable.value.ObjectValue;

public class SecondTaskService implements JavaDelegate {

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        DataIn dataIn  = (DataIn) execution.getVariable("dataEntity");
        System.out.println("获取上一个task输入的参数："+dataIn.getNum()+dataIn.getMsg());
        DataOut dataOut = new DataOut();
        dataOut.setNum("second");
        dataOut.setMsg(dataIn.getMsg()+",接着SecondTaskService,加工了msg");

//        ObjectValue customerDataValue = Variables.objectValue(dataOut)
//                .serializationDataFormat(Variables.SerializationDataFormats.JAVA)
//                .create();

        execution.setVariable("dataEntity", dataOut);
    }
}
