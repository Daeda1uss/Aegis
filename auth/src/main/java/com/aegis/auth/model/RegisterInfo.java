package com.aegis.auth.model;

import lombok.Data;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Created by GarryGe on 2018/3/28.
 */
@Data
public class RegisterInfo {
    private String username;
    private String password;

    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        System.out.println(encoder.encode("admin"));
    }
}
