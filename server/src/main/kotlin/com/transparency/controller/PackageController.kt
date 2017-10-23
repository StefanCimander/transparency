package com.transparency.controller

import com.transparency.model.Package
import com.transparency.service.DependenciesRequest
import com.transparency.service.PackageService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@CrossOrigin
@RestController
@RequestMapping("/api/packages")
class PackageController {

    @Autowired
    private lateinit var packageService: PackageService

    @GetMapping
    fun findAll() = packageService.findAll()

    @GetMapping(value = "/{id}")
    fun getById(@PathVariable id: Long) = packageService.findById(id)

    @GetMapping(value = "/hierarchy")
    fun getHierarchy(@RequestParam(name = "dependencies") dependencies: String): Package {
        val dependenciesRequest = when (dependencies.toUpperCase()) {
            "ONLY_EXPLICIT" -> DependenciesRequest.ONLY_EXPLICIT
            "ONLY_IMPLICIT" -> DependenciesRequest.ONLY_IMPLICIT
            "CONFORMAL" -> DependenciesRequest.CONFORMAL
            "NON_CONFORMAL" -> DependenciesRequest.NON_CONFORMAL
            else -> DependenciesRequest.ALL_DEPENDENCIES
        }
        return packageService.findHierarchy(dependenciesRequest)
    }
}
