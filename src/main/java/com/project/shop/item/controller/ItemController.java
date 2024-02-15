package com.project.shop.item.controller;


import com.project.shop.global.config.security.domain.UserDto;
import com.project.shop.item.dto.response.ItemListResponse;
import com.project.shop.item.dto.response.ItemResponse;
import com.project.shop.item.service.ItemService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping ("/items")
@RequiredArgsConstructor
@Validated
@Tag( name = "ItemController", description = "[사용자] 상품 API")
public class ItemController {

    private final ItemService itemService;

    //상품 전체 조회
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ItemListResponse> findAll(@AuthenticationPrincipal UserDto userDto){
        return itemService.findAll(userDto.getLoginId());
    }

    //상품 상세 조회
    @GetMapping("/{itemId}")
    @ResponseStatus(HttpStatus.OK)
    public ItemResponse detailFind(@AuthenticationPrincipal UserDto userDto, @PathVariable("itemId") long itemId){
        return itemService.detailFind(userDto.getLoginId(), itemId);
    }

}
