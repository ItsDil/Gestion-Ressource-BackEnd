package com.adilbou.securityjwt.Security.Services_Security;

import com.adilbou.securityjwt.Entities.Member;
import com.adilbou.securityjwt.Entities.Role;
import com.adilbou.securityjwt.Entities.User;
import com.adilbou.securityjwt.Security.AuthenticationRequest;
import com.adilbou.securityjwt.Security.AuthenticationResponse;
import com.adilbou.securityjwt.Security.RegisterRequest;

public interface AuthenticationService {

     AuthenticationResponse register(RegisterRequest request);
     AuthenticationResponse authenticate(AuthenticationRequest request);
     boolean hasRole(String email, String nomRole);
     Role getRole(String roleName);

      Member updateUser(Member user);

      void deleteUser(Integer id);
      User getUserByUserName(String userName);

      User getUserById(Integer id);

}
