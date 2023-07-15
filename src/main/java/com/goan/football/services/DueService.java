package com.goan.football.services;

import com.goan.football.models.Due;
import com.goan.football.utils.BaseService;

import java.util.List;

public interface DueService extends BaseService<Due> {

    List<Due> payDues(List<String> dueIds);
}
