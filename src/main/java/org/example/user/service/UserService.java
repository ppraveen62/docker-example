package org.example.user.service;

import org.example.user.entity.UserEntity;

import java.util.List;

public interface UserService {
    UserEntity create(UserEntity userEntity)throws Exception;

    List<UserEntity> getAll() throws Exception;

}
