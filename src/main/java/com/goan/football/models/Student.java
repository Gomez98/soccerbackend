package com.goan.football.models;

import lombok.Data;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
@ToString
public class Student extends BaseModel{
    String firstName;
    String lastName;
    String fullName;
    Integer age;
    String dni;
    String email;
    Boolean active;
}
