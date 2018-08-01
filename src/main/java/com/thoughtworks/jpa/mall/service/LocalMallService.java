package com.thoughtworks.jpa.mall.service;

import com.thoughtworks.jpa.mall.model.Commodity;
import com.thoughtworks.jpa.mall.repository.MallRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
            selectedCommodity.get().setName(commodity.getName());
            selectedCommodity.get().setPrice(commodity.getPrice());
            selectedCommodity.get().setType(commodity.getType());
            selectedCommodity.get().setBand(commodity.getBand());
            selectedCommodity.get().setDescription(commodity.getDescription());
            selectedCommodity.get().setDate(commodity.getDate());
            selectedCommodity.get().setLocation(commodity.getLocation());
            mallRepository.save(selectedCommodity.get());
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
        return mallRepository.findByTypeAndPriceBetween(type,minPrice,maxPrice);
    }

    @Override
    public List<Commodity> listAllPages(int pages, int size) {
        Pageable pageable = PageRequest.of(pages, size);
        //Pageable pageable = new PageRequest(pages, size, Sort.Direction.ASC, "id");
        return mallRepository.findAll(pageable).getContent();
    }

    @Override
    public List<Commodity> listAllPagesAndSort(int pages, int size) {
        Sort sort = new Sort(Sort.Direction.ASC, "price");
        Pageable pageable = PageRequest.of(pages, size, sort);
        //Pageable pageable = new PageRequest(pages, size, sort);
        return mallRepository.findAll(pageable).getContent();
    }

}
