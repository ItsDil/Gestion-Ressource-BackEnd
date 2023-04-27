package com.adilbou.securityjwt.Security.Services_Security;

import com.adilbou.securityjwt.Entities.*;
import com.adilbou.securityjwt.Exception.EntityExistException;
import com.adilbou.securityjwt.Exception.NotFoundException;
import com.adilbou.securityjwt.Security.ConfigSecur.JwtService;
import com.adilbou.securityjwt.Repositories.RoleRepository;
import com.adilbou.securityjwt.Repositories.UserRepository;
import com.adilbou.securityjwt.Security.AuthenticationRequest;
import com.adilbou.securityjwt.Security.AuthenticationResponse;
import com.adilbou.securityjwt.Security.RegisterRequest;
import com.adilbou.securityjwt.Security.token.Token;
import com.adilbou.securityjwt.Security.token.TokenRepository;
import com.adilbou.securityjwt.Security.token.TokenType;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.*;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final TokenRepository tokenRepository;
    private final AuthenticationManager authenticationManager;



    public AuthenticationResponse register(RegisterRequest request) {

        User existFour = userRepository.findByEmail(request.getEmail()).orElse(null);
        if(existFour!=null){
            throw new EntityExistException("Email already used, Try again !!");
        }

        List<Role> roles = request.getRoles();
        List<Role> savedRoles = new ArrayList<>();
        Role role = roleRepository.findByRolename(roles.get(0).getRolename());
        if(role==null) {
            throw new NotFoundException("Role '" + role + "' not exist, please check again !!");
        }
        savedRoles.add(role);


//        for (Role role : roles) {
//            Role savedRole = roleRepository.findByRolename(role.getRolename());
//        }


        if (role.getRolename().equals("FOUR")){
            var user = Fournisseur.builder()
                    .firstname(request.getFirstname())
                    .lastname(request.getLastname())
                    .email(request.getEmail())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .roles(savedRoles)
                    .society(request.getSociety())
                    .build();
            var savedUser= userRepository.save(user);
            Map<String, Object> extraClaims = new HashMap<>();
            extraClaims.put("firstName", user.getFirstname());
            extraClaims.put("lastName", user.getLastname());
            extraClaims.put("roles", roles);

            var jwtToken = jwtService.generateToken(extraClaims,user);
            var refreshToken = jwtService.generateRefreshToken(extraClaims,user);
            saveUserToken(savedUser,jwtToken);
            return AuthenticationResponse.builder()
                    .accessToken(jwtToken)
                    .refreshToken(refreshToken)
                    .build();

        }else if(role.getRolename().equals("RESP")){


            var responsable = Responsable.builder()
                    .firstname(request.getFirstname())
                    .lastname(request.getLastname())
                    .email(request.getEmail())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .roles(savedRoles)
                    .Entreprise(request.getEntreprise())
                    .build();
            var savedUser= userRepository.save(responsable);
            Map<String, Object> extraClaims = new HashMap<>();
            extraClaims.put("id", responsable.getId());
            extraClaims.put("firstName", responsable.getFirstname());
            extraClaims.put("lastName", responsable.getLastname());
            extraClaims.put("roles", roles);

            var jwtToken = jwtService.generateToken(extraClaims,responsable);
            var refreshToken = jwtService.generateRefreshToken(extraClaims,responsable);
            saveUserToken(savedUser,jwtToken);
            return AuthenticationResponse.builder()
                    .accessToken(jwtToken)
                    .refreshToken(refreshToken)
                    .build();
        }else if(role.getRolename().equals("CHDP")){

            var chefDepartement = Member.builder()
                    .firstname(request.getFirstname())
                    .lastname(request.getLastname())
                    .email(request.getEmail())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .roles(savedRoles)
                    .departement(request.getDepartement())
                    .build();
            var savedUser= userRepository.save(chefDepartement);
            Map<String, Object> extraClaims = new HashMap<>();
            extraClaims.put("id", chefDepartement.getId());
            extraClaims.put("firstName", chefDepartement.getFirstname());
            extraClaims.put("lastName", chefDepartement.getLastname());
            extraClaims.put("roles", roles);

            var jwtToken = jwtService.generateToken(extraClaims,chefDepartement);
            var refreshToken = jwtService.generateRefreshToken(extraClaims,chefDepartement);
            saveUserToken(savedUser,jwtToken);
            return AuthenticationResponse.builder()
                    .accessToken(jwtToken)
                    .refreshToken(refreshToken)
                    .build();
        }else if(role.getRolename().equals("ENSE")){


            var enseignant = Member.builder()
                    .firstname(request.getFirstname())
                    .lastname(request.getLastname())
                    .email(request.getEmail())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .roles(savedRoles)
                    .departement(request.getDepartement())
                    .build();
            var savedUser= userRepository.save(enseignant);
            Map<String, Object> extraClaims = new HashMap<>();
            extraClaims.put("id", enseignant.getId());
            extraClaims.put("firstName", enseignant.getFirstname());
            extraClaims.put("lastName", enseignant.getLastname());
            extraClaims.put("roles", roles);

            var jwtToken = jwtService.generateToken(extraClaims,enseignant);
            var refreshToken = jwtService.generateRefreshToken(extraClaims,enseignant);
            saveUserToken(savedUser,jwtToken);
            return AuthenticationResponse.builder()
                    .accessToken(jwtToken)
                    .refreshToken(refreshToken)
                    .build();
        }else if(role.getRolename().equals("TECH")){


            var technicien = Technicien.builder()
                    .firstname(request.getFirstname())
                    .lastname(request.getLastname())
                    .email(request.getEmail())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .roles(savedRoles)
                    .speciality(request.getSpeciality())
                    .build();
            var savedUser= userRepository.save(technicien);
            Map<String, Object> extraClaims = new HashMap<>();
            extraClaims.put("id", technicien.getId());
            extraClaims.put("firstName", technicien.getFirstname());
            extraClaims.put("lastName", technicien.getLastname());
            extraClaims.put("roles", roles);

            var jwtToken = jwtService.generateToken(extraClaims,technicien);
            var refreshToken = jwtService.generateRefreshToken(extraClaims,technicien);
            saveUserToken(savedUser,jwtToken);
            return AuthenticationResponse.builder()
                    .accessToken(jwtToken)
                    .refreshToken(refreshToken)
                    .build();
        }
        return null;

    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(()->new NotFoundException("User with Email '"+ request.getEmail()+"' Not Found"));



        Map<String, Object> extraClaims = new HashMap<>();
        extraClaims.put("id", user.getId());
        extraClaims.put("firstName", user.getFirstname());
        extraClaims.put("lastName", user.getLastname());
        extraClaims.put("roles", user.getRoles());


        var jwtToken = jwtService.generateToken(extraClaims,user);
        var refreshToken = jwtService.generateRefreshToken(extraClaims,user);
        revokeAllUserTokens(user);
        saveUserToken(user,jwtToken);
        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .refreshToken(refreshToken)
                .build();

    }


    private void saveUserToken(User user, String jwtToken) {
        var token = Token.builder()
                .user(user)
                .token(jwtToken)
                .tokenType(TokenType.BEARER)
                .expired(false)
                .revoked(false)
                .build();
        tokenRepository.save(token);
    }

    private void revokeAllUserTokens(User user) {
        var validUserTokens = tokenRepository.findAllValidTokenByUser(user.getId());
        if (validUserTokens.isEmpty())
            return;
        validUserTokens.forEach(token -> {
            token.setExpired(true);
            token.setRevoked(true);
        });
        tokenRepository.saveAll(validUserTokens);
    }

    public void refreshToken(HttpServletRequest request,
                             HttpServletResponse response
    ) throws IOException {
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        final String refreshToken;
        final String userEmail;
        if(authHeader == null || !authHeader.startsWith("Bearer ")){
            return;
        }
        refreshToken = authHeader.substring(7);
        userEmail = jwtService.extractUsername(refreshToken);    // todo extract the userEmail from JWT TOKEN, we need class that can manipulate this JWT TOKEN;
        if(userEmail !=null ){
            //the user is not authenticated yet  !!!
            var  user = this.userRepository.findByEmail(userEmail).orElseThrow();

            if(jwtService.isTokenValid(refreshToken,user)){
                var accessToken = jwtService.generateToken(user);
                revokeAllUserTokens(user);
                saveUserToken(user,accessToken);
                var authResponse = AuthenticationResponse.builder()

                        .accessToken(accessToken)
                        .refreshToken(refreshToken)
                        .build();
                new ObjectMapper().writeValue(response.getOutputStream(), authResponse);
            }
        }

    }



//    ****************************************************************************

    public boolean hasRole(String email, String nomRole) {
        User user = getUserByUserName(email);

            Collection<Role> roles = user.getRoles();
            return roles.stream()
                    .map(Role::getRolename)
                    .anyMatch(nomRole::equals);

    }


    @Override
    public Role getRole(String roleName) {
        Role role = roleRepository.findByRolename(roleName);
        if (role == null)
            throw new NotFoundException("Role: " + roleName + " not found");
        return role;
    }

    @Override
    public Member updateUser(Member user) {

        String passwordEnco = passwordEncoder.encode(user.getPassword());
        user.setPassword(passwordEnco);
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(Integer id) {
        userRepository.deleteById(id);
    }

    @Override
    public User getUserByUserName(String email) {
        User user = userRepository.findByEmail(email).orElse(null);
        if (user == null)
            throw new NotFoundException("User with email "+email+" not found, please check !! ");
        return user;
    }

    @Override
    public User getUserById(Integer id) {
        User user = userRepository.findById(id).orElse(null);
        if (user == null)
            throw new NotFoundException("User not found, please check !! ");
        return user;
    }

}
