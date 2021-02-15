package com.project.Task1.FacebookAuth;

import org.springframework.http.ResponseEntity;

public interface FacebookService {

    public String generateFacebookAuthorizeUrl();

    public ResponseEntity<Object> generateFacebookAccessToken(String code);


}
