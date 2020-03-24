package com.gupao.edu.vip.uti.task316;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class FirstTaskService  implements JavaDelegate {

    @Override
    public void execute(DelegateExecution execution) throws Exception {

//        // Java Object API: Get Variable
//        Integer val1 = (Integer) execution.getVariable("val1");
//
//// Typed Value API: Get Variable
//        IntegerValue typedVal2 = execution.getVariableTyped("val2");
//        Integer val2 = typedVal2.getValue();
//
//        Integer diff = val1 - val2;
//
//// Java Object API: Set Variable
//        execution.setVariable("diff", diff);
//
//// Typed Value API: Set Variable
//        IntegerValue typedDiff = Variables.integerValue(diff);
//        execution.setVariable("diff", typedDiff);

//        // Transformation is done automatically!
        DataIn dataIn  = (DataIn) execution.getVariable("dataEntity");
        System.out.println("获取上一个task输入的参数："+dataIn.getNum()+dataIn.getMsg());
        DataOut dataOut = new DataOut();
        dataOut.setNum("first");
        dataOut.setMsg(dataIn.getMsg()+",接着FirstTaskService,加工了msg");
//        BeanUtils.copyProperties(dataIn,dataOut);

//        ObjectValue customerDataValue = Variables.objectValue(dataOut)
//                .serializationDataFormat(Variables.SerializationDataFormats.JAVA)
//                .create();

        execution.setVariable("dataEntity", dataOut);
//
//        // You can get the "typed" variable:
//        ObjectValue typedValue = execution.getVariableTyped("application");
//        // and get more information from it if you like:
//        log.info("Typed Variable application values: "
//                + "\n    Type: " + typedValue.getType()
//                + "\n    Value: " + typedValue.getValue()
//                + "\n    SerializationDataFormat: " + typedValue.getSerializationDataFormat()
//                + "\n    ValueSerialized: " + typedValue.getValueSerialized());
//
//        // BTW: When doing queries you can decide if values gets deserialized
//        // important e.g. for Tasklist/REST queries using the JSON anyway
//
//        // now lets make up some rating and set it
//        application.setRating(42);


    }
}
