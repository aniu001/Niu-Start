package com.rjwl.api.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.rjwl.api.entity.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 *
 * </p>
 *
 * @author aniu
 * @since 2019-04-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "Admin对象", description = "")
public class Admin extends BaseEntity {


    private static final long serialVersionUID = 3947410306657519255L;
    @ApiModelProperty(value = "登录用户名")
    private String adminName;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "角色表id")
    private Integer roleId;

    @ApiModelProperty(value = "后台用户禁用状态，1代表禁用，默认为0，只用管理员admin有权限禁用")
    private Integer adminState;

    @ApiModelProperty(value = "0 不分配 1 分配")
    private Integer distribution;

    @ApiModelProperty(value = "1代表是超级管理员，权限最大")
    private Integer superState;

    @ApiModelProperty(value = "验证码")
    private String vcode;

    @ApiModelProperty(value = "用户昵称")
    @TableField("nickName")
    private String nickName;


}
