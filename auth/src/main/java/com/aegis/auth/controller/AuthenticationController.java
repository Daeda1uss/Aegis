package com.aegis.auth.controller;

import com.aegis.auth.model.RegisterInfo;
import com.aegis.auth.model.User;
import com.aegis.auth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by GarryGe on 2018/3/28.
 */
@RestController
public class AuthenticationController {
    @Autowired
    private UserService userService;

    @PostMapping(value = "register")
    public ResponseEntity<Boolean> register(@RequestBody RegisterInfo registerInfo) {
        return ResponseEntity.ok(userService.register(registerInfo));
    }

    @GetMapping(value = "/current")
    public Principal getUser(Principal principal) {
        return principal;
    }

    @PutMapping(value = "/client")
    public ResponseEntity<String> createClient(@RequestBody BaseClientDetails clientDetails) {
        userService.insert(clientDetails);
        return ResponseEntity.ok("ok");
    }

    @GetMapping(value = "/user")
    public Map<String, Object> getUserInfo(Principal principal) {
        String username = principal.getName();
        User user = userService.findUser(username);
        Map<String, Object> userInfo = new HashMap<>();
        userInfo.put("username", username);
        userInfo.put("email", user.getEmail());
        return userInfo;
    }
}
