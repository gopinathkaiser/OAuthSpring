package com.OAuth.OAuthWithout.JWT.Config;


import com.OAuth.OAuthWithout.JWT.Entity.UserEntity;
import com.OAuth.OAuthWithout.JWT.Service.LoadUserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;


//@Component
//@RequiredArgsConstructor
//public class OAuthSuccessHandler implements AuthenticationSuccessHandler {
//
//    private final LoadUserService loadUserService;
//
//    @Override
//    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
//        String email = ((OAuth2AuthenticationToken)authentication).getPrincipal().getAttributes().getOrDefault("email","").toString();
//        UserEntity userEntity = loadUserService.loadUserByUsername(email);
//        System.out.println("hello");
////        SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
////        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
////                userEntity,null,userEntity.getAuthorities()
////        );
////        token.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
////        securityContext.setAuthentication(token);
////        SecurityContextHolder.setContext(securityContext);
//
//
//    }
//}


//@Bean
@Service
@RequiredArgsConstructor
public class OAuthSuccessHandler implements AuthenticationSuccessHandler {

    private final LoadUserService loadUserService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        String email = ((OAuth2AuthenticationToken) authentication).getPrincipal().getAttributes().getOrDefault("email", "").toString();
        UserEntity userEntity =  loadUserService.loadUserByUsername(email);
        DefaultOAuth2User principal = (DefaultOAuth2User) authentication.getPrincipal();
//        String temp = principal.getAttribute("nameAttributeKey");
        Map<String,Object> attributes = principal.getAttributes();
        System.out.println(principal.getAttributes());
        DefaultOAuth2User user = new DefaultOAuth2User(userEntity.getAuthorities(),attributes,"sub");
        Authentication securityAuth = new OAuth2AuthenticationToken(user,userEntity.getAuthorities(),((OAuth2AuthenticationToken) authentication).getAuthorizedClientRegistrationId());
        SecurityContextHolder.getContext().setAuthentication(securityAuth);
        System.out.println("Doneeee");
    }
}

