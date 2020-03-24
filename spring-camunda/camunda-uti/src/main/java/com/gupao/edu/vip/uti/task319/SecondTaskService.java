package com.gupao.edu.vip.uti.task319;

import com.gupao.edu.vip.uti.task316.DataIn;
import com.gupao.edu.vip.uti.task316.DataOut;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SecondTaskService implements JavaDelegate {

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        Date date = new Date();
        String strDateFormat = "yyyy-MM-dd HH:mm:ss";
        SimpleDateFormat sdf = new SimpleDateFormat(strDateFormat);
        System.out.println("SecondTaskService time:"+sdf.format(date));
    }
}
