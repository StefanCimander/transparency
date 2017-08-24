package com.transparency.dao;

import com.transparency.entity.FeatureEntity;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FeatureDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @SuppressWarnings("unchecked")
    public List<FeatureEntity> findAll() {
        Session session = sessionFactory.openSession();
        List<FeatureEntity> features = session.createCriteria(FeatureEntity.class).list();
        features.forEach(feature -> {
            Hibernate.initialize(feature.getParentPackage());
        });
        session.close();
        return features;
    }
}
