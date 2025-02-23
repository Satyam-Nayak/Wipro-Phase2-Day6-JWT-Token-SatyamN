package com.ust.Security.service;


import com.ust.Security.model.Userinfo;
import com.ust.Security.repository.Userinforepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

@Service
public class Userservices {
    @Autowired
    private Userinforepository repo;
    @Autowired
    private PasswordEncoder passwordEncoder;
    public String addUser(Userinfo userInfo) {
        userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));
        repo.save(userInfo);
        return "user added to system ";
    }

    @Transactional
    public String resetPassword(String email, String newPassword) {
        Userinfo user = repo.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found with email: " + email));

        user.setPassword(passwordEncoder.encode(newPassword));  // Encrypt new password
        repo.save(user);
        return "Password reset successful";
    }
}
