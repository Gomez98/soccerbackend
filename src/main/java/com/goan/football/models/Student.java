package com.goan.football.models;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class Student extends BaseModel{
    String firstName;
    String lastName;
    Integer age;
    String studentRole;
    String membershipName;
    Double payment;
}
