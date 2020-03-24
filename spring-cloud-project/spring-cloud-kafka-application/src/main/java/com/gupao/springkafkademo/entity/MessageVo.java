package com.gupao.springkafkademo.entity;

import java.io.Serializable;
import java.util.Date;

public class MessageVo implements Serializable {

    private String msgName;

    private String message;

    private String status;

    private Date data;

    public MessageVo(String msgName, String message, String status) {
        this.msgName = msgName;
        this.message = message;
        this.status = status;
    }

    public String getMsgName() {
        return msgName;
    }

    public void setMsgName(String msgName) {
        this.msgName = msgName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "MessageVo{" +
                "msgName='" + msgName + '\'' +
                ", message='" + message + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
