package org.example.user.service;

import org.example.user.entity.UserEntity;
import org.example.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Service(value = "userDetailService")
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserEntity> byUserName = userRepository.findByEmailId(username);
        UserEntity userEntity = byUserName.orElseThrow(() -> new UsernameNotFoundException("user name not exit"));
        UserDetails userDetails = User.builder()
                .username(userEntity.getEmailId())
                .password(userEntity.getPassword())
                .authorities(Collections.emptyList()).build();
        return userDetails;
    }
}
