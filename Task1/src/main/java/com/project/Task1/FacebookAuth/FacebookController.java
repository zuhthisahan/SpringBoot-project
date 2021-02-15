package com.project.Task1.FacebookAuth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class FacebookController {

    @Autowired
    private FacebookService facebookService;

    @GetMapping("/generateFacebookAuthorizeUrl")
    public String generateFacebookAuthorizeUrl(){
        return facebookService.generateFacebookAuthorizeUrl();
    }

    @GetMapping("/facebook")
    public void generateFacebookAccessToken(@RequestParam("code") String code){
        facebookService.generateFacebookAccessToken(code);
    }


}
