package com.gamingfunserver.project.repository;

import com.gamingfunserver.project.model.user.Role;
import com.gamingfunserver.project.model.user.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
//@EnableJpaRepositories(basePackages="com.gamingfunserver.project.repository")
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(RoleName roleName);
}
