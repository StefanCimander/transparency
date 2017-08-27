package com.transparency.controller

import com.transparency.model.Feature
import com.transparency.service.FeatureService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@CrossOrigin
@RestController
@RequestMapping("/api/features")
class FeatureController {

    @Autowired
    private lateinit var featureService: FeatureService

    @GetMapping()
    fun getAll(): List<Feature> {
        return featureService.findAll()
    }
}
