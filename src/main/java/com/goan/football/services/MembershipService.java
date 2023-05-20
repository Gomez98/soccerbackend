package com.goan.football.services;

import com.goan.football.models.Membership;

public interface MembershipService {

    Membership save(Membership membership);

    Membership update(String id, Membership membership);

    Membership get(String id);
}
