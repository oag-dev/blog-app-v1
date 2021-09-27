package org.oagBlog.blogapp.service.auth;

import org.oagBlog.blogapp.model.dto.register.RegisterDto;
import org.oagBlog.blogapp.model.response.ApiResponse;
import org.oagBlog.blogapp.repository.role.RoleRepository;
import org.oagBlog.blogapp.repository.user.UserRepository;
import org.oagBlog.blogapp.security.jwt.provider.JwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.oagBlog.blogapp.entity.enums.role.RoleName;
import org.oagBlog.blogapp.entity.user.UserEntity;
import org.oagBlog.blogapp.model.dto.login.LoginDto;

import java.util.Collections;
import java.util.Optional;
import java.util.UUID;

@Service
@PropertySource("application.properties")
public class AuthServiceImpl implements AuthService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    JavaMailSender javaMailSender;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JwtProvider jwtProvider;
    @Value("${spring.datasource.url}")
    private String url;

    @Override
    public ApiResponse registerUser(RegisterDto registerDto) {
        System.out.println(url);
        boolean existsByUsername = userRepository.existsByUsername(registerDto.getEmail());
        if (existsByUsername) {
            return new ApiResponse("Bunday email li mijoz mavjud!!!", false);
        }
        UserEntity userEntity = createUserEntity(registerDto);
        userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
        userEntity.setRoles(Collections.singleton(roleRepository.findByName(RoleName.USER)));
        userEntity.setEmailCode(UUID.randomUUID().toString());
        userRepository.save(userEntity);
        //EMAILGA TASDIQLASH CODINI CHAQIRUVCHI METHOD NI CHAQIYAPMAN
        sendEmail(userEntity.getUsername(), userEntity.getEmailCode());

        return new ApiResponse("Mufoqiyatli ro'yhatdan o'tingiz emailgiz ni tastiqlang", true);
    }

    @Override
    public ApiResponse loginUser(LoginDto loginDto) {
        try {
            Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    loginDto.getEmail(), loginDto.getPassword()));
            UserEntity userEntity = (UserEntity) authenticate.getPrincipal();
            String token = jwtProvider.generateToken(userEntity.getUsername(), userEntity.getRoles());
            return new ApiResponse("Token", true, token);
        } catch (BadCredentialsException exception) {
            return new ApiResponse("Parol yoki Login xato!!!", false);
        }
    }

    @Override
    public boolean sendEmail(String sendingEmail, String emailCode) {
        try {
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setFrom("oagBlog.com");
            mailMessage.setTo(sendingEmail);
            mailMessage.setSubject("Accountni Tasdiqlash");
            mailMessage.setText("<a href='http://localhost:8585/api/auth/verify_email?emailCode=" + emailCode + "&email=" + sendingEmail + "'>Tasdiqlang</a>");
            javaMailSender.send(mailMessage);
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    @Override
    public ApiResponse verifyEmail(String emailCode, String email) {
        Optional<UserEntity> byUsernameAndEmailCode = userRepository.findByUsernameAndEmailCode(email, emailCode);
        if (byUsernameAndEmailCode.isEmpty()) {
            return new ApiResponse("Account allaqchon tasdiqlangan", false);
        }
        UserEntity userEntity = byUsernameAndEmailCode.get();
        userEntity.setEnabled(true);
        userEntity.setEmailCode(null);
        userRepository.save(userEntity);
        return new ApiResponse("Account tasdiqlandi", true);

    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username + " topilmadi"));
    }
}
