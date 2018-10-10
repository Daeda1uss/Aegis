package com.aegis.resource;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * Created by GarryGe on 2018/9/17.
 */
@RestController
public class AuthenticateController {
    @GetMapping("/current")
    public ResponseEntity<Principal> current(Principal principal) {
        return ResponseEntity.ok(principal);
    }
}
