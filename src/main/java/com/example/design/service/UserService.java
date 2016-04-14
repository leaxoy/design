package com.example.design.service;

import com.example.design.mapper.UserMapper;
import com.example.design.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Created by lxh on 4/14/16.
 */
@Service
public class UserService {
    @Autowired
    Optional<UserMapper> mapper;

    public int count(String name, String passwd) {
        if (mapper.isPresent()) {
            return mapper.get().count(name, passwd);
        }
        return 0;
    }

    public List<User> all() {
        if (mapper.isPresent()) {
            return mapper.get().all();
        }
        return null;
    }

    public int addUser(User user) {
        if (mapper.isPresent()) {
            return mapper.get().add(user);
        }
        return -1;
    }

    public int updateInfo(User user) {
        if (mapper.isPresent()) {
            return mapper.get().update(user);
        }
        return -1;
    }

    public List<User> getByName(String name) {
        if (mapper.isPresent()) {
            return mapper.get().selectByName(name);
        }
        return null;
    }

    public int removeById(int id) {
        if (mapper.isPresent()) {
            return mapper.get().delete(id);
        }
        return -1;
    }

    public int removeByPhone(String phone) {
        if (mapper.isPresent()) {
            return mapper.get().deleteByPhone(phone);
        }
        return -1;
    }
}
