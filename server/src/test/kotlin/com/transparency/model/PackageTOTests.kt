package com.transparency.model

import com.transparency.packages.transport.PackageTO
import org.junit.BeforeClass
import org.junit.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class PackageTOTests {

    companion object {
        private val package1 = PackageTO(1, null, "PackageTO 1")
        private val package2 = PackageTO(2, 1L, "PackageTO 2")
        private val package3 = PackageTO(3, 1L, "PackageTO 3")
        private val package4 = PackageTO(4L, 2L, "PackageTO 4")
        private val package5 = PackageTO(5L, 4L, "PackageTO 5")
        private val package6 = PackageTO(6L, null, "PackageTO 6")

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
        assertTrue(package2.canAddPackageAsChild(package5))
    }

    @Test
    fun unrelatedPackagesCanNotBeInsertedIntoChildHierarchy() {
        assertFalse(package1.canAddPackageAsChild(package6))
    }

    @Test
    fun childrenNotAffectedByInsertingUnrelatedPackageIntoChildHierarchy() {
        val packageToInsert = PackageTO(7L, null, "PackageTO 7")
        package6.addChildPackage(packageToInsert)
        assertFalse(package6.childPackages.contains(packageToInsert))
    }

    @Test
    fun hasNewChildAfterInsertingIntoChildHierarchy() {
        val packageToInsert = PackageTO(8L, 1L, "PackageTO 8")
        package1.addChildPackage(packageToInsert)
        assertTrue(package1.childPackages.contains(packageToInsert))
    }

    @Test
    fun childHasNewChildAfterInsertingIntoChildHierarchy() {
        val packageToInsert = PackageTO(9L, 2L, "PackageTO 9")
        package1.addChildPackage(packageToInsert)
        assertTrue(package1.childPackages.any { it.childPackages.contains(packageToInsert) })
    }
}