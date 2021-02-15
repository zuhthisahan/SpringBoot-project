package com.project.Task1.FacebookAuth;

import com.project.Task1.Customer.Model.CustomerEntity;
import com.project.Task1.Customer.Model.CustomerEntityRepository;
import com.project.Task1.Customer.Model.OuthCustomerEntity;
import com.project.Task1.Customer.Model.OuthCustomerEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.social.connect.Connection;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.User;
import org.springframework.social.facebook.connect.FacebookConnectionFactory;
import org.springframework.social.oauth2.AccessGrant;
import org.springframework.social.oauth2.OAuth2Operations;
import org.springframework.social.oauth2.OAuth2Parameters;
import org.springframework.stereotype.Service;

@Service
public class FacebookServiceImpl implements FacebookService{

    @Autowired
    private OuthCustomerEntityRepository outhCustomerEntityRepository;

    @Autowired
    private CustomerEntityRepository customerEntityRepository;


//    @Value("${spring.social.facebook.app-id}")
//    private String facebookId;
//
//    @Value("${spring.social.facebook.app-secret}")
//    private String facebookSecret;

    // Can use the above defined values but I'm getting different error so i directly use the app id and secret below
    private FacebookConnectionFactory createConnection(){
        return new FacebookConnectionFactory("718561482184158", "1484dcaf54c74b8ba9f3a6dfd82677ca");
    }

    @Override
    public String generateFacebookAuthorizeUrl() {
        OAuth2Parameters params = new OAuth2Parameters();
        params.setRedirectUri("http://localhost:8080/facebook");
        params.setScope("email");
        return createConnection().getOAuthOperations().buildAuthenticateUrl(params);

    }

    @Override
    public ResponseEntity<Object> generateFacebookAccessToken(String code) {
        // System.out.println("genFabAuth");
        OAuth2Operations operations =createConnection().getOAuthOperations();
        AccessGrant accessToken = operations.exchangeForAccess(code, "http://localhost:8080/facebook",
                null);
        System.out.println("Access Token : " + accessToken.getAccessToken());

        Connection<Facebook> connection = createConnection().createConnection(accessToken);
        Facebook facebook = connection.getApi();
        String[] fields = { "id", "email", "first_name", "last_name" };

        User userProfile = facebook.fetchObject("me", User.class, fields);
        System.out.println(userProfile.getId());
        System.out.println(userProfile.getEmail());

        OuthCustomerEntity outhCustomerEntity = new OuthCustomerEntity();
        outhCustomerEntity.setEmail(userProfile.getEmail());
        outhCustomerEntity.setUid(userProfile.getId());
        outhCustomerEntity.setFirstName(userProfile.getFirstName());
        outhCustomerEntity.setLastName(userProfile.getLastName());
        outhCustomerEntity.setType("facebook");

        CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.setOuthCustomerEntity(outhCustomerEntity);
        customerEntity.setEmail(userProfile.getEmail());

        outhCustomerEntityRepository.save(outhCustomerEntity);
        customerEntityRepository.save(customerEntity);
        return ResponseEntity.ok().body("Signup successfully");
    }


}