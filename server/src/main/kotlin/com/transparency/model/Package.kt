package com.transparency.model

import com.fasterxml.jackson.annotation.JsonIgnore
import com.transparency.entity.PackageEntity

class Package(val id: Long, val name: String) {
    @JsonIgnore
    var parentPackage: Package? = null
    val childPackages: MutableCollection<Package> = HashSet()

    constructor(entity: PackageEntity): this(entity.id, entity.name) {
        if (entity.parentPackage != null) {
            parentPackage = Package(entity.parentPackage)
        }
        if (entity.children != null) {
            childPackages.addAll(entity.children.map(::Package))
        }
    }

    constructor(id: Long, name: String, parentPackage: Package?): this(id, name) {
        this.parentPackage = parentPackage
    }

    fun canAddPackageAsChild(packageToInsert: Package): Boolean {
        return packageToInsert.parentPackage?.id == id
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
        if (childPackage.parentPackage?.id == id) {
            childPackages.add(childPackage)
        } else {
            childPackages.forEach { it.addChildPackage(childPackage) }
        }
    }
}