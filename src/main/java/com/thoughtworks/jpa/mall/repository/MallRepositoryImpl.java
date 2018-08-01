package com.thoughtworks.jpa.mall.repository;

import com.thoughtworks.jpa.mall.entity.Commodity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class MallRepositoryImpl implements MallRepositoryCustom{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Commodity> filter(String type,Double minPrice, Double maxPrice){
        final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        final CriteriaQuery<Commodity> query = criteriaBuilder.createQuery(Commodity.class);
        final Root<Commodity> commodityRoot = query.from(Commodity.class);

        Predicate predicate = criteriaBuilder.conjunction();
        if(type != null && !type.isEmpty()){
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(commodityRoot.get("type"),type));
        }
        if(maxPrice!=null){
            predicate = criteriaBuilder.and(predicate,criteriaBuilder.lessThan(commodityRoot.get("price"),maxPrice));
        }
        if(minPrice!=null){
            predicate = criteriaBuilder.and(predicate,criteriaBuilder.greaterThan(commodityRoot.get("price"),minPrice));
        }

        query.where(predicate);
        List<Commodity> result = entityManager.createQuery(query).getResultList();
        return result;
    }
}
