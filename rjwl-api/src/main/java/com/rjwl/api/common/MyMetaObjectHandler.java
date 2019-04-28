package com.rjwl.api.common;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * 启用mybatis-plus 自动填充
 *
 * @author aniu
 */
public class MyMetaObjectHandler implements MetaObjectHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(MyMetaObjectHandler.class);

    @Override
    public void insertFill(MetaObject metaObject) {
        LOGGER.info("start insert fill ....");
        LocalDateTime createTime = LocalDateTime.now();
        this.setFieldValByName("gmt", createTime, metaObject);
        this.setFieldValByName("upt", createTime, metaObject);
        this.setFieldValByName("version", 1, metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        LOGGER.info("start update fill ....");
        LocalDateTime updateTime = LocalDateTime.now();
        this.setFieldValByName("upt", updateTime, metaObject);
    }
}
