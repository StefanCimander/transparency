package controller

import model.Feature

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class FeatureController {

    @RequestMapping("/features")
    fun getAll(): Feature {
        return Feature(
                id = 7643,
                name = "Test Feature"
        )
    }
}