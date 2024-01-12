package com.OAuth.OAuthWithout.JWT.Controller;


import com.OAuth.OAuthWithout.JWT.Entity.UserEntity;
import com.OAuth.OAuthWithout.JWT.Repository.UserEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.OAuth2AuthenticatedPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TempController {

    private final UserEntityRepository userEntityRepository;

    @GetMapping("/")
    public String temp(@AuthenticationPrincipal OAuth2AuthenticatedPrincipal oAuth2AuthenticatedPrincipal){
        UserEntity userEntity = userEntityRepository.findByEmail(oAuth2AuthenticatedPrincipal.getAttributes().get("email").toString());
        System.out.println(userEntity.getAuthorities());

        return oAuth2AuthenticatedPrincipal.getAttributes().get("email").toString();
    }


    @GetMapping("/admin/get")
    public String hi(){
        return "Hello";
    }
}
