package org.yuhan.ziyu.auth.exception;

import lombok.Data;

/**
 * @author Howell
 */
@Data
public class OSSException extends RuntimeException {

    private String msg;

    public OSSException(String msg){
        super(msg);
        this.msg = msg;
    }
}
