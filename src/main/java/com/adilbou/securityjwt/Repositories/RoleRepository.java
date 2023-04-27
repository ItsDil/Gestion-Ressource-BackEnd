package com.adilbou.securityjwt.Repositories;

import com.adilbou.securityjwt.Entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Integer> {

    Role findByRolename(String rolename);
}
