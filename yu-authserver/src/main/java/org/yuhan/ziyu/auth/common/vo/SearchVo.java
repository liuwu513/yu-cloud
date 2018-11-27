package org.yuhan.ziyu.auth.common.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Howell
 */
@Data
public class SearchVo implements Serializable {

    private String startDate;

    private String endDate;
}
