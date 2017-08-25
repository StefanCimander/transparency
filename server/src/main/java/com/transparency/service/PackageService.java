package com.transparency.service;

import com.transparency.dao.PackageDAO;
import com.transparency.entity.PackageEntity;
import com.transparency.exception.HierarchyRootNotFoundException;
import com.transparency.exception.PackageNotFoundException;
import com.transparency.model.Package;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PackageService {

    @Autowired
    private PackageDAO packageDAO;

    public List<Package> findAll() {
        return packageDAO.findAll().stream().map(Package::new).collect(Collectors.toList());
    }

    public Package findById(long id) throws PackageNotFoundException {
        PackageEntity packageEntity = packageDAO.findById(id);
        if (packageEntity == null) {
            throw new PackageNotFoundException(id);
        }
        return new Package(packageEntity);
    }

    public Package findHierarchy() throws HierarchyRootNotFoundException {
        List<Package> allPackages = packageDAO.findAll().stream().map(Package::new).collect(Collectors.toList());
        Package rootPackage = findHierarchyRootIn(allPackages)
                .orElseThrow(HierarchyRootNotFoundException::new);
        return buildHierarchyWithRoot(rootPackage, allPackages);
    }

    private Optional<Package> findHierarchyRootIn(List<Package> packages) {
        return packages.stream().filter(pack -> pack.getParentPackage() == null).findFirst();
    }

    private Package buildHierarchyWithRoot(Package rootPackage, List<Package> packages) {
        packages.remove(rootPackage);
        while (!packages.isEmpty()) {
            int sizeBeforeInserting = packages.size();
            packages = packagesAfterInsertingInto(rootPackage, packages);
            if (packages.size() >= sizeBeforeInserting) {
                break;
            }
        }
        return rootPackage;
    }

    private List<Package> packagesAfterInsertingInto(Package rootPackage, List<Package> packages) {
        Package packageToInsert = packages.get(0);
        if (rootPackage.canAddPackageAsChild(packageToInsert)) {
            rootPackage.addChildPackage(packageToInsert);
        } else {
            packages.add(packageToInsert);
        }
        packages.remove(0);
        return packages;
    }
}
