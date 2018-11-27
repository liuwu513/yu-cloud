package org.yuhan.ziyu.auth.common.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Howell
 */
@Data
public class IpLocate implements Serializable {

    private String retCode;

    private City result;
}

