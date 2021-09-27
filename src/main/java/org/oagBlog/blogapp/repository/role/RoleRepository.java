package org.oagBlog.blogapp.repository.role;

import org.oagBlog.blogapp.entity.enums.role.RoleName;
import org.oagBlog.blogapp.entity.role.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RoleRepository extends JpaRepository<RoleEntity, Integer> {

    RoleEntity findByName(RoleName name);
}
