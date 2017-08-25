package com.transparency.entity;

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

    @ManyToOne
    @JoinColumn(name="parent_package_id")
    private PackageEntity parentPackage;

    @OneToMany(mappedBy = "parentPackage")
    private List<FeatureEntity> features = new ArrayList<>();

    @Transient
    private List<PackageEntity> children = new ArrayList<>();

    protected PackageEntity() {
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public PackageEntity getParentPackage() {
        return parentPackage;
    }

    public List<PackageEntity> getChildren() {
        return children;
    }

    public List<FeatureEntity> getFeatures() {
        return features;
    }
}
