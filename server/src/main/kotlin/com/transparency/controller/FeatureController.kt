package com.transparency.controller

import com.transparency.service.FeatureService
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
    private lateinit var featureService: FeatureService

    @GetMapping()
    fun getAll() = featureService.findAll()

    @GetMapping(value = "/logically-depending")
    fun getAllWithLogicalDependencies() = featureService.findAllWithLogicalDependencies()
}
