package com.transparency.service

import com.transparency.dao.PackageDAO
import com.transparency.exception.HierarchyRootNotFoundException
import com.transparency.exception.PackageNotFoundException
import com.transparency.model.Package
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class PackageService {

    @Autowired
    private lateinit var packageDAO: PackageDAO

    fun findAll(): List<Package> {
        return packageDAO.findAll().map(::Package)
    }

    @Throws(PackageNotFoundException::class)
    fun findById(id: Long): Package {
        val packageEntity = packageDAO.findById(id)
        return Package(packageEntity)
    }

    @Throws(HierarchyRootNotFoundException::class)
    fun findHierarchy(): Package {
        val allPackages = packageDAO.findAllWithFeatureDependencies().map(::Package)
        val rootPackage = findHierarchyRootIn(allPackages) ?: throw HierarchyRootNotFoundException()
        return buildHierarchyWithRoot(rootPackage, allPackages)
    }

    private fun findHierarchyRootIn(packages: List<Package>): Package? {
        return packages.firstOrNull { it.parentPackageId == null }
    }

    private fun buildHierarchyWithRoot(rootPackage: Package, packages: List<Package>): Package {
        var remainingPackages = ArrayList(packages)
        remainingPackages.remove(rootPackage)
        while (!remainingPackages.isEmpty()) {
            val sizeBeforeInserting = packages.size
            remainingPackages = packagesAfterInsertingInto(rootPackage, remainingPackages)
            if (remainingPackages.size >= sizeBeforeInserting) {
                break
            }
        }
        return rootPackage
    }

    private fun packagesAfterInsertingInto(rootPackage: Package, packages: ArrayList<Package>): ArrayList<Package> {
        val packageToInsert = packages[0]
        if (rootPackage.canAddPackageAsChild(packageToInsert)) {
            rootPackage.addChildPackage(packageToInsert)
        } else {
            packages.add(packageToInsert)
        }
        packages.removeAt(0)
        return packages
    }
}
