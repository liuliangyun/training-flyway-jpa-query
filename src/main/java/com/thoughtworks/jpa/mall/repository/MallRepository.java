package com.thoughtworks.jpa.mall.repository;

import com.thoughtworks.jpa.mall.model.Commodity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MallRepository extends JpaRepository<Commodity, Integer> {

    List<Commodity> findByType(String type);

    List<Commodity> findByPriceBetween(double minPrice,double maxPrice);

    List<Commodity> findByTypeAndPriceBetween(String type, double minPrice, double maxPrice);

}
