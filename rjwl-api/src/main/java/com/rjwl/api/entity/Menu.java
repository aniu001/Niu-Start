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
@ApiModel(value = "Menu对象", description = "")
public class Menu extends BaseEntity {

    private static final long serialVersionUID = -8098386733461177403L;
    @ApiModelProperty(value = "当前菜单id")
    private Integer zid;

    @ApiModelProperty(value = "父菜单id 一级菜单默认0")
    private Integer pid;

    @ApiModelProperty(value = "1代表超级管理员才能打开的页面，")
    private Integer superState;

    @ApiModelProperty(value = "是否展开状态0代表关闭，1代表展开")
    private Integer state;

    private String menuCode;

    @ApiModelProperty(value = "value显示值")
    private String text;

    @ApiModelProperty(value = "菜单对应地址")
    private String url;

    @ApiModelProperty(value = "图标")
    @TableField("inconCls")
    private String inconCls;


}
