package com.khoana.funnyfood.controller;

import com.khoana.funnyfood.payload.DataResponse;
import com.khoana.funnyfood.payload.request.RestaurantCreateRequest;
import com.khoana.funnyfood.service.RestaurantService;
import com.khoana.funnyfood.service.imp.FileServiceImp;
import com.khoana.funnyfood.service.imp.RestaurantServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/restaurant")
public class RestaurantController {
    @Autowired
    FileServiceImp fileService;
    @Autowired
    RestaurantServiceImp restaurantService;


    @PostMapping()
    public ResponseEntity<?> createRestaurant(@RequestParam MultipartFile file,
                                              @RequestParam String title ,
                                              @RequestParam String subtitle,
                                              @RequestParam String descript,
                                              @RequestParam boolean isfreeship,
                                              @RequestParam String address,
                                              @RequestParam String dateOpen){
        DataResponse data = new DataResponse();
        RestaurantCreateRequest request = RestaurantCreateRequest.builder()
                .title(title)
                .subtitle(subtitle)
                .descript(descript)
                .isfreeship(isfreeship)
                .address(address)
                .dateOpen(dateOpen)
                .build();
        boolean check = restaurantService.insertRestaurant(request, file);
        data.setData(check);
        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    @GetMapping("/file/{filename:.+}")
    public ResponseEntity<?> getFileRestaurant(@PathVariable String filename){
        Resource resource = fileService.loadFile(filename);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }

}
