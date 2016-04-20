package com.example.design.service;

import com.example.design.mapper.UserMapper;
import com.example.design.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by lxh on 4/14/16.
 */
@Service
public class UserService {
    @Autowired
    UserMapper mapper;

    public int count(String name, String passwd) {
        return mapper.count(name, passwd);
    }

    public List<User> all() {
        return mapper.all();
    }

    public int addUser(User user) {
        return mapper.add(user);
    }

    public int updateInfo(User user) {
        return mapper.update(user);
    }

    public List<User> getByName(String name) {
        return mapper.selectByName(name);
    }

    public int removeById(int id) {
        return mapper.delete(id);
    }

    public int removeByPhone(String phone) {
        return mapper.deleteByPhone(phone);
    }

    public String[] getRoles(String name) {
        String role = mapper.getRole(name);
        if (null == role) {
            return null;
        }
        return role.split(";");
    }

    public boolean validateUser(String name, String password) {
        int count = mapper.count(name, password);
        return count == 1;
    }

    public boolean hasUser(String username) {
        List<User> list = mapper.selectByName(username);
        return !list.isEmpty();
    }
}
