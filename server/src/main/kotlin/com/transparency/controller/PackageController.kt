package com.transparency.controller

import com.transparency.exception.HierarchyRootNotFoundException
import com.transparency.exception.PackageNotFoundException
import com.transparency.model.Package
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
    fun findAll(): List<Package> {
        return packageService.findAll()
    }

    @GetMapping(value = "/{id}")
    @Throws(PackageNotFoundException::class)
    fun getById(@PathVariable id: Long): Package {
        return packageService.findById(id)
    }

    @GetMapping(value = "/hierarchy")
    @Throws(HierarchyRootNotFoundException::class)
    fun getHierarchy(): Package {
        return packageService.findHierarchy()
    }
}
