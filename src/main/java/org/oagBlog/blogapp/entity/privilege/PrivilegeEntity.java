package org.oagBlog.blogapp.entity.privilege;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.PackagePrivate;
import org.oagBlog.blogapp.entity.enums.privilege.PrivilegeName;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Entity(name = "privilege")
@AllArgsConstructor
@NoArgsConstructor
@PackagePrivate
@Data
public class PrivilegeEntity implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    @Column(unique = true)
    @Enumerated(EnumType.STRING)
    PrivilegeName name;

    @Override
    public String getAuthority() {
        return name.name();
    }
}
