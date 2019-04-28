package com.rjwl.api.entity.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author aniu
 */
@Data
@ApiModel
public class LoginDto {

    @ApiModelProperty("用户名")
    private String adminName;
    @ApiModelProperty("密码")
    private String password;


}
