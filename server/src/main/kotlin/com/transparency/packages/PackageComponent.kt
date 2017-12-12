package com.transparency.packages

import com.transparency.packages.exception.PackageNotFoundException
import com.transparency.packages.transport.PackageTO

interface PackageComponent {

    /**
     * Finds all packages.
     *
     * @return a list of all packages.
     */
    fun findAll(): List<PackageTO>

    /**
     * Finds the package with a given id.
     *
     * @param id the package identifier.
     * @return the package with the according id.
     * @throws exception if there is no package with such id.
     */
    @Throws(PackageNotFoundException::class)
    fun findById(id: Long): PackageTO
}