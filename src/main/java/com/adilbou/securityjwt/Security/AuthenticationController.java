package com.adilbou.securityjwt.Security;

import com.adilbou.securityjwt.Entities.Member;
import com.adilbou.securityjwt.Entities.User;
import com.adilbou.securityjwt.Security.Services_Security.AuthenticationServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@CrossOrigin("*")

public class AuthenticationController {

    private final AuthenticationServiceImpl service;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request){

            return ResponseEntity.ok(service.register(request));

    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request){
        return ResponseEntity.ok(service.authenticate(request));
    }


    @PostMapping("/refresh-token")
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {

        service.refreshToken(request, response);

    }

    @PutMapping("/edit-memeber/{id}")
    public Member edit(@PathVariable Integer id,@RequestBody Member member ){

        member.setId(id);
        return service.updateUser(member);

    }

    @DeleteMapping("/delete-memeber/{id}")
    public void Delete(@PathVariable Integer id){

         service.deleteUser(id);
    }




}
