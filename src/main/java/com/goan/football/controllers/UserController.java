package com.goan.football.controllers;

import com.goan.football.models.Search;
import com.goan.football.models.User;
import com.goan.football.services.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@AllArgsConstructor
@Slf4j
@PreAuthorize("hasRole('ADMIN')")
public class UserController {

    private final UserService userService;

    @MutationMapping
    public User addUser(@Argument User user){
        return userService.save(user);
    }

    @MutationMapping
    public User updateUser(@Argument User user){
        return userService.update(user);
    }

    @QueryMapping
    @PreAuthorize("hasAnyAuthority('user:read', 'admin:read')")
    public User getUser(@Argument String id){
        return userService.get(id);
    }

    @QueryMapping
    @PreAuthorize("hasAnyAuthority('user:read', 'admin:read')")
    public List<User> allUsers(@Argument Search search){
        return userService.all(search);
    }
}
