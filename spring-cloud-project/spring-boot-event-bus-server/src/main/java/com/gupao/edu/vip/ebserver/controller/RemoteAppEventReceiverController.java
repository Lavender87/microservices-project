package com.gupao.edu.vip.ebserver.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class RemoteAppEventReceiverController {

    @PostMapping("receive/remote/event")
    public String receive(@RequestBody Map<String,Object> data){
        String sender = (String)(data.get("sender"));
        String value = JSONObject.toJSONString((data.get("value")));
        String type = (String)(data.get("type"));
        System.out.println(JSONObject.toJSONString(data));
        return "receive";
    }
}
