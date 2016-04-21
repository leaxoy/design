package com.example.design.service.impl;

import com.example.design.mapper.MenuMapper;
import com.example.design.model.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * 菜单的服务接口
 * Created by lxh on 4/17/16.
 */
@Service
public class MenuService {
    @Autowired
    Optional<MenuMapper> mapper;

    public Optional<List<Menu>> all() {
        Optional<List<Menu>> menus;
        if (mapper.isPresent()) {
            menus = mapper.get().all();
        } else {
            menus = null;
        }
        if (menus.isPresent()) {
            return menus;
        }
        return null;
    }
}
