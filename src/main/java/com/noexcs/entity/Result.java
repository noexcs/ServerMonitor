package com.noexcs.entity;

import lombok.Data;

/**
 * @author noexcs
 */
@Data
public class Result {
    private Integer code;
    private String msg;
    private Object data;
}
