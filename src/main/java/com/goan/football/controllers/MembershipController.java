package com.goan.football.controllers;

import com.goan.football.models.Membership;
import com.goan.football.services.MembershipService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
@AllArgsConstructor
@Slf4j
public class MembershipController {

    private final MembershipService membershipService;

    @QueryMapping
    public Membership addMembership(@Argument Membership membership){
        return membershipService.save(membership);
    }

    @QueryMapping
    public Membership updateMembership(@Argument String id, @Argument Membership membership){
        return  membershipService.update(id, membership);
    }

    @QueryMapping
    public Membership getMembership(@Argument String id){
        return  membershipService.get(id);
    }

}
