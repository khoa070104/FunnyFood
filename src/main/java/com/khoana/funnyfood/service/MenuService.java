package com.khoana.funnyfood.service;

import com.khoana.funnyfood.entity.Category;
import com.khoana.funnyfood.entity.Food;
import com.khoana.funnyfood.entity.MenuRestaurant;
import com.khoana.funnyfood.entity.Restaurant;
import com.khoana.funnyfood.payload.request.MenuCreateRequest;
import com.khoana.funnyfood.repository.FoodRepository;
import com.khoana.funnyfood.service.imp.FileServiceImp;
import com.khoana.funnyfood.service.imp.MenuServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class MenuService implements MenuServiceImp {
    @Autowired
    private FileServiceImp fileServiceImp;

    @Autowired
    private FoodRepository foodRepository;
    /*
    * String title;
    String timeShip;
    String address;
    String dateOpen;
    String image;
    *
    * */
    @Override
    public boolean createMenu(MenuCreateRequest request, MultipartFile file) {
        boolean isSuccess = false;
        try {
            boolean check = fileServiceImp.saveFile(file);
            if (check) {
                Food food = new Food();
                food.setTitle(request.getTitle());
                food.setPrice(request.getPrice());
                food.setTimeShip(request.getTimeShip());
                food.setImage(request.getImage());
                Category category = new Category();
                category.setId(request.getCategoryId());
                food.setCategory(category);
                foodRepository.save(food);
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
        return isSuccess;
    }
}
