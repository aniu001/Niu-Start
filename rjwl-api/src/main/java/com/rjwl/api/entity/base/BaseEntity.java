package com.rjwl.api.entity.base;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 实体类通用字段
 *
 * @author aniu
 */
@Data
public class BaseEntity implements Serializable {

    private static final long serialVersionUID = -3927350507182183013L;
    @JsonIgnore
    @TableId(type = IdType.AUTO)
    protected Integer id;

    @JsonIgnore
    @TableField(fill = FieldFill.INSERT)
    protected LocalDateTime gmt;

    @JsonIgnore
    @TableField(fill = FieldFill.INSERT_UPDATE)
    protected LocalDateTime upt;

    @JsonIgnore
    @TableField(fill = FieldFill.INSERT)
    protected Integer version;

}
