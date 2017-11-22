package com.transparency.features.entity

import com.transparency.logical.entitiy.LogicalFunctionEntity
import com.transparency.packages.entity.Package
import javax.persistence.*

@Entity
@Table(name = "features")
data class Feature(@Id val id: Long, val name: String) {

    @ManyToOne
    @JoinColumn(name = "package_id")
    lateinit var parentPackage: Package

    @OneToMany
    @JoinTable(
            name = "feature_links",
            joinColumns = arrayOf(JoinColumn(name = "linking_feature_id", referencedColumnName = "id")),
            inverseJoinColumns = arrayOf(JoinColumn(name = "linked_feature_id", referencedColumnName = "id")))
    lateinit var linkedFeatures: List<Feature>

    @OneToMany
    @JoinTable(
            name = "logical_dependencies",
            joinColumns = arrayOf(JoinColumn(name = "dependent_feature_id", referencedColumnName = "id")),
            inverseJoinColumns = arrayOf(JoinColumn(name = "target_feature_id", referencedColumnName = "id")))
    lateinit var logicallyDependentFeatures: MutableList<Feature>

    @OneToMany
    @JoinTable(
            name = "feature_function_mappings",
            joinColumns = arrayOf(JoinColumn(name = "feature_id", referencedColumnName = "id")),
            inverseJoinColumns = arrayOf(JoinColumn(name = "logical_function_id", referencedColumnName = "id")))
    lateinit var logicalFunctions: List<LogicalFunctionEntity>

    val hasLogicalDependencies: Boolean
        get() = !logicallyDependentFeatures.isEmpty()

    override fun equals(other: Any?): Boolean {
        if (other == null || other !is Feature) {
            return false
        }
        return id == other.id
    }
}
