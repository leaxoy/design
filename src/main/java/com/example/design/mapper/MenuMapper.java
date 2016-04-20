package com.example.design.mapper;

import com.example.design.model.resource.Menu;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * 菜单持久化接口.
 * Created by lxh on 4/17/16.
 */
@Repository
public interface MenuMapper {

    /**
     * 获取所有菜单 or null.
     *
     * @return 所有菜单
     */
    @Select("")
    Optional<List<Menu>> all();

    /**
     * 获取ID为id的菜单 or null.
     *
     * @param id 需要获取的菜单的id
     * @return 菜单 or null.
     */
    @Select("")
    Optional<Menu> id(int id);

    /**
     * @param menu 需要更新的菜单.
     */
    @Update("")
    void update(Menu menu);

}
