package com.rjwl.api.service.impl;

import com.rjwl.api.entity.Menu;
import com.rjwl.api.mapper.MenuMapper;
import com.rjwl.api.service.MenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author aniu
 * @since 2019-04-23
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {
    @Resource
    private MenuMapper menuMapper;

    @Override
    public List<Menu> selectMenusByRoleId(Integer roleId) {
        return menuMapper.selectMenusByRoleId(roleId);

    }
}
