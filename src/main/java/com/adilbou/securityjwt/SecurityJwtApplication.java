package com.adilbou.securityjwt;

import com.adilbou.securityjwt.Entities.Role;
import com.adilbou.securityjwt.Security.RegisterRequest;
import com.adilbou.securityjwt.Repositories.RoleRepository;
import com.adilbou.securityjwt.Repositories.UserRepository;
import com.adilbou.securityjwt.Security.Services_Security.AuthenticationServiceImpl;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class SecurityJwtApplication {

    public static void main(String[] args) {
        SpringApplication.run(SecurityJwtApplication.class, args);
    }
    @Bean
    CommandLineRunner start(RoleRepository roleRepository,
                            UserRepository userRepository,
                            AuthenticationServiceImpl authService) {

        return args -> {

            Role roleUser = new Role();
            Role roleAdmin = new Role();
            Role roleFournisseur = new Role();
            Role roleChefDepart = new Role();
            Role roleEnse = new Role();
            Role roleTech = new Role();




            roleAdmin.setRolename("RESP");
            roleUser.setRolename("USER");
            roleFournisseur.setRolename("FOUR");
            roleChefDepart.setRolename("CHDP");
            roleEnse.setRolename("ENSE");
            roleTech.setRolename("TECH");

            roleRepository.save(roleFournisseur);
            roleRepository.save(roleAdmin);
            roleRepository.save(roleUser);
            roleRepository.save(roleChefDepart);
            roleRepository.save(roleEnse);
            roleRepository.save(roleTech);


//            Responsable admin = new Responsable();
//            admin.setEmail("admin@gmail.com");
//            admin.setPassword("1234");
//            admin.setFirstname("admin");
//            admin.setLastname("admin");
//            admin.setEntreprise("SOKA");
//            List<Role> roles = new ArrayList<>();
//            roles.add(roleAdmin);
//            admin.setRoles(roles);

            List<Role> roles = new ArrayList<>();
            roles.add(roleAdmin);

            RegisterRequest admin = new RegisterRequest();
            admin.setFirstname("admin");
            admin.setLastname("admin");
            admin.setEmail("admin@gmail.com");
            admin.setPassword("1234");
            admin.setSociety(null);
            admin.setEntreprise("FST-FES");
            admin.setRoles(roles);
            authService.register(admin);



            for(int i=0;i<5;i++){
                List<Role> roles_four = new ArrayList<>();
                roles_four.add(roleFournisseur);
                RegisterRequest fournisseur = new RegisterRequest();
                fournisseur.setFirstname("four"+i);
                fournisseur.setLastname("last four"+i);
                fournisseur.setEmail("four"+i+"@gmail.com");
                fournisseur.setPassword("1234");
                fournisseur.setSociety("SOKA");
                fournisseur.setRoles(roles_four);
                authService.register(fournisseur);

            }


            RegisterRequest technicien = new RegisterRequest();
            List<Role> roles_tech = new ArrayList<>();
            roles_tech.add(roleTech);
            technicien.setFirstname("tech");
            technicien.setLastname("thbou");
            technicien.setEmail("tech@gmail.com");
            technicien.setPassword("1234");
            technicien.setSpeciality("Materiel Side");
            technicien.setRoles(roles_tech);
            authService.register(technicien);






        };
    }
}
