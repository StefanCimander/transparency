package com.transparency.packages.entity

import com.transparency.features.entity.Feature
import javax.persistence.*

@Entity
@Table(name = "packages")
data class Package(@Id val id: Long, val name: String) {

    @ManyToOne
    @JoinColumn(name = "parent_package_id")
    var parentPackage: Package? = null

    @OneToMany(mappedBy = "parentPackage")
    lateinit var features: List<Feature>
}