package com.thoughtworks.jpa.mall.controller;

import com.thoughtworks.jpa.mall.model.Commodity;
import com.thoughtworks.jpa.mall.service.LocalMallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class MallController {

    @Autowired
    private LocalMallService localMallService;

    @GetMapping("/commodities")
    public List<Commodity> listAll() {
        return localMallService.listAll();
    }

    @GetMapping("/commodities/{id}")
    public Commodity findOne(@PathVariable int id) {
        return localMallService.findOne(id);
    }

    @PostMapping("/commodities")
    public void addOne(@RequestBody Commodity commodity) {
        localMallService.addOne(commodity);
    }

    @DeleteMapping("/commodities/{id}")
    public void deleteOne(@PathVariable int id) {
        localMallService.deleteOne(id);
    }

    @PutMapping("/commodities/{id}")
    public void updateOne(@PathVariable int id, @RequestBody Commodity commodity) {
        localMallService.updateOne(id,commodity);
    }

    @GetMapping("/commodities/filter/type")
    public List<Commodity> filterByType(@RequestParam String type) {
        return localMallService.filterByType(type);
    }

    @GetMapping("/commodities/filter/price")
    public List<Commodity> filterByPrice(@RequestParam double minPrice, double maxPrice){
        return localMallService.filterByPrice(minPrice,maxPrice);
    }

    @GetMapping("/commodities/filter/type/price")
    public List<Commodity> filterByTypeAndPrice(@RequestParam String type, double minPrice, double maxPrice){
        return localMallService.filterByTypeAndPrice(type,minPrice,maxPrice);
    }


    @GetMapping("/commodities/page")
    public List<Commodity> listAllPages(@RequestParam(value = "pageNum", defaultValue = "0") int pageNum, @RequestParam(value = "pageSize", required = false) int pageSize) {
        return localMallService.listAllPages(pageNum,pageSize);
    }

    @GetMapping("/commodities/page/sort")
    public List<Commodity> listAllSortedPages(@RequestParam(value = "pageNum", defaultValue = "0") int pageNum, @RequestParam(value = "pageSize", required = false) int pageSize, @RequestParam(value = "order") String order) {
        return localMallService.listAllSortedPages(pageNum,pageSize,order);
    }


}
