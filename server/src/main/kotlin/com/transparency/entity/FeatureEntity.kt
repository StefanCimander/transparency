package com.transparency.entity

import javax.persistence.*

@Entity
@Table(name = "features")
data class FeatureEntity(@Id val id: Long, val name: String) {

    @ManyToOne
    @JoinColumn(name = "package_id")
    lateinit var parentPackage: PackageEntity;
}
