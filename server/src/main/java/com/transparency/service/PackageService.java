package com.transparency.service;

import com.transparency.dao.PackageDAO;
import com.transparency.entity.PackageEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PackageService {

    @Autowired
    private PackageDAO packageDAO;

    /**
     *
     *
     * @return
     */
    public List<PackageEntity> findAll() {
        return packageDAO.findAll(false);
    }

    /**
     * Finds the package hierarchy.
     *
     * @return The root package with the hierarchy initialized.
     */
    public PackageEntity findPackageHierarchy(boolean includeLogicalDependencies) {
        List<PackageEntity> packages = packageDAO.findAll(includeLogicalDependencies);
        PackageEntity rootPackage = initializeRootPackage(packages);
        while (!packages.isEmpty()) {
            PackageEntity nextPackageToInsert = packages.remove(0);
            if (!insertIntoHierarchy(nextPackageToInsert, rootPackage)) {
                packages.add(nextPackageToInsert);
            }
        }
        return rootPackage;
    }

    /**
     *
     *
     * @param id
     * @return
     */
    public PackageEntity findById(long id) {
        return packageDAO.findById(id);
    }


    private PackageEntity initializeRootPackage(List<PackageEntity> packages) {
        PackageEntity rootPackage = new PackageEntity(0L, "Root PackageEntity");
        List<PackageEntity> rootChildPackages = packages.stream()
                .filter(pack -> pack.getParentPackageId() == null)
                .collect(Collectors.toList());
        rootChildPackages.forEach(childPackage -> {
            rootPackage.addChildPackage(childPackage);
            packages.remove(childPackage);
        });
        return rootPackage;
    }

    private Boolean insertIntoHierarchy(PackageEntity packageToInsert, PackageEntity hierarchy) {
        if (hierarchy.getId().equals(packageToInsert.getParentPackageId())) {
            hierarchy.addChildPackage(packageToInsert);
            return true;
        } else if (!hierarchy.getChildren().isEmpty()) {
            return hierarchy.getChildren().stream()
                    .map(childHierarchy -> insertIntoHierarchy(packageToInsert, childHierarchy))
                    .collect(Collectors.toList())
                    .contains(true);
        }
        return false;
    }
}
