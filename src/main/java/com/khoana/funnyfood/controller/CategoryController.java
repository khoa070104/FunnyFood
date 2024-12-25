package com.khoana.funnyfood.controller;

import com.khoana.funnyfood.payload.DataResponse;
import com.khoana.funnyfood.service.CategoryService;
import com.khoana.funnyfood.service.imp.CategoryServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*") // Setup for Client to access this API
@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    CategoryServiceImp categoryService;

    @GetMapping()
    public ResponseEntity<?> getHomePageCategory(){
        DataResponse data = new DataResponse();
        data.setData(categoryService.getListCategoryHomePage());
        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    @CacheEvict(value = "category", allEntries = true)
    @GetMapping("/cleancatche")
    public String clearCache(){
        return "Clear cache success";
    }
}
