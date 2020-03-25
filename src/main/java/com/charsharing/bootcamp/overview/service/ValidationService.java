package com.charsharing.bootcamp.overview.service;

import org.springframework.stereotype.Service;

@Service
public class ValidationService {

    public boolean isTitleEmpty(String title){
        if(title.trim().isEmpty()){
            return true;
        }
        return false;
    }
    public boolean isCreatorEmpty(String creator){
        if(creator.trim().isEmpty()){
            return true;
        }
        return false;
    }

}
