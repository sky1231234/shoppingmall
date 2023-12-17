package com.project.shop.item.controller;

import com.project.shop.item.domain.Category;
import com.project.shop.item.dto.request.CategoryRequest;
import com.project.shop.item.dto.request.CategoryUpdateRequest;
import com.project.shop.item.dto.response.CategoryResponse;
import com.project.shop.item.dto.response.UserReviewResponse;
import com.project.shop.item.service.CategoryService;
import com.project.shop.item.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping ("/api")
@RequiredArgsConstructor
@Validated
public class CategoryController {
    private final CategoryService categoryService;

    //조회
    @GetMapping("/category")
    @ResponseStatus(HttpStatus.OK)
    public List<Category> categoryFindAll(){
        return categoryService.categoryFindAll();
    }

    @GetMapping("/hello")
    @ResponseStatus(HttpStatus.OK)
    public String hello(){
        return "hello";
    }


    //등록
    @PostMapping("/category")
    @ResponseStatus(HttpStatus.CREATED)
    public void categoryCreate(@RequestBody CategoryRequest categoryRequest){
        categoryService.create(categoryRequest);
    }

    //수정
    @PutMapping("/category/{categoryId}")
    @ResponseStatus(HttpStatus.OK)
    public void categoryUpdate(@PathVariable("categoryId") long categoryId, @RequestBody CategoryUpdateRequest categoryUpdateRequest){
        categoryService.update(categoryId,categoryUpdateRequest);
    }

    //삭제
    @DeleteMapping("/category/{categoryId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void categoryDelete(@PathVariable("categoryId") long categoryId){
        categoryService.delete(categoryId);
    }

}
