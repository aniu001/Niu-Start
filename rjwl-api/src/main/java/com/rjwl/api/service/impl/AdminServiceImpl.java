package com.rjwl.api.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rjwl.api.entity.Admin;
import com.rjwl.api.mapper.AdminMapper;
import com.rjwl.api.service.AdminService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author aniu
 * @since 2019-04-23
 */
@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements AdminService {

    @Override
    public Admin selectAdminByName(String phone) {
        QueryWrapper<Admin> queryWrapper = new QueryWrapper<Admin>();
        queryWrapper.eq("admin_name", phone);
        queryWrapper.eq("admin_state", 0);
        return getOne(queryWrapper);
    }
}
