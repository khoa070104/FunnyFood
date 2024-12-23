package com.khoana.funnyfood.service;

import com.khoana.funnyfood.dto.CategoryDTO;
import com.khoana.funnyfood.dto.MenuDTO;
import com.khoana.funnyfood.dto.RestaurantDTO;
import com.khoana.funnyfood.entity.Category;
import com.khoana.funnyfood.entity.RatingRestaurant;
import com.khoana.funnyfood.entity.Restaurant;
import com.khoana.funnyfood.payload.request.RestaurantCreateRequest;
import com.khoana.funnyfood.repository.RatingRestaurantRepository;
import com.khoana.funnyfood.repository.RestaurantRepository;
import com.khoana.funnyfood.service.imp.FileServiceImp;
import com.khoana.funnyfood.service.imp.RestaurantServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 
 */
@Service
public class RestaurantService implements RestaurantServiceImp {
    @Autowired
    private RestaurantRepository restaurantRepository;
    @Autowired
    private RatingRestaurantRepository ratingRestaurantRepository;
    
    @Autowired
    private FileServiceImp fileServiceImp;

    @Override
    public boolean insertRestaurant(RestaurantCreateRequest restaurant, MultipartFile file) {
        boolean isSuccess = false;
        try {
            boolean check = fileServiceImp.saveFile(file);
            if (check) {
                Restaurant res = new Restaurant();
                res.setTitle(restaurant.getTitle());
                res.setSubtitle(restaurant.getSubtitle());
                res.setAddress(restaurant.getAddress());
                res.setDescription(restaurant.getDescript());
                res.setIsFreeShip(restaurant.isIsfreeship());
                
                // Handle date parsing with null check and proper format
                String dateOpen = restaurant.getDateOpen();
                if (dateOpen != null && !dateOpen.trim().isEmpty()) {
                    try {
                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                        dateFormat.setLenient(false); // Strict date parsing
                        Date date = dateFormat.parse(dateOpen.trim());
                        res.setOpenDate(date);
                    } catch (Exception e) {
                        throw new IllegalArgumentException("Invalid date format. Please use yyyy-MM-dd format (e.g., 2024-11-11)");
                    }
                } else {
                    // Set current date as default if no date provided
                    res.setOpenDate(new Date());
                }
                
                res.setImage(file.getOriginalFilename());
                
                // Save the restaurant using the save method
                restaurantRepository.save(res);
                isSuccess = true;
            }
        } catch (Exception e) {
            e.printStackTrace(); 
            throw new RuntimeException(e.getMessage());
        }
        return isSuccess;
    }

    @Override
    public List<RestaurantDTO> getHomePageRestaurant() {
        List<RestaurantDTO> listRes = new ArrayList<>();
        PageRequest pageRequest = PageRequest.of(0, 6);
        var list = restaurantRepository.findAll(pageRequest);
        list.stream().forEach(restaurant -> {
            RestaurantDTO res = new RestaurantDTO();
            res.setTitle(restaurant.getTitle());
            res.setSubtitle(restaurant.getSubtitle());
            res.setFreeShip(restaurant.getIsFreeShip());
            res.setImage(restaurant.getImage());
            res.setRatting(calculateRating(ratingRestaurantRepository.findByRestaurantId(restaurant.getId())));
            listRes.add(res);
        });
        return listRes;
    }



    private Double calculateRating(List<RatingRestaurant> listRating) {
        if (listRating != null && !listRating.isEmpty()) {
            double sum = listRating.stream()
                    .mapToDouble(RatingRestaurant::getRatePoint)
                    .sum();
            return sum / listRating.size();
        }
        return 0.0;
    }

    @Override
    public RestaurantDTO getDetailRestaurant(int id) {
        Optional<Restaurant> res = restaurantRepository.findById(id);
        RestaurantDTO restaurantDTO = new RestaurantDTO();
        if(res.isPresent()){
            List<CategoryDTO> listCategory = new ArrayList<>();
            restaurantDTO.setTitle(res.get().getTitle());
            restaurantDTO.setSubtitle(res.get().getSubtitle());
            restaurantDTO.setFreeShip(res.get().getIsFreeShip());
            restaurantDTO.setImage(res.get().getImage());
            restaurantDTO.setRatting(calculateRating(ratingRestaurantRepository.findByRestaurantId(res.get().getId())));
            for (var menu : res.get().getMenus()) {
                CategoryDTO categoryDTO = new CategoryDTO();
                List<MenuDTO> listMenu = new ArrayList<>();
                Category cate = menu.getCategory();
                categoryDTO.setName(cate.getNameCate());
                for (var food : cate.getFoods()) {
                    MenuDTO menuDTO = new MenuDTO();
                    menuDTO.setImage(food.getImage());
                    menuDTO.setPrice(food.getPrice());
                    menuDTO.setTitle(food.getTitle());
                    listMenu.add(menuDTO);
                }
                categoryDTO.setMenus(listMenu); // Set the menus to categoryDTO
                listCategory.add(categoryDTO);
            }
            restaurantDTO.setCategories(listCategory); // Set the categories to restaurantDTO
        }
        return restaurantDTO;
    }
}
