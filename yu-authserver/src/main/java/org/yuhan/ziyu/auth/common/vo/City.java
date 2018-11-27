package org.yuhan.ziyu.auth.common.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Howell
 */
@Data
public class City implements Serializable {

    String country;

    String province;

    String city;
}
