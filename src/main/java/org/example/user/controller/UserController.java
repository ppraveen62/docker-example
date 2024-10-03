package org.example.user.controller;

import org.example.common.AuthRequest;
import org.example.common.JwtUtill;
import org.example.user.entity.UserEntity;
import org.example.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private JwtUtill jwtUtill;
    @Autowired
    private UserService userService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    UserDetailsService userDetailsService;

    @GetMapping("/test")
    public String test(){
        return "Hello Spring Security";
    }
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest authRequest)throws Exception{
        try {
            Authentication authenticate = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(),
                            authRequest.getPassword()));
            UserDetails userDetails = userDetailsService.loadUserByUsername(authRequest.getUsername());
            String token = jwtUtill.generateToken(userDetails.getUsername());
            ResponseEntity<?> responseEntity = new ResponseEntity<>(token,HttpStatus.OK);
            return responseEntity;
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid Credentials");
        }
    }
    @PostMapping("/create")
    public ResponseEntity<UserEntity> create(@RequestBody UserEntity userEntity)throws Exception{
        UserEntity userEntity1 = userService.create(userEntity);
        ResponseEntity<UserEntity> responseEntity = new ResponseEntity<>(userEntity1, HttpStatus.OK);
        return responseEntity;
    }

    @GetMapping("/get")
    public ResponseEntity<List<UserEntity>> getAll()throws Exception{
        List<UserEntity> entityList = userService.getAll();
        ResponseEntity<List<UserEntity>> listResponseEntity = new ResponseEntity<>(entityList,HttpStatus.OK);
        return listResponseEntity;
    }

}
