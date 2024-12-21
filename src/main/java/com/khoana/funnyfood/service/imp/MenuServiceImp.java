package com.khoana.funnyfood.service.imp;

import com.khoana.funnyfood.payload.request.MenuCreateRequest;
import org.springframework.web.multipart.MultipartFile;

public interface MenuServiceImp {
    boolean createMenu(MenuCreateRequest request, MultipartFile file);

}
