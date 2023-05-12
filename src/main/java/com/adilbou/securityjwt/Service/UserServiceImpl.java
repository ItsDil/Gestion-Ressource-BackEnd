package com.adilbou.securityjwt.Service;

import com.adilbou.securityjwt.Entities.Member;
import com.adilbou.securityjwt.Entities.Technicien;
import com.adilbou.securityjwt.Repositories.MemberRepository;
import com.adilbou.securityjwt.Repositories.TechnicienRepository;
import com.adilbou.securityjwt.Repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserSevice{
    private UserRepository userRepository;
    private MemberRepository memberRepository;
    private  TechnicienRepository technicienRepository;


    public UserServiceImpl(UserRepository userRepository, MemberRepository memberRepository,TechnicienRepository technicienRepository) {
        this.userRepository = userRepository;
        this.memberRepository = memberRepository;
        this.technicienRepository=technicienRepository;

    }


    @Override
    public List<Member> getAllMemebers() {

        List<Member> memeberDepart = memberRepository.findAll();

        return memeberDepart;

    }
    @Override
    public List<Technicien> getAllTech() {
        List<Technicien> technicien = technicienRepository.findAll();
        return technicien;

    }
}
