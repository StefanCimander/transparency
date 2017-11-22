package com.transparency.features.controller

import com.transparency.features.FeatureComponent
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@CrossOrigin
@RestController
@RequestMapping("/api/features")
class FeatureController {

    @Autowired
    private lateinit var featureComponent: FeatureComponent

    @GetMapping()
    fun getAll() = featureComponent.findAll()

    @GetMapping(value = "/logically-depending")
    fun getAllLogicallyDepending() = featureComponent.findAllLogicallyDepending()
}
