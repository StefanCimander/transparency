package com.transparency.model

import org.junit.BeforeClass
import org.junit.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class PackageTests {

    companion object {

        private val package1 = Package(1, null, "Package 1")
        private val package2 = Package(2, 1L, "Package 2")
        private val package3 = Package(3, 1L, "Package 3")
        private val package4 = Package(4L, 2L, "Package 4")
        private val package5 = Package(5L, 4L, "Package 5")
        private val package6 = Package(6L, null, "Package 6")

        @BeforeClass
        @JvmStatic fun setupPackageChildHierarchy() {
            package1.addChildPackage(package2, package3);
            package2.addChildPackage(package4);
        }
    }

    @Test
    fun directChildCanBeInsertedIntoChildHierarchy() {
        assertTrue(package1.canAddPackageAsChild(package3))
        assertTrue(package2.canAddPackageAsChild(package4))
    }

    @Test
    fun indirectChildCanBeInsertedIntoChildHierarchy() {
        assertTrue(package1.canAddPackageAsChild(package4))
        assertTrue(package1.canAddPackageAsChild(package5))
        // assertTrue(package2.canAddPackageAsChild(package5))
    }

    @Test
    fun unrelatedPackagesCanNotBeInsertedIntoChildHierarchy() {
        assertFalse(package1.canAddPackageAsChild(package6))
    }

    @Test
    fun childrenNotAffectedByInsertingUnrelatedPackageIntoChildHierarchy() {
        val packageToInsert = Package(7L, null, "Package 7")
        package6.addChildPackage(packageToInsert)
        assertFalse(package6.childPackages.contains(packageToInsert))
    }

    @Test
    fun hasNewChildAfterInsertingIntoChildHierarchy() {
        val packageToInsert = Package(8L, 1L, "Package 8")
        package1.addChildPackage(packageToInsert)
        assertTrue(package1.childPackages.contains(packageToInsert))
    }

    @Test
    fun childHasNewChildAfterInsertingIntoChildHierarchy() {
        val packageToInsert = Package(9L, 2L, "Package 9")
        package1.addChildPackage(packageToInsert)
        assertTrue(package1.childPackages.any { it.childPackages.contains(packageToInsert) })
    }
}