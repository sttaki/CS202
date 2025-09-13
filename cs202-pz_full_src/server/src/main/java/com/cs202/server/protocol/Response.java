package com.cs202.server.protocol;

import java.io.Serializable;

public class Response implements Serializable {
    private boolean ok;
    private Object payload;
    private String message;

    public static Response ok(Object p){
        Response r = new Response();
        r.ok = true;
        r.payload = p;
        return r;
    }

    public static Response error(String m){
        Response r = new Response();
        r.ok = false;
        r.message = m;
        return r;
    }

    public boolean isOk(){ return ok; }
    public Object getPayload(){ return payload; }
    public String getMessage(){ return message; }
}
