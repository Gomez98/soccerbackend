package com.goan.football.models;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class Payment extends BaseModel {

    String studentId;
    String studentName;
    String mode;
    Double amount;

}

