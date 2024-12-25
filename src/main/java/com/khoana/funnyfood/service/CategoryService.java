package com.khoana.funnyfood.service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.khoana.funnyfood.dto.CategoryDTO;
import com.khoana.funnyfood.dto.MenuDTO;
import com.khoana.funnyfood.entity.Category;
import com.khoana.funnyfood.repository.CategoryRepository;
import com.khoana.funnyfood.service.imp.CategoryServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService implements CategoryServiceImp {
    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    RedisTemplate redisTemplate;

    Gson gson = new Gson();

    @Override
    public List<CategoryDTO> getListCategoryHomePage() {
        PageRequest pageRequest = PageRequest.of(0, 2);
        String redisData = (String) redisTemplate.opsForValue().get("category");
        List<CategoryDTO> list = new ArrayList<>();

        if(redisData == null) {
            List<Category> listCategory = categoryRepository.findAll(pageRequest).getContent();

            list = listCategory.stream()
                    .map(category -> {
                        CategoryDTO categoryDTO = new CategoryDTO();
                        categoryDTO.setName(category.getNameCate());

                        List<MenuDTO> listMenus = new ArrayList<>(category.getFoods().size());
                        for (var menu : category.getFoods()) {
                            listMenus.add(MenuDTO.builder()
                                    .image(menu.getImage())
                                    .price(menu.getPrice())
                                    .title(menu.getTitle())
                                    .build());
                        }

                        categoryDTO.setMenus(listMenus);
                        return categoryDTO;
                    })
                    .collect(Collectors.toList());
            String json = gson.toJson(list);
            redisTemplate.opsForValue().set("category", json);
        }else{
            Type type = new TypeToken<List<CategoryDTO>>(){}.getType();
            list = gson.fromJson(redisData, type);
        }

        return list;
    }
}
