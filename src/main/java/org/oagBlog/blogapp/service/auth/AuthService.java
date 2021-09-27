package org.oagBlog.blogapp.service.auth;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.oagBlog.blogapp.entity.user.UserEntity;
import org.oagBlog.blogapp.model.dto.login.LoginDto;
import org.oagBlog.blogapp.model.dto.register.RegisterDto;
import org.oagBlog.blogapp.model.response.ApiResponse;

public interface AuthService extends UserDetailsService {

    ApiResponse registerUser(RegisterDto registerDto);

    ApiResponse loginUser(LoginDto loginDto);

    default UserEntity createUserEntity(RegisterDto registerDto) {
        UserEntity userEntity = new UserEntity();
        userEntity.setFirstName(registerDto.getFirstName());
        userEntity.setLastName(registerDto.getLastName());
        userEntity.setUsername(registerDto.getEmail());
        userEntity.setPassword(registerDto.getPassword());
        return userEntity;
    }

    boolean sendEmail(String sendingEmail, String emailCode);

    ApiResponse verifyEmail(String emailCode, String email);


}
