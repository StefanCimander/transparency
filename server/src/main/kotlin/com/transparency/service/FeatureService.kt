package com.transparency.service

import com.transparency.dao.FeatureDAO
import com.transparency.model.Feature
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class FeatureService {

    @Autowired
    private lateinit var featureDAO: FeatureDAO

    fun findAll(): List<Feature> {
        return featureDAO.findAll().map(::Feature)
    }
}