package com.transparency.entity

import javax.persistence.*

@Entity
@Table(name = "features")
data class FeatureEntity(@Id val id: Long, val name: String) {

    @ManyToOne
    @JoinColumn(name = "package_id")
    lateinit var parentPackage: PackageEntity;

    @OneToMany
    @JoinTable(
            name = "feature_links",
            joinColumns = arrayOf(JoinColumn(name = "linking_feature_id", referencedColumnName = "id")),
            inverseJoinColumns = arrayOf(JoinColumn(name = "linked_feature_id", referencedColumnName = "id")))
    lateinit var linkedFeatures: List<FeatureEntity>
}
