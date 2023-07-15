package com.goan.football.utils;

import com.goan.football.models.BaseModel;

import java.util.Date;

public class ModelUtils {

    public static <S extends BaseModel> void prepare(S entity){
        entity.setCreatedAt(String.valueOf(new Date().getTime()));
        entity.setModifiedAt(String.valueOf(new Date().getTime()));
        entity.setDeleted(false);
    }
}
