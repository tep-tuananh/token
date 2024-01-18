package com.ra.scurity.user_principal.IMPL;

import com.ra.model.entity.User;
import com.ra.repisitory.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userOptional = userRepository.findByUserName(username);
        if(userOptional.isPresent()){
            User user = userOptional.get();
           return UserPrincipale.builder().user(user).authorities(user.getRoles().stream().map(
                   item-> new SimpleGrantedAuthority(item.getName())).toList()).build();
        }
        return null;
    }
}
