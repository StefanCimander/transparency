package com.transparency.controller

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
    fun getHierarchy() = packageService.findHierarchy()
}
