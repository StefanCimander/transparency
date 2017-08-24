package com.transparency.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "packages")
public class PackageEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    private String name;

    @JsonIgnore
    @Column(name = "parent_package_id")
    private Long parentPackageId;

    @OneToMany(mappedBy = "parentPackage")
    private List<FeatureEntity> features = new ArrayList<>();

    @Transient
    private List<PackageEntity> children = new ArrayList<>();

    protected PackageEntity() {
    }

    public PackageEntity(final Long id, final String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Long getParentPackageId() {
        return parentPackageId;
    }

    public List<PackageEntity> getChildren() {
        return children;
    }

    public void addChildPackage(PackageEntity childPackage) {
        children.add(childPackage);
    }

    public List<FeatureEntity> getFeatures() {
        return features;
    }
}
