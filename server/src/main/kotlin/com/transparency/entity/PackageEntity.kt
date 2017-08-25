package com.transparency.entity

import java.util.ArrayList
import javax.persistence.*

@Entity
@Table(name = "packages")
data class PackageEntity(@Id val id: Long, val name: String) {

    @ManyToOne
    @JoinColumn(name = "parent_package_id")
    val parentPackage: PackageEntity? = null

    @OneToMany(mappedBy = "parentPackage")
    val features: List<FeatureEntity> = ArrayList()

    @Transient
    var children: List<PackageEntity> = ArrayList()
}