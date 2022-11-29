package com.demo.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
public class RetrievePwdDto implements Serializable {
    private String account;
    private String userPhone;
    private String newPwd;
    private String verCode;
}
