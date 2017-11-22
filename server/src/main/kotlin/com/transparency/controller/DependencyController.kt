package com.transparency.controller

import com.transparency.model.DependencyDetails
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

    @GetMapping(value = "/details")
    fun getDependencyDetails(@RequestParam(name = "sourceId") sourceFeatureId: Long,
                             @RequestParam(name = "targetId") targetFeatureId: Long): DependencyDetails =
            dependencyService.getDetailsWithSourceAndTargetId(sourceFeatureId, targetFeatureId)

    @GetMapping(value = "/statistics")
    fun getDependencyStatistics() = dependencyService.getDependencyStatistics()
}