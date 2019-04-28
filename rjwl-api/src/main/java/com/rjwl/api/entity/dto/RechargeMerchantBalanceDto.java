package com.rjwl.api.entity.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

/**
 * @ClassName RechargeMerchantBalanceDto
 * @Description TODO
 * @Author aniu
 * @Date 2019/4/28 11:22
 * @Version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "RechargeMerchantBalanceDto对象", description = "商户余额充值实体")
public class RechargeMerchantBalanceDto {

    @ApiModelProperty(value = "主键ID")
    private Integer id;
    @ApiModelProperty(value = "金额")
    private BigDecimal money;
}
