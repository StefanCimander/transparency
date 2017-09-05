package com.transparency.controller

import com.transparency.service.DependencyService

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@CrossOrigin
@RestController
@RequestMapping("/api/dependencies")
class DependencyController {

    @Autowired
    lateinit var dependencyService: DependencyService

    @PutMapping(value = "/analyse")
    fun analyseLogicalFeatureDependencies() {
        dependencyService.analyseImplicitFeatureDependencies()
    }
}