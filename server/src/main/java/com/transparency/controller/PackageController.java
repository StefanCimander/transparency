package com.transparency.controller;

import com.transparency.exception.HierarchyRootNotFoundException;
import com.transparency.model.Package;
import com.transparency.service.PackageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@CrossOrigin
@RestController
@RequestMapping("/api/packages")
public class PackageController {

    @Autowired
    private PackageService packageService;

    @RequestMapping(method = GET)
    public List<Package> getAll() {
        return packageService.findAll();
    }

    @RequestMapping(value = "/{id}")
    public Package getById(@PathVariable long id) {
        return packageService.findById(id);
    }

    @RequestMapping(value = "/hierarchy")
    public Package getHierarchy() throws HierarchyRootNotFoundException {
        return packageService.findHierarchy();
    }
}
