package com.jou.demo.service;

import com.jou.demo.DAO.UserDAO;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    boolean validateUser(String userID, String password);
}
