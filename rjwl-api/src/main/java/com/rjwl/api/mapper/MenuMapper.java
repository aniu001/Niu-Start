package com.rjwl.api.mapper;

import com.rjwl.api.entity.Menu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author aniu
 * @since 2019-04-23
 */
public interface MenuMapper extends BaseMapper<Menu> {
    /**
     * 根据角色ID 获取 所有菜单
     * @param roleId
     * @return
     */
    List<Menu> selectMenusByRoleId(Integer roleId);
}
