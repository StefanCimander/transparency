package com.transparency.service;

import com.transparency.dao.FeatureDAO;
import com.transparency.entity.FeatureEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeatureService {

    @Autowired
    private FeatureDAO featureDAO;

    public List<FeatureEntity> findAll() {
        return featureDAO.findAll();
    }
}
