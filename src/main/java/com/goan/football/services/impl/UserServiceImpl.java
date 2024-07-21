package com.goan.football.services.impl;

import com.goan.football.config.JwtService;
import com.goan.football.models.Search;
import com.goan.football.models.User;
import com.goan.football.models.token.Token;
import com.goan.football.models.token.TokenType;
import com.goan.football.repositories.TokenRepository;
import com.goan.football.repositories.UserRepository;
import com.goan.football.services.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static com.goan.football.utils.ModelUtils.prepare;


@Service
@AllArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final TokenRepository tokenRepository;


    @Override
    public User save(User entity) {
        prepare(entity);
        entity.setPassword(passwordEncoder.encode(entity.getPassword()));
        var savedUser = userRepository.save(entity);
        var jwtToken = jwtService.generateToken(entity);
        saveUserToken(savedUser, jwtToken);
        return userRepository.save(entity);
    }

    @Override
    public User update(User entity) {
        if (entity == null) {
            return null;
        }
        if (entity.getId() == null) {
            return null;
        }
        User user = userRepository.findById(entity.getId()).orElse(null);
        if(user != null){
            user.setDeleted(entity.getDeleted() != null ? entity.getDeleted() : user.getDeleted());
            user.setEmail(entity.getEmail() != null ? entity.getEmail() : user.getEmail());
            user.setFirstName(entity.getFirstName() != null ? entity.getFirstName() : user.getFirstName());
            user.setLastName(entity.getLastName() != null ? entity.getLastName() : user.getLastName());
            user.setRole(entity.getRole() != null ? entity.getRole() : user.getRole());
            user.setModifiedAt(String.valueOf(new Date().getTime()));
            return userRepository.save(user);
        }
        return null;
    }

    @Override
    public User get(String id) {
        User user = userRepository.findById(id).orElse(null);
        return user;
    }

    @Override
    public List<User> all(Search search) {
        List<User> users = userRepository.findAll();
        users = users.stream().filter(s -> !s.getDeleted()).collect(Collectors.toList());
        return users;
    }

    @Override
    public List<User> all(String id) {
        return null;
    }

    private void saveUserToken(User user, String jwtToken) {
        var token = Token.builder()
                .user(user)
                .token(jwtToken)
                .tokenType(TokenType.BEARER)
                .expired(false)
                .revoked(false)
                .build();
        tokenRepository.save(token);
    }
}
