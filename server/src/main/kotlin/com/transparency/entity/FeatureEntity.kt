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

    @OneToMany
    @JoinTable(
            name = "logical_dependencies",
            joinColumns = arrayOf(JoinColumn(name = "dependent_feature_id", referencedColumnName = "id")),
            inverseJoinColumns = arrayOf(JoinColumn(name = "target_feature_id", referencedColumnName = "id")))
    lateinit var logicallyDependentFeatures: MutableList<FeatureEntity>

    @OneToMany
    @JoinTable(
            name = "feature_function_mappings",
            joinColumns = arrayOf(JoinColumn(name = "feature_id", referencedColumnName = "id")),
            inverseJoinColumns = arrayOf(JoinColumn(name = "logical_function_id", referencedColumnName = "id")))
    lateinit var logicalFunctions: List<LogicalFunctionEntity>

    override fun equals(other: Any?): Boolean {
        if (other == null || other !is FeatureEntity) {
            return false
        }
        return id == other.id
    }
}
