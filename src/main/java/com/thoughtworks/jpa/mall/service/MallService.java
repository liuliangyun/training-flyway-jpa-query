package com.thoughtworks.jpa.mall.service;

import com.thoughtworks.jpa.mall.model.Commodity;
import org.springframework.data.domain.Page;

import java.util.List;

public interface MallService {

    List<Commodity> listAll();

    Commodity findOne(int id);

    void addOne(Commodity commodity);

    void deleteOne(int id);

    void updateOne(int id, Commodity commodity);

    List<Commodity> filterByType(String type);

    List<Commodity> filterByPrice(double minPrice,double maxPrice);

    List<Commodity> filterByTypeAndPrice(String type, double minPrice, double maxPrice);

    List<Commodity> listAllPages(int pageNum, int pageSize);

    List<Commodity> listAllSortedPages(int pageNum, int pageSize, String order);
}
