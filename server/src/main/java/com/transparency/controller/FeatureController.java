package com.transparency.controller;

import com.transparency.model.Feature;
import com.transparency.service.FeatureService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@CrossOrigin
@RestController
@RequestMapping("/api/features")
public class FeatureController {

    @Autowired
    private FeatureService featureService;

    @RequestMapping(method = GET)
    public List<Feature> getAll() {
        return featureService.findAll().stream().map(Feature::new).collect(Collectors.toList());
    }
}
