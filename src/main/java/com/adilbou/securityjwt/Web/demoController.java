package com.adilbou.securityjwt.Web;

import com.adilbou.securityjwt.Entities.Member;
import com.adilbou.securityjwt.Entities.User;
import com.adilbou.securityjwt.Service.UserSevice;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/demo-Controller")
@CrossOrigin("*")
public class demoController {

    private UserSevice userSevice;

    public demoController(UserSevice userSevice) {
        this.userSevice = userSevice;
    }

    @GetMapping
    public ResponseEntity<String> sayHello(){
        return ResponseEntity.ok("Hello From Secured enpoint");
    }

    @GetMapping("/members")
    public List<Member> getAllMemebers(){
        return userSevice.getAllMemebers();
    }

}
