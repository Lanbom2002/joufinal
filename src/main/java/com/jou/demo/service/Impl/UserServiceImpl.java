package com.jou.demo.service.Impl;

import com.jou.demo.DAO.UserDAO;
import com.jou.demo.DO.UserDO;
import com.jou.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDAO userDAO; // 通过 @Autowired 注解来自动注入 UserDAO 的实现

    @Override
    public boolean validateUser(String userID, String password) {
        UserDO userDO = userDAO.findUserByID(userID);
        return userDO != null && userDO.getPassword().equals(password);
    }
}
