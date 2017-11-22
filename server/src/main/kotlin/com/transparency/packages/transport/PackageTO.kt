package com.transparency.packages.transport

import com.transparency.packages.entity.Package
import com.transparency.features.transport.FeatureTO

class PackageTO(val id: Long, val parentPackageId: Long?, val name: String) {
    val childPackages: MutableList<PackageTO> = ArrayList()
    val features: MutableList<FeatureTO> = ArrayList()

    constructor(entity: Package): this(entity.id, entity.parentPackage?.id, entity.name) {
        if (entity.features != null) {
            entity.features.forEach { addFeature(FeatureTO(it)) }
        }
    }

    fun canAddPackageAsChild(packageToInsert: PackageTO): Boolean {
        return packageToInsert.parentPackageId == id
                || canChildInsertPackageIntoChildHierarchy(packageToInsert)
    }

    private fun canChildInsertPackageIntoChildHierarchy(packageToInsert: PackageTO): Boolean {
        return childPackages.stream().anyMatch({ it.canAddPackageAsChild(packageToInsert) })
    }

    fun addChildPackage(vararg childPackages: PackageTO) {
        childPackages.forEach {
            if (canAddPackageAsChild(it)) {
                insertPackageIntoChildHierarchy(it)
            }
        }
    }

    private fun insertPackageIntoChildHierarchy(childPackage: PackageTO) {
        if (childPackage.parentPackageId == id) {
            childPackages.add(childPackage)
        } else {
            childPackages.forEach { it.addChildPackage(childPackage) }
        }
    }

    private fun addFeature(vararg features: FeatureTO) {
        features.forEach {
            if (it.parentPackageId == id) {
                this.features.add(it);
            }
        }
    }
}