package com.adilbou.securityjwt.Service;

import com.adilbou.securityjwt.Entities.Member;
import com.adilbou.securityjwt.Entities.Technicien;
import com.adilbou.securityjwt.Entities.User;

import java.util.List;

public interface UserSevice {
    List<Member> getAllMemebers();
    List<Technicien> getAllTech();
}
