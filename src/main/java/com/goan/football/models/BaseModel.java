package com.goan.football.models;

import lombok.Data;

@Data
public class BaseModel {
    String id;
    String createdAt;
    String modifiedAt;
    String modifiedBy;
    boolean deleted;
}
