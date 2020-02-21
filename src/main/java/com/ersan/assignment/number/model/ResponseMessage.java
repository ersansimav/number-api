package com.ersan.assignment.number.model;

import java.io.Serializable;

public class ResponseMessage implements Serializable {

    private static final long serialVersionUID = 1L;

    private String responseCode;
    private String responseDesc;

    public ResponseMessage(String responseCode, String responseDesc) {
        this.responseCode = responseCode;
        this.responseDesc = responseDesc;
    }

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public String getResponseDesc() {
        return responseDesc;
    }

    public void setResponseDesc(String responseDesc) {
        this.responseDesc = responseDesc;
    }
}
