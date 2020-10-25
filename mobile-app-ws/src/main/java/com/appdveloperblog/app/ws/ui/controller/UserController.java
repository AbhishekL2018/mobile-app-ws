package com.appdveloperblog.app.ws.ui.controller;

import com.appdveloperblog.app.ws.service.UserService;
import com.appdveloperblog.app.ws.shared.dto.UserDto;
import com.appdveloperblog.app.ws.ui.model.request.UserDetailsRequestModel;
import com.appdveloperblog.app.ws.ui.model.response.UserRest;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")  //http://localhost:9865/users
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public String getUser(){
        return "get User was called";
    }

    @PostMapping
    public UserRest createUser(@RequestBody UserDetailsRequestModel userDetails){
        UserRest returnValue = new UserRest();
        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(userDetails,userDto);
        UserDto createdUser = userService.createUser(userDto);
        BeanUtils.copyProperties(createdUser,returnValue);
        return returnValue;
    }

    @PutMapping
    public String updateUser(){
        return "update User was called";
    }

    @DeleteMapping
    public String deleteUser(){
        return "delete User was called";
    }

}
