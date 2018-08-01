package com.thoughtworks.jpa.mall.service;

import com.thoughtworks.jpa.mall.entity.Commodity;
import com.thoughtworks.jpa.mall.repository.MallRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class LocalMallService implements MallService{

    @Autowired
    private MallRepository mallRepository;

    @Override
    public List<Commodity> listAll() {
        List<Commodity> list = new ArrayList<>();
        List<Commodity> findList = mallRepository.findAll();
        for(int i=0;i<findList.size();i++){
            Commodity findCommodity = findList.get(i);
            Commodity commodity= new Commodity(findCommodity.getName(),findCommodity.getPrice(),findCommodity.getType(),findCommodity.getBand());
            list.add(commodity);
        }
        return list;
        //return mallRepository.findAll();
    }

    @Override
    public Commodity findOne(int id) {
        Optional<Commodity> selectedCommodity = mallRepository.findById(id);
        if(selectedCommodity.isPresent()){
            return selectedCommodity.get();
        }
        return null;
    }

    @Override
    public void addOne(Commodity commodity) {
        mallRepository.save(commodity);
    }

    @Override
    public void deleteOne(int id) {
        Optional<Commodity> selectedCommodity = mallRepository.findById(id);
        if(selectedCommodity.isPresent()){
            mallRepository.deleteById(id);
        }
    }

    @Override
    public void updateOne(int id, Commodity commodity) {
        Optional<Commodity> selectedCommodity = mallRepository.findById(id);
        if(selectedCommodity.isPresent()){
            Commodity commodity1 = selectedCommodity.get();
            commodity1.setName(commodity.getName());
            commodity1.setPrice(commodity.getPrice());
            commodity1.setType(commodity.getType());
            commodity1.setBand(commodity.getBand());
            commodity1.setDescription(commodity.getDescription());
            commodity1.setDate(commodity.getDate());
            commodity1.setLocation(commodity.getLocation());
            mallRepository.save(commodity1);
        }
    }

    @Override
    public List<Commodity> filterByType(String type) {
        return mallRepository.findByType(type);
    }

    @Override
    public List<Commodity> filterByPrice(double minPrice, double maxPrice) {
        return mallRepository.findByPriceBetween(minPrice,maxPrice);
    }

    @Override
    public List<Commodity> filterByTypeAndPrice(String type, double minPrice, double maxPrice) {
        //return mallRepository.findByTypeAndPriceBetween(type,minPrice,maxPrice);
        return mallRepository.filter(type,minPrice,maxPrice);
    }

    @Override
    public List<Commodity> listAllPages(int pageNum, int pageSize) {
        PageRequest page = PageRequest.of(pageNum, pageSize);
        return mallRepository.findAll(page).getContent();
    }

    @Override
    public List<Commodity> listAllSortedPages(int pageNum, int pageSize, String order) {
        Sort sort = new Sort(order.equals("ASC") ? Sort.Direction.ASC : Sort.Direction.DESC, "price");
        PageRequest page = PageRequest.of(pageNum, pageSize, sort);
        return mallRepository.findAll(page).getContent();
    }

}
