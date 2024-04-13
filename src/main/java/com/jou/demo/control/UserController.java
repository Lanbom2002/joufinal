package com.jou.demo.control;

import com.jou.demo.DAO.UserDAO;
import com.jou.demo.DO.UserDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class UserController {
    @Autowired
    private UserDAO userDAO;

    @GetMapping("/user")
    @ResponseBody
    public List<UserDO> getAll(){
        return userDAO.findAll();
    }
}
