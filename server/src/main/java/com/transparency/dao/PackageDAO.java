package com.transparency.dao;

import com.transparency.entity.PackageEntity;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * PackageEntity data access object (DAO).
 *
 * @author Stefan Cimander
 */
@Repository
public class PackageDAO {

    @Autowired
    private SessionFactory sessionFactory;

    /**
     * Finds all packages saved at the database.
     *
     * @param includeLogicalDependencies Whether to include the logical feature dependencies.
     * @return A list of packages.
     */
    @SuppressWarnings("unchecked")
    public List<PackageEntity> findAll(boolean includeLogicalDependencies) {
        Session session = sessionFactory.openSession();
        List<PackageEntity> packages = session.createCriteria(PackageEntity.class).list();
        packages.forEach(pack -> {
            Hibernate.initialize(pack.getFeatures());
            /*
            pack.getFeatures().forEach(feature -> {

                Hibernate.initialize(feature.getLinkedFeatures());
                if (includeLogicalDependencies) {
                    Hibernate.initialize(feature.getDependentFeatures());
                } else {
                    feature.setDependentFeatures(new HashSet<>());
                }
            });
            */
        });
        session.close();
        return packages;
    }

    /**
     * Finds a package by the given identifier.
     *
     * @param id The package identifier.
     * @return The package with the according identifier.
     */
    public PackageEntity findById(long id) {
        Session session = sessionFactory.openSession();
        PackageEntity pack = session.get(PackageEntity.class, id);
        Hibernate.initialize(pack.getFeatures());
        session.close();
        return pack;
    }
}
