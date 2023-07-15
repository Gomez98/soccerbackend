package com.goan.football.models;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document
public class Workshop extends BaseModel{
    String name;
    List<String> schedule;
    Double price;
}
