package com.goan.football.services.impl;

import com.goan.football.models.Membership;
import com.goan.football.repositories.MembershipRepository;
import com.goan.football.services.MembershipService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class MembershipServiceImpl implements MembershipService {

    private final MembershipRepository membershipRepository;

    @Override
    public Membership save(Membership membership) {

        return membershipRepository.save(membership);
    }

    @Override
    public Membership update(String id, Membership membership) {
        Membership membershipUpdated = new Membership();
        membershipUpdated.setId(id);
        if (membership.getName() != null){
            membershipUpdated.setName(membership.getName());
        }
        if (membership.getPrice() != null) {
            membershipUpdated.setPrice(membership.getPrice());
        }
        if (membership.getActive() != null) {
            membershipUpdated.setActive(membership.getActive());
        }
        return membershipRepository.save(membershipUpdated);
    }

    @Override
    public Membership get(String id) {
        return membershipRepository.findById(id).orElse(null);
    }
}
