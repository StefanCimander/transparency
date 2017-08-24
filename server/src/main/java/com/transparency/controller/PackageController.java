package com.transparency.controller;

import com.transparency.service.PackageService;
import com.transparency.model.Package;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@CrossOrigin
@RestController
@RequestMapping("/api/packages")
public class PackageController {

    @Autowired
    private PackageService packageService;

    @RequestMapping(method = GET)
    public List<Package> getAll() {
        return packageService.findAll().stream().map(Package::new).collect(Collectors.toList());
    }

    @RequestMapping(value = "/{id}")
    public Package getById(@PathVariable long id) {
        return new Package(packageService.findById(id));
    }

}
