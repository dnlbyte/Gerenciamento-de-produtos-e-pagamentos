package com.DanielSilva.salesmanagement.security.application.service;

import com.DanielSilva.salesmanagement.security.application.dto.UserResponse;
import com.DanielSilva.salesmanagement.security.application.form.AuthRequest;
import com.DanielSilva.salesmanagement.security.domain.exceptions.user.UserInsertException;
import com.DanielSilva.salesmanagement.security.domain.exceptions.user.UserNotFoundException;
import com.DanielSilva.salesmanagement.security.domain.model.UserModel;
import com.DanielSilva.salesmanagement.security.domain.repository.UserRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserModel  findByUsernameAndActiveIsTrue(String email){
        return userRepository.findByUsernameAndIsActiveIsTrue(email).orElseThrow( () -> new UserNotFoundException(String.format("User '%s' was not found", email)));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info(username);
        UserModel user = userRepository.findByUsernameAndIsActiveIsTrue(username)
                .orElseThrow(() -> new UserNotFoundException("User not found"));
        return org.springframework.security.core.userdetails.User
                .withUsername(user.getUsername())
                .password(user.getPassword())
                .roles(user.getRole().name())
                .build();
    }

    public void register(AuthRequest form) {
        Date date = new Date();

        UserModel user = new UserModel();
        user.setUsername(form.getEmail());
        user.setPassword(passwordEncoder.encode(form.getPassword()));
        user.setCreatedAt(date);
        user.setUpdatedAt(date);
        user.setActive(true);
        user.setVersion(1);
        user.setRole(form.getRole());

        try {
            userRepository.save(user);
        } catch(DataIntegrityViolationException err){
            throw new UserInsertException(String.format("Failed to register the user ‘%s’. Check if the data is correct", form.getEmail()));
        }
    }

    public List<UserResponse> getAll() {
        return userRepository.findAll()
                .stream()
                .map(model -> new UserResponse(model.getUsername(), model.getRole()))
                .collect(Collectors.toList());
    }

}
