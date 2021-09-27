package org.oagBlog.blogapp.entity.role;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.PackagePrivate;
import org.oagBlog.blogapp.entity.enums.role.RoleName;
import org.oagBlog.blogapp.entity.privilege.PrivilegeEntity;
import org.springframework.security.core.GrantedAuthority;


import javax.persistence.*;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@PackagePrivate
@Entity(name = "role")
public class RoleEntity implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    @Column(unique = true)
    @Enumerated(EnumType.STRING)
    RoleName name;

    @ManyToMany
    Set<PrivilegeEntity> privilegeEntities;

    @Override
    public String getAuthority() {
        return this.name.name();
    }
}
