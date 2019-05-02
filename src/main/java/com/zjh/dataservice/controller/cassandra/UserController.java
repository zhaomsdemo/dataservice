package com.zjh.dataservice.controller.cassandra;

import com.zjh.dataservice.entity.cassandra.User;
import com.zjh.dataservice.service.cassandra.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public ResponseEntity<User> postUser(@RequestBody User user){
        User response = userService.saveUser(user);
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }
}
