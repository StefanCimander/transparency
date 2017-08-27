package com.transparency.model;

import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class PackageTests {

    private static Package package1 = new Package(1, null, "Package 1");
    private static Package package2 = new Package(2, 1L, "Package 2");
    private static Package package3 = new Package(3, 1L, "Package 3");
    private static Package package4 = new Package(4L, 2L, "Package 4");
    private static Package package5 = new Package(5L, 4L, "Package 5");
    private static Package package6 = new Package(6L, null, "Package 6");

    @BeforeClass
    public static void setupPackageChildHierarchy() {
        package1.addChildPackage(package2, package3);
        package2.addChildPackage(package4);
    }

    @Test
    public void directChildCanBeInsertedIntoChildHierarchy() {
        assertTrue(package1.canAddPackageAsChild(package3));
        assertTrue(package2.canAddPackageAsChild(package4));
    }

    @Test
    public void indirectChildCanBeInsertedIntoChildHierarchy() {
        assertTrue(package1.canAddPackageAsChild(package4));
        assertTrue(package1.canAddPackageAsChild(package5));
        assertTrue(package2.canAddPackageAsChild(package5));
    }

    @Test
    public void unrelatedPackagesCanNotBeInsertedIntoChildHierarchy() {
        assertFalse(package1.canAddPackageAsChild(package6));
    }

    @Test
    public void childrenNotAffectedByInsertingUnrelatedPackageIntoChildHierarchy() {
        Package packageToInsert = new Package(7L, null, "Package 7");
        package6.addChildPackage(packageToInsert);
        assertFalse(package6.getChildPackages().contains(packageToInsert));
    }

    @Test
    public void hasNewChildAfterInsertingIntoChildHierarchy() {
        Package packageToInsert = new Package(8L, 1L, "Package 8");
        package1.addChildPackage(packageToInsert);
        assertTrue(package1.getChildPackages().contains(packageToInsert));
    }

    @Test
    public void childHasNewChildAfterInsertingIntoChildHierarchy() {
        Package packageToInsert = new Package(9L, 2L, "Package 9");
        package1.addChildPackage(packageToInsert);
        assertTrue(package1.getChildPackages().stream().anyMatch(child ->
                child.getChildPackages().contains(packageToInsert))
        );
    }
}
