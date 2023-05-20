package com.goan.football.models;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class Membership {

    String id;
    String name;
    Double price;
    Boolean active;
}
