package com.transparency.dependencies.controller

import com.transparency.dependencies.DependencyComponent
import com.transparency.dependencies.transport.DependencyDetailsTO
import com.transparency.dependencies.service.DependencyService

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@CrossOrigin
@RestController
@RequestMapping("/api/dependencies")
class DependencyController {

    // TODO: SC - Only use dependency component intead of service

    @Autowired
    lateinit var dependencyService: DependencyService

    @Autowired
    lateinit var dependencyComponent: DependencyComponent

    @DeleteMapping()
    fun deleteImplicitFeatureDependencies() = dependencyService.deleteImplicitDependencies()

    @PutMapping(value = "/analyse")
    fun analyseImplicitFeatureDependencies() = dependencyService.analyseImplicitFeatureDependencies()

    @GetMapping(value = "/details")
    fun getDependencyDetails(@RequestParam(name = "source") sourceFeatureId: Long,
                             @RequestParam(name = "target") targetFeatureId: Long): DependencyDetailsTO =
            dependencyComponent.findDetailsWithSourceAndTargetFeatureIds(sourceFeatureId, targetFeatureId)

    @GetMapping(value = "/statistics")
    fun getDependencyStatistics() = dependencyService.getDependencyStatistics()
}