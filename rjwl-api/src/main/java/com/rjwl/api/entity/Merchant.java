package com.rjwl.api.entity;

import com.rjwl.api.entity.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

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
@ApiModel(value = "Merchant对象", description = "")
public class Merchant extends BaseEntity {

    private static final long serialVersionUID = -4288172732803798048L;
    @ApiModelProperty(value = "商户标识")
    private String merchantTag;

    @ApiModelProperty(value = "app标识")
    private String appTag;

    @ApiModelProperty(value = "商户余额")
    private BigDecimal merchantBalance;

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
