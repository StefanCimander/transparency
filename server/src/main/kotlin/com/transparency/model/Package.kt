package com.transparency.model

import com.transparency.entity.PackageEntity

class Package(val id: Long, val parentPackageId: Long?, val name: String) {
    val childPackages: MutableList<Package> = ArrayList()
    val features: MutableList<Feature> = ArrayList()

    constructor(entity: PackageEntity): this(entity.id, entity.parentPackage?.id, entity.name) {
        if (entity.features != null) {
            entity.features.forEach { addFeature(Feature(it)) }
        }
    }

    fun canAddPackageAsChild(packageToInsert: Package): Boolean {
        return packageToInsert.parentPackageId == id
                || canChildInsertPackageIntoChildHierarchy(packageToInsert)
    }

    private fun canChildInsertPackageIntoChildHierarchy(packageToInsert: Package): Boolean {
        return childPackages.stream().anyMatch({ it.canAddPackageAsChild(packageToInsert) })
    }

    fun addChildPackage(vararg childPackages: Package) {
        childPackages.forEach {
            if (canAddPackageAsChild(it)) {
                insertPackageIntoChildHierarchy(it)
            }
        }
    }

    private fun insertPackageIntoChildHierarchy(childPackage: Package) {
        if (childPackage.parentPackageId == id) {
            childPackages.add(childPackage)
        } else {
            childPackages.forEach { it.addChildPackage(childPackage) }
        }
    }

    private fun addFeature(vararg features: Feature) {
        features.forEach {
            if (it.parentPackageId == id) {
                this.features.add(it);
            }
        }
    }
}