package com.company;

public class Request {

    private String request;
    private String type;

    public Request(String request, String type) {
        this.request = request;
        this.type = type;
    }

    public String getRequest() {
        return request;
    }

    public String getType() {
        return type;
    }
}
