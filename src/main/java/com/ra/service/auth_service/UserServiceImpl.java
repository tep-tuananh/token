package com.ra.service.auth_service;

import com.ra.model.dto.requset.UserLogin;
import com.ra.model.dto.response.UserResponse;
import com.ra.model.entity.Role;
import com.ra.model.entity.User;
import com.ra.repisitory.RoleRepositoty;
import com.ra.repisitory.UserRepository;
import com.ra.scurity.jwt.JwtProvider;
import com.ra.scurity.user_principal.IMPL.UserPrincipale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepositoty roleRepositoty;
    @Autowired
    private AuthenticationProvider authenticationProvider;
    @Autowired
    private JwtProvider jwtProvider;
    @Override
    public User register(User user) {
        // mã hóa mật khẩu
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
     Role role = roleRepositoty.findByName("ROLE_USER"); // mặc định khi thêm là user không phải  là admin
        Set<Role> roles = new HashSet<>();
        roles.add(role);
       user.setRoles(roles);
        return userRepository.save(user);
    }

    @Override
    public UserResponse login(UserLogin userLogin) {
        Authentication authentication;
        try {
            authentication = authenticationProvider.
           authenticate(new UsernamePasswordAuthenticationToken(userLogin.getUserName(), userLogin.getPassword()));
        }catch (AuthenticationException e)
        {
            throw new RuntimeException("Đăng nhập sai tên tài khoản hoặc mật khẩu");
        }
        UserPrincipale userPrincipale= (UserPrincipale) authentication.getPrincipal();
        // tao token
        String token = jwtProvider.generateToken(userPrincipale);
        // convert sang đối tượng userResponse
        return UserResponse.builder().
                fullName(userPrincipale.getUser().getFullName()).
                id(userPrincipale.getUser().getId())
                .token(token).
               roles(userPrincipale.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toSet())).
                build();
    }
}
