package com.transparency.controller

import com.transparency.service.DependencyService

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@CrossOrigin
@RestController
@RequestMapping("/api/dependencies")
class DependencyController {

    @Autowired
    lateinit var dependencyService: DependencyService

    @DeleteMapping()
    fun deleteImplicitFeatureDependencies() = dependencyService.deleteImplicitDependencies()

    @PutMapping(value = "/analyse")
    fun analyseImplicitFeatureDependencies() = dependencyService.analyseImplicitFeatureDependencies()
}