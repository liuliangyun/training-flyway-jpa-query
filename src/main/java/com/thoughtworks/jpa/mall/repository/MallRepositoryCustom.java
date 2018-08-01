package com.thoughtworks.jpa.mall.repository;

import com.thoughtworks.jpa.mall.entity.Commodity;

import java.util.List;

public interface MallRepositoryCustom {

    List<Commodity> filter(String type, Double minPrice, Double maxPrice);
}
