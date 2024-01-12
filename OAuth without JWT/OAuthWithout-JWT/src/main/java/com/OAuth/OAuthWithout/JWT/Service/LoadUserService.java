package com.OAuth.OAuthWithout.JWT.Service;


import com.OAuth.OAuthWithout.JWT.Entity.UserEntity;
import com.OAuth.OAuthWithout.JWT.Repository.UserEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@RequiredArgsConstructor
public class LoadUserService {

    private final UserEntityRepository userEntityRepository;

    public UserEntity loadUserByUsername(String username){

        UserEntity userEntity = userEntityRepository.findByEmail(username);

        if(userEntity==null){
            throw new UsernameNotFoundException("User Not Found");
        }

        return userEntity;
    }
}
