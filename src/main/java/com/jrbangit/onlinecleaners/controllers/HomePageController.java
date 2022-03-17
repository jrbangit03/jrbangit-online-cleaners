package com.jrbangit.onlinecleaners.controllers;

import com.jrbangit.onlinecleaners.models.AuthenticationRequest;
import com.jrbangit.onlinecleaners.models.AuthenticationResponse;
import com.jrbangit.onlinecleaners.services.LoginService;
import com.jrbangit.onlinecleaners.utilities.JWTUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomePageController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private LoginService userDetailService;

    @RequestMapping(path = "/home", method = RequestMethod.GET)
    public String home(){
        return "HomePage";
    }

    @RequestMapping(path = "/login")
    public ResponseEntity<?> authenticateLogin(@RequestBody AuthenticationRequest authenticationRequest){
        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUserId(), authenticationRequest.getPassword()));
        } catch (BadCredentialsException exp){
            throw new BadCredentialsException(exp.getMessage() + "Bad Credentials");
        }

        UserDetails userDetails = userDetailService.loadUserByUsername(authenticationRequest.getUserId());
        String token = JWTUtility.createToken(userDetails);
        AuthenticationResponse authenticationResponse = new AuthenticationResponse();
        authenticationResponse.setJwt(token);

        return ResponseEntity.ok(authenticationResponse);
    }
}
