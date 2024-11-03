package com.salasync.projectfinal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.salasync.projectfinal.entity.AuthenticationDTO;
import com.salasync.projectfinal.entity.RegisterDTO;
import com.salasync.projectfinal.entity.User;
import com.salasync.projectfinal.repositorys.UserRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("auth")

public class AuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserRepository userRepository;
    
        
    @PostMapping("/login")
    
    public ResponseEntity login(@RequestBody @Valid AuthenticationDTO data){
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.email(), data.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);

    return ResponseEntity.ok().build();
}

@PostMapping("/register") 
public ResponseEntity register(@RequestBody @Valid RegisterDTO data){
    if(this.userRepository.findByLogin(data.email()) != null) return ResponseEntity.badRequest().build();

    String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
    User newUser = new User(data.email(), encryptedPassword, data.role());

    this.userRepository.save(newUser);
}

}
