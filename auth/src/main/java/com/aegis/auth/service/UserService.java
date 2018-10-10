package com.aegis.auth.service;

import com.aegis.auth.model.RegisterInfo;
import com.aegis.auth.model.User;
import com.aegis.auth.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.stereotype.Service;

/**
 * Created by GarryGe on 2018/3/27.
 */
@Service
public class UserService {
    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @Autowired
    private JdbcClientDetailsService clientDetailsService;
    @Autowired
    private JdbcUserDetailsManager userDetailsService;
    @Autowired
    private UserRepository userRepository;

    public boolean register(RegisterInfo registerInfo) {
        User user = new User();
        user.setUsername(registerInfo.getUsername());
        user.setPassword(encoder.encode(registerInfo.getPassword()));
        userDetailsService.createUser(user);
        return true;
    }

    public void insert(ClientDetails clientDetails) {
        clientDetailsService.addClientDetails(clientDetails);
    }

    public User findUser(String username) {
        return userRepository.findByUsername(username);
    }
}
