package com.gupao.vip.drools.edu.controller;

import com.gupao.vip.drools.edu.entity.Address;
import com.gupao.vip.drools.edu.entity.ParamInfo;
import com.gupao.vip.drools.edu.entity.RuleResult;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.UUID;

@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private KieSession kieSession;

    @ResponseBody
    @RequestMapping(value = "/address/{postCode}",method = RequestMethod.GET)
    public void test(@PathVariable(value = "postCode") String postCode){
        // 以下的数据可以从数据库来，这里写死了
        Address address = new Address();
        address.setPostcode(postCode);
        // 使用规则引擎
        kieSession.insert(address);
        int ruleFiredCount = kieSession.fireAllRules();
        System.out.println("触发了" + ruleFiredCount + "条规则");
        System.out.println("---------------------------------");
    }


    @ResponseBody
    @RequestMapping(value = "/mydata/{murexid}/{settletype}",method = RequestMethod.GET)
    public String testPapamInfo(@PathVariable(value = "murexid") String murexid,
                                @PathVariable(value = "settletype") String settletype){
        ParamInfo paramInfo = new ParamInfo() ;

        paramInfo.setIsoCode("USF")
                .setLocalId("4556792")
                .setDataStatus("UPDATE");

        if(!"0".equals(murexid))paramInfo.setMurex3Id(murexid);
        if(!"0".equals(settletype))paramInfo.setSettleType(settletype);

        // 入参
        kieSession.insert(paramInfo) ;

        // 返参
        RuleResult ruleResult = new RuleResult() ;
        kieSession.insert(ruleResult) ;
        int ruleFiredCount = kieSession.fireAllRules() ;
        System.out.println("触发了" + ruleFiredCount + "条规则");
        System.out.println("---------------------------------");
        return ruleResult.getMsg();
    }

//    @Test
//    public void testInConstrait() throws Exception {
//        sessionStatefull = KnowledgeSessionHelper
//                .getStatefulKnowledgeSessionWithCallback(kieContainer, "ksession-lesson3");
//        OutputDisplay display = new OutputDisplay();
//        sessionStatefull.setGlobal("showResult", display);
//        CashFlow cashFlow = new CashFlow();
//        cashFlow.setType(CashFlow.CREDIT);
//        sessionStatefull.insert(cashFlow);
//        sessionStatefull.fireAllRules();
//    }

    public String cashFlow(){
//        OutputDisplay display = new OutputDisplay();
//        sessionStatefull.setGlobal("showResults", display);
//        Account a = new Account();
//        a.setAccountNo(1);
//        a.setBalance(0);
//        sessionStatefull.insert(a);
//        CashFlow cash1 = new CashFlow();
//        cash1.setAccountNo(1);
//        cash1.setAmount(1000);
//        cash1.setType(CashFlow.CREDIT);
//        sessionStatefull.insert(cash1);
//        sessionStatefull.fireAllRules();
//        Assert.assertEquals(a.getBalance(), 1000,0);
        return "";
    }

//    public void testcalculateBalance() throws Exception {
//        sessionStatefull = KnowledgeSessionHelper
//                .getStatefulKnowledgeSessionWithCallback(kieContainer, "ksession-lesson2");
//        OutputDisplay display = new OutputDisplay();
//        sessionStatefull.setGlobal("showResults", display);
//        Account a = new Account();
//        a.setAccountNo(1);
//        a.setBalance(0);
//        sessionStatefull.insert(a);
//        CashFlow cash1 = new CashFlow();
//        cash1.setAccountNo(1);
//        cash1.setAmount(1000);
//        cash1.setMvtDate(DateHelper.getDate("2016-01-15"));
//        cash1.setType(CashFlow.CREDIT);
//        sessionStatefull.insert(cash1);
//        CashFlow cash2 = new CashFlow();
//        cash2.setAccountNo(1);
//        cash2.setAmount(500);
//        cash2.setMvtDate(DateHelper.getDate("2016-02-15"));
//        cash2.setType(CashFlow.DEBIT);
//        sessionStatefull.insert(cash2);
//        CashFlow cash3 = new CashFlow();
//        cash3.setAccountNo(1);
//        cash3.setAmount(1000);
//        cash3.setMvtDate(DateHelper.getDate("2016-04-15"));
//        cash3.setType(CashFlow.CREDIT);
//        sessionStatefull.insert(cash3);
//        AccountingPeriod period = new AccountingPeriod();
//        period.setStartDate(DateHelper.getDate("2016-01-01"));
//        period.setEndDate(DateHelper.getDate("2016-03-31"));
//        sessionStatefull.insert(period);
//        sessionStatefull.fireAllRules();
//        Assert.assertTrue(a.getBalance()==500);
//    }
}
