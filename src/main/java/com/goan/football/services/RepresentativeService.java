package com.goan.football.services;

import com.goan.football.models.Representative;

import java.util.List;

public interface RepresentativeService {

    Representative save(Representative student);

    Representative get(String id);

    List<Representative> all(String term, int page, int size);
}
