package com.appdveloperblog.app.ws.service.impl;

import com.appdveloperblog.app.ws.UserRepository;
import com.appdveloperblog.app.ws.io.entity.UserEntity;
import com.appdveloperblog.app.ws.service.UserService;
import com.appdveloperblog.app.ws.shared.dto.UserDto;
import com.appdveloperblog.app.ws.shared.utils.Utils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    Utils utils;

    @Override
    public UserDto createUser(UserDto userDto) {
        if (null != userRepository.findByEmail(userDto.getEmail())) {
            throw new RuntimeException("Email already exists");
        }
        UserEntity userEntity = new UserEntity();
        BeanUtils.copyProperties(userDto, userEntity);

        userEntity.setEncryptedPassword("test");
        String userId = utils.generateUserId(30);
        userEntity.setUserId(userId);

        UserEntity storedUserDetails = userRepository.save(userEntity);
        UserDto returnValue = new UserDto();

        BeanUtils.copyProperties(storedUserDetails, returnValue);
        return returnValue;
    }
}
