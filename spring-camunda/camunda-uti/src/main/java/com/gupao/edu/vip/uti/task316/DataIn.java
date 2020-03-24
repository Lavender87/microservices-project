package com.gupao.edu.vip.uti.task316;

import java.io.Serializable;
import java.util.UUID;

public class DataIn implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id = UUID.randomUUID().toString();


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    private String num;

    private String msg;

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
