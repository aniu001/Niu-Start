package com.rjwl.api.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

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
@ApiModel(value="MerchantBill对象", description="")
public class MerchantBill extends BaseEntity {

    private static final long serialVersionUID = -1319009761447637753L;
    @ApiModelProperty(value = "商户标识")
    private String merchantTag;

    @ApiModelProperty(value = "业务标识")
    private String businessTag;

    @ApiModelProperty(value = "金额")
    private BigDecimal money;

    @ApiModelProperty(value = "扩展字段1")
    private String extend1;

    @ApiModelProperty(value = "扩展字段2")
    private String extend2;

    @ApiModelProperty(value = "扩展字段3")
    private String extend3;

    @ApiModelProperty(value = "扩展字段4")
    private String extend4;

    @ApiModelProperty(value = "扩展字段5")
    private String extend5;


}
