package org.example.user.service;

import org.example.user.entity.UserEntity;
import org.example.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public UserEntity create(UserEntity userEntity)throws Exception{
        Optional<UserEntity> byUserName = userRepository.findByEmailId(userEntity.getEmailId());
        if (byUserName.isPresent()){
            throw new Exception("user emailId Exist");
        }
        userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
        UserEntity user = userRepository.save(userEntity);
        return user;
    }

    @Override
    public List<UserEntity> getAll() throws Exception {
        List<UserEntity> entityList = userRepository.findAll();
        return entityList;
    }

}
