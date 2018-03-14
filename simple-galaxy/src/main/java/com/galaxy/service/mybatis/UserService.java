package com.galaxy.service.mybatis;

import com.galaxy.entity.UserEntity;
import com.galaxy.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    @Transactional
    public List<UserEntity> getAll() {
        return this.userMapper.getAll();
    }

    @Transactional
    public UserEntity getOne(Long id) {
        return this.userMapper.getOne(id);
    }

    @Transactional
    public void insert(UserEntity user) {
        this.userMapper.insert(user);
    }

    @Transactional
    public void update(UserEntity user) {
        this.userMapper.update(user);
    }

    @Transactional
    public void delete(Long id) {
        this.userMapper.delete(id);
    }


}
