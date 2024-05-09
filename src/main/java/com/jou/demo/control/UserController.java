package com.jou.demo.control;

import com.jou.demo.DAO.UserDAO;
import com.jou.demo.DO.UserDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserDAO userDAO;

    @PostMapping
    public ResponseEntity<Void> createUser(@RequestBody UserDO userDO) {
        userDAO.insert(userDO);
        return ResponseEntity.ok().build();
    }

}