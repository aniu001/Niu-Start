package com.rjwl.api.service;

import com.rjwl.api.entity.Admin;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author aniu
 * @since 2019-04-23
 */
public interface AdminService extends IService<Admin> {
    /**
     * 根据adminName 获取用户
     * @param phone
     * @return
     */
    Admin selectAdminByName(String phone);
}
