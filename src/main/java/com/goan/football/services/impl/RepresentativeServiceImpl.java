package com.goan.football.services.impl;

import com.goan.football.models.Representative;
import com.goan.football.models.Student;
import com.goan.football.repositories.RepresentativeRepository;
import com.goan.football.repositories.StudentRepository;
import com.goan.football.services.RepresentativeService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@AllArgsConstructor
@Slf4j
public class RepresentativeServiceImpl implements RepresentativeService {

    private final RepresentativeRepository representativeRepository;

    @Override
    public Representative save(Representative representative) {
        return representativeRepository.save(representative);
    }

    @Override
    public Representative get(String id) {
        return representativeRepository.findById(id).orElse(null);
    }

    @Override
    public List<Representative> all(String term, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("name").ascending());
        Page<Representative> representativePage = representativeRepository.findAllByName(term, pageable);
        return representativePage.get().toList();
    }
}
