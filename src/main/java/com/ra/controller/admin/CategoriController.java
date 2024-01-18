package com.ra.controller.admin;

import com.ra.model.dto.response.CategoryResponse;
import com.ra.model.entity.Category;
import com.ra.service.category.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import  org.springframework.data.domain.Pageable;


@Controller
@RequestMapping("/v1/admin/categories")
public class CategoriController{
    @Autowired
    private CategoryService categoryService;
    @GetMapping("")
    public ResponseEntity<?> getAll(
            @RequestParam(defaultValue = "5",name = "limit") int limit,
            @RequestParam(defaultValue = "0",name = "page") int page,
            @RequestParam(defaultValue = "categoryName" , name = "sort") String sort,
            @RequestParam(defaultValue = "asc" , name = "oder") String oder
    ){
        Pageable pageable;
        if(oder.equals("asc"))
        {
            pageable= (Pageable) PageRequest.of(page,limit, Sort.by(sort).ascending());
        }
        else
        {
            pageable= (Pageable) PageRequest.of(page,limit, Sort.by(sort).descending());
        }
        Page<CategoryResponse> categoryList =categoryService.getAll( pageable);
        return new ResponseEntity<>(categoryList,HttpStatus.OK);
    }
    @PostMapping("")
    public ResponseEntity<?> create(@RequestBody Category category)
    {
        Category categoryNew=categoryService.save(category);
        return new ResponseEntity<>(categoryNew,HttpStatus.CREATED);
    }
}
