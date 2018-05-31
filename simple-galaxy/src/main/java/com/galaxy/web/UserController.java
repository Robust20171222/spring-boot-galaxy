package com.galaxy.web;

import com.galaxy.entity.UserEntity;
import com.galaxy.service.mybatis.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    private static final Logger LOGGER = LogManager.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @RequestMapping("/getUsers")
    @ResponseBody
    public List<UserEntity> getUsers() {
        LOGGER.info(System.currentTimeMillis() + "---getUsers");
        List<UserEntity> users = this.userService.getAll();
        return users;
    }

    @RequestMapping("/getUser")
    @ResponseBody
    public UserEntity getUser(Long id) {
        UserEntity user = this.userService.getOne(id);
        return user;
    }

    @RequestMapping("/add")
    public void save(UserEntity user) {
        this.userService.insert(user);
    }

    @RequestMapping(value = "update")
    public void update(UserEntity user) {
        this.userService.update(user);
    }

    @RequestMapping(value = "/delete/{id}")
    public void delete(@PathVariable("id") Long id) {
        this.userService.delete(id);
    }

}