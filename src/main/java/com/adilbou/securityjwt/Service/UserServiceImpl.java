package com.adilbou.securityjwt.Service;

import com.adilbou.securityjwt.Entities.Member;
import com.adilbou.securityjwt.Repositories.MemberRepository;
import com.adilbou.securityjwt.Repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserSevice{
    private UserRepository userRepository;
    private MemberRepository memberRepository;


    public UserServiceImpl(UserRepository userRepository, MemberRepository memberRepository) {
        this.userRepository = userRepository;
        this.memberRepository = memberRepository;

    }


    @Override
    public List<Member> getAllMemebers() {

        List<Member> memeberDepart = memberRepository.findAll();

        return memeberDepart;

    }
}
