package com.goan.football.services.impl;

import com.goan.football.models.Search;
import com.goan.football.models.Student;
import com.goan.football.models.Workshop;
import com.goan.football.repositories.WorkshopRepository;
import com.goan.football.services.WorkshopService;
import com.goan.football.utils.ModelUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class WorkshopServiceImpl implements WorkshopService {

    private final WorkshopRepository workshopRepository;

    @Override
    public Workshop save(Workshop entity) {
        ModelUtils.prepare(entity);
        return workshopRepository.save(entity);
    }

    @Override
    public Workshop update(Workshop entity) {
        if (entity == null) {
            return null;
        }
        if (entity.getId() == null) {
            return null;
        }
        Workshop workshop = workshopRepository.findById(entity.getId()).orElse(null);
        if(workshop != null){
            workshop.setDeleted(entity.getDeleted() != null ? entity.getDeleted() : workshop.getDeleted());
            workshop.setName(entity.getName() != null ? entity.getName() : workshop.getName());
            workshop.setPrice(entity.getPrice() != null ? entity.getPrice() : workshop.getPrice());
            workshop.setSchedule(entity.getSchedule() != null ? entity.getSchedule() : workshop.getSchedule());
            workshop.setModifiedAt(String.valueOf(new Date().getTime()));
            return workshopRepository.save(workshop);
        }
        return null;
    }

    @Override
    public Workshop get(String id) {
        return null;
    }

    @Override
    public List<Workshop> all(Search search) {
        List<Workshop> workshops = new ArrayList<>();
        String term = null;
        Integer page = null;
        Integer size = null;

        if (search != null && search.getTerm() != null) {
            term = search.getTerm();
        }
        if (search != null && search.getPage() != null) {
            page = search.getPage();
        }
        if (search != null && search.getSize() != null) {
            size = search.getSize();
        }

        if (page == null && size == null && term == null){
            workshops = workshopRepository.findAll()
                    .stream()
                    .sorted(Comparator.comparing(Workshop::getModifiedAt).reversed())
                    .collect(Collectors.toList());
        }
        workshops = workshops.stream().filter(s -> !s.getDeleted()).collect(Collectors.toList());
        return workshops;
    }

    @Override
    public List<Workshop> all(String id) {
        return null;
    }
}
