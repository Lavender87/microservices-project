package com.gupao.springkafkademo.controller;

import com.alibaba.fastjson.JSONObject;
import com.gupao.springkafkademo.entity.Company;
import com.gupao.springkafkademo.entity.Employee;
import com.gupao.springkafkademo.entity.MessageVo;
import com.gupao.springkafkademo.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
public class MessageSendController {
//    @Autowired
//    private KafkaTemplate<String, String> kafkaTemplate;

    //方式二
    private final KafkaTemplate<String, String> kafkaTemplate;

    private final String topic;

    public MessageSendController(KafkaTemplate<String, String> kafkaTemplate,
                                 @Value("${kafka.topic}") String topic) {
        this.kafkaTemplate = kafkaTemplate;
        this.topic = topic;

        System.out.println("this.topic--"+this.topic);
    }

    @Autowired
    private CompanyService companyService;

    @GetMapping("/send/{messge}")
    public String send(@PathVariable String messge) {
        kafkaTemplate.send("test", " 来自前端KafkaTemplate方式发送数据message:" + messge);
        return messge;
    }

    @GetMapping("/sendObj/{messge}")
    public String sendObj(@PathVariable String messge) {
        MessageVo msgVo = new MessageVo("测试",messge,"SUCCESS");
        kafkaTemplate.send("test", JSONObject.toJSONString(msgVo));
        return messge;
    }

    @PostMapping("/send/msg")
    public String sendMsg(@RequestBody MessageVo msg){

        return JSONObject.toJSONString(msg);
    }

    @GetMapping("/company/list")
    public List<Company> getAllCompany(){
//        return companyService.GetAllCompanyData();
        return null;
    }


    @GetMapping("/employee/list")
    public List<Employee> getAllEmployee(){
        return companyService.findALL();
    }

    @GetMapping("/employee/{id}")
    public Employee getById(@PathVariable int id){
        return companyService.queryById(id);
    }

    @PostMapping("/employee/add")
    public int addEmployee(@RequestBody Employee employee){
        return companyService.insertEmployee(employee);
    }

    @GetMapping("/employee/count")
    public int getSize(){
        return companyService.getSize();
    }

}
