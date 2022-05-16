package com.noexcs.entity;

import lombok.Data;

import java.util.Date;
import java.io.Serializable;


/**
 * @author noexcs
 */
@Data
public class Status implements Serializable {
    private static final long serialVersionUID = 464169939781316236L;
    private Integer id;
    private Float cpuUsage;
    private Float memoryUsage;

    private Float diskUsage;

    private String serverIp;

    private Date createTime;



}

