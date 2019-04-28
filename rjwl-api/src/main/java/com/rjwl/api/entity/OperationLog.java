package com.rjwl.api.entity;

import com.rjwl.api.entity.base.BaseEntity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
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
 * @since 2019-04-25
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(value="OperationLog对象", description="")
public class OperationLog extends BaseEntity {

    private static final long serialVersionUID = 7724756894281040083L;

    @ApiModelProperty(value = "adminId")
    private Integer adminId;

    @ApiModelProperty(value = "用户名")
    private String adminName;

    @ApiModelProperty(value = "操作记录")
    private String operationRecord;

    @ApiModelProperty(value = "登陆IP")
    private String loginIp;


}
