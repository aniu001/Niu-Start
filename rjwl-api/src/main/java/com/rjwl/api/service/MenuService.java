package com.rjwl.api.service;

import com.rjwl.api.entity.Menu;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author aniu
 * @since 2019-04-23
 */
public interface MenuService extends IService<Menu> {
    /**
     * 根据角色ID 获取 所有菜单
     * @param roleId
     * @return
     */
    List<Menu> selectMenusByRoleId(Integer roleId);
}
