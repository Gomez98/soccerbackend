package com.goan.football.services.impl;

import com.goan.football.models.Due;
import com.goan.football.models.DueStatusEnum;
import com.goan.football.models.Search;
import com.goan.football.repositories.DueRepository;
import com.goan.football.services.DueService;
import com.goan.football.utils.ModelUtils;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class DueServiceImpl implements DueService {

    private final DueRepository dueRepository;


    @Override
    public Due save(Due entity) {
        ModelUtils.prepare(entity);
        return dueRepository.save(entity);
    }

    @Override
    public Due update(Due entity) {
        return null;
    }

    @Override
    public Due get(String id) {
        return null;
    }

    @Override
    public List<Due> all(Search search) {
        List<Due> dues;
        String term = null;
        Integer page = null;
        Integer size = null;

        if (search.getTerm() != null) {
            term = search.getTerm();
        }
        if (search.getPage() != null) {
            page = search.getPage();
        }
        if (search.getSize() != null) {
            size = search.getSize();
        }

        if (page == null && size == null && term == null){
            dues = dueRepository.findAll()
                    .stream()
                    .sorted(Comparator.comparing(Due::getModifiedAt).reversed())
                    .collect(Collectors.toList());
        } else if (page != null && size !=null){
            if (term == null){
                term = "";
            }
            Pageable pageable = PageRequest.of(page, size, Sort.by("name").ascending());
            Page<Due> studentPage = dueRepository.findAllByName(term, pageable);
            dues = studentPage.get().toList();
        } else {
            dues = dueRepository.findAllByName(term);
        }


        return dues;
    }

    @Override
    public List<Due> all(String id) {
        return dueRepository.findAllByStudentId(id);
    }

    @Override
    public List<Due> payDues(List<String> dueIds) {

        List<Due> dueList = dueRepository.findAllById(dueIds);
        for (Due due: dueList) {
            due.setStatus(DueStatusEnum.PAID.name());
        }
        return dueRepository.saveAll(dueList);
    }
}
