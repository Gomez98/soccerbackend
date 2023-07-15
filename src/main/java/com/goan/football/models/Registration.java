package com.goan.football.models;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class Registration extends BaseModel{
    Student student;
    String workshopName;
    String schedule;
}
