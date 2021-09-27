package org.oagBlog.blogapp.repository.user;

import org.oagBlog.blogapp.entity.user.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<UserEntity, UUID> {

    boolean existsByUsername(String username);

    Optional<UserEntity> findByUsernameAndEmailCode(String username, String emailCode);

    Optional<UserEntity> findByUsername(String username);
}
