package net.moltak.soap.exception;

import net.moltak.soap.model.BaseRequest;

/**
 * Created by moltak on 15. 2. 13..
 */
public class SoapException extends Exception {
    
    private BaseRequest baseRequest;
    
    public SoapException(String message) {
        super(message);
    }

    public BaseRequest getBaseRequest() {
        return baseRequest;
    }

    public void setBaseRequest(BaseRequest baseRequest) {
        this.baseRequest = baseRequest;
    }
}
