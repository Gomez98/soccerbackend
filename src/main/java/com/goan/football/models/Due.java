package com.goan.football.models;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Data
@Document
public class Due extends BaseModel{
    String studentId;
    Double amount;
    String name;
    String status;
    LocalDate creationDate;
    LocalDate dueDate;
    String registrationId;
    String workshopName;
}
