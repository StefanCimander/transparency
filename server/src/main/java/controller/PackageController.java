package controller;

import model.Package;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api")
public class PackageController {

    @RequestMapping("/packages")
    public List<Package> getAll() {
        return Arrays.asList(
                new Package(1234, "First Package"),
                new Package(5678, "Second Package")
        );
    }
}
