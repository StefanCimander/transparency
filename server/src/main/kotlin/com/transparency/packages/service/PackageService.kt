package com.transparency.packages.service

import com.transparency.dependencies.transport.DependenciesRequest
import com.transparency.packages.PackageComponent
import com.transparency.packages.dao.PackageDAO
import com.transparency.packages.exception.HierarchyRootNotFoundException
import com.transparency.packages.exception.PackageNotFoundException
import com.transparency.packages.transport.PackageTO
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class PackageService: PackageComponent {

    @Autowired
    private lateinit var packageDAO: PackageDAO

    override fun findAll(): List<PackageTO> {
        return packageDAO.findAll().map(::PackageTO)
    }

    @Throws(PackageNotFoundException::class)
    override fun findById(id: Long): PackageTO {
        val packageEntity = packageDAO.findById(id)
        return PackageTO(packageEntity)
    }

    @Throws(HierarchyRootNotFoundException::class)
    fun findHierarchy(dependenciesRequest: DependenciesRequest): PackageTO {
        val allPackages = packageDAO.findAllWithFeatureDependencies(dependenciesRequest).map(::PackageTO)
        val rootPackage = findHierarchyRootIn(allPackages) ?: throw HierarchyRootNotFoundException()
        return buildHierarchyWithRoot(rootPackage, allPackages)
    }

    private fun findHierarchyRootIn(packages: List<PackageTO>): PackageTO? {
        return packages.firstOrNull { it.parentPackageId == null }
    }

    private fun buildHierarchyWithRoot(rootPackage: PackageTO, packages: List<PackageTO>): PackageTO {
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

    private fun packagesAfterInsertingInto(rootPackage: PackageTO, packages: ArrayList<PackageTO>): ArrayList<PackageTO> {
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
