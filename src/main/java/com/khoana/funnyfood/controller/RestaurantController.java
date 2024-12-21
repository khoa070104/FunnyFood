package com.khoana.funnyfood.controller;

import com.khoana.funnyfood.payload.DataResponse;
import com.khoana.funnyfood.service.imp.FileServiceImp;
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


    @PostMapping()
    public ResponseEntity<?> createRestaurant(@RequestParam MultipartFile file){
        DataResponse data = new DataResponse();
        boolean isSuccess = fileService.saveFile(file);
        data.setData(isSuccess);
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
