package com.gupao.edu.vip.uti.task316;

import java.io.Serializable;

public class DataOut extends DataIn implements Serializable {

    private static final long serialVersionUID = 2L;

    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
