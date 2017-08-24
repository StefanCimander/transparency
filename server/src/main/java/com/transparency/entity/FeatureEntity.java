package com.transparency.entity;

import javax.persistence.*;

@Entity
@Table(name = "features")
public class FeatureEntity {

    private long id;
    private String name;
    private PackageEntity parentPackage;
    // private Set<FeatureEntity> linkedFeatures;
    // private Set<FeatureEntity> dependentFeatures;
    // private Set<LogicalFunctionEntity> logicalFunctions;

    public FeatureEntity(long id, String name) {
        this.id = id;
        this.name = name;
    }

    protected FeatureEntity() {
    }

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ManyToOne
    @JoinColumn(name="package_id")
    public PackageEntity getParentPackage() {
        return parentPackage;
    }

    public void setParentPackage(PackageEntity parentPackage) {
        this.parentPackage = parentPackage;
    }

//    @OneToMany
//    @JoinTable(
//            name = "feature_links",
//            joinColumns = @JoinColumn(name = "linking_feature_id", referencedColumnName = "id"),
//            inverseJoinColumns = @JoinColumn(name = "linked_feature_id", referencedColumnName = "id")
//    )
//    public Set<FeatureEntity> getLinkedFeatures() {
//        return linkedFeatures;
//    }
//
//    public void setLinkedFeatures(Set<FeatureEntity> linkedFeatures) {
//        this.linkedFeatures = linkedFeatures;
//    }
//
//    @OneToMany
//    @JoinTable(
//            name = "logical_dependencies",
//            joinColumns = @JoinColumn(name = "source_feature_id", referencedColumnName = "id"),
//            inverseJoinColumns = @JoinColumn(name = "target_feature_id", referencedColumnName = "id")
//    )
//    public Set<FeatureEntity> getDependentFeatures() {
//        return dependentFeatures;
//    }
//
//    public void setDependentFeatures(Set<FeatureEntity> dependentFeatures) {
//        this.dependentFeatures = dependentFeatures;
//    }
//
//    @JsonIgnore
//    @ManyToMany
//    @JoinTable(
//            name = "feature_function_mappings",
//            joinColumns = @JoinColumn(name = "feature_id", referencedColumnName = "id"),
//            inverseJoinColumns = @JoinColumn(name = "logical_function_id", referencedColumnName = "id")
//    )
    // public Set<LogicalFunctionEntity> getLogicalFunctions() {
    //   return logicalFunctions;
    // }

    // public void setLogicalFunctions(Set<LogicalFunctionEntity> logicalFunctions) {
    //        this.logicalFunctions = logicalFunctions;
    // }


    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null || getClass() != other.getClass()) {
            return false;
        }

        FeatureEntity feature = (FeatureEntity) other;
        return id == feature.id && name.equals(feature.name);
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + name.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "FeatureEntity{id=" + id + ", name=" + name + "}";
    }
}
