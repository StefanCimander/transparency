package com.transparency.features.service

import com.transparency.features.FeatureComponent
import com.transparency.features.dao.FeatureDAO
import com.transparency.features.entity.Feature
import com.transparency.features.exception.FeatureNotFoundException
import com.transparency.features.transport.FeatureTO
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class FeatureService: FeatureComponent {

    @Autowired
    private lateinit var featureDAO: FeatureDAO

    override fun findAll() = featureDAO.findAll()
            .map(::FeatureTO)

    override fun findAllLogicallyDepending() = featureDAO.findAllIncludingDependencies()
            .filter(Feature::hasLogicalDependencies)
            .map(::FeatureTO)

    @Throws(FeatureNotFoundException::class)
    override fun findById(id: Long) = FeatureTO(featureDAO.findById(id))
}