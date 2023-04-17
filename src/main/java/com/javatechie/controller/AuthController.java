package com.javatechie.controller;

import com.javatechie.config.jwt.JwtService;
import com.javatechie.dto.AuthRequest;
import com.javatechie.dto.Product;
import com.javatechie.entity.UserInfo;
import com.javatechie.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private JwtService jwtService;
    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("")
    public String authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
        Authentication authentication =
                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(authRequest.username(), authRequest.password()));
        if (authentication.isAuthenticated()) {
            return jwtService.generateToken(authRequest.username());
        } else {
            throw new UsernameNotFoundException("invalid user request !");
        }


    }
}
