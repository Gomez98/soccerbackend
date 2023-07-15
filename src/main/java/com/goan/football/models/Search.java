package com.goan.football.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Search {
    String term;
    Integer page;
    Integer size;
}
