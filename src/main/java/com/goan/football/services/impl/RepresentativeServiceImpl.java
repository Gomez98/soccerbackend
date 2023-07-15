package com.goan.football.services.impl;

import com.goan.football.models.Representative;
import com.goan.football.models.Search;
import com.goan.football.models.Student;
import com.goan.football.models.StudentRole;
import com.goan.football.repositories.RepresentativeRepository;
import com.goan.football.services.RepresentativeService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


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
    public Representative update(Representative entity) {
        if (entity == null) {
            return null;
        }
        if (entity.getId() == null) {
            return null;
        }
        Representative representative = representativeRepository.findById(entity.getId()).orElse(null);
        if(representative != null){
            representative.setDeleted(entity.getDeleted() != null ? entity.getDeleted() : representative.getDeleted());
            representative.setName(entity.getName() != null ? entity.getName() : representative.getName());
            representative.setStudentName(entity.getStudentName() != null ? entity.getStudentName() : representative.getStudentName());
            representative.setModifiedAt(String.valueOf(new Date().getTime()));
            return representativeRepository.save(representative);
        }
        return null;
    }

    @Override
    public Representative get(String id) {
        return representativeRepository.findById(id).orElse(null);
    }

    @Override
    public List<Representative> all(Search search) {
        List<Representative> representatives = representativeRepository.findAll();
        representatives = representatives.stream().filter(s -> !s.getDeleted()).collect(Collectors.toList());
        return representatives;
    }

    @Override
    public List<Representative> all(String id) {
        return null;
    }
}
