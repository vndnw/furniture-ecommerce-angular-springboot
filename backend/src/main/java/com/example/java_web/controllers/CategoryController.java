package com.example.java_web.controllers;

import com.example.java_web.dtos.CategoryDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/categories")
public class CategoryController {
    //hiển thi tat ca cac categories
    @GetMapping("") //http://localhost:8080/api/v1/categories?page=1&limit=10
    public ResponseEntity<String> getAllCategories(
            @RequestParam("page") int page,
            @RequestParam("limit") int limit

    ) {
        return ResponseEntity.ok(String.format("getAllCategories, page= %d, limit= %d", page, limit));
    }
    @PostMapping("")
    //neu tham so truyen vao la 1 object thi sao? =>> Data Transfer Object = Request Object
    public ResponseEntity<String> insertCategory(CategoryDTO categoryDTO) {
        return ResponseEntity.ok("this is insertcategory");
    }
    @PutMapping("/{id}")
    public ResponseEntity<String> updateCategory(@PathVariable Long id){
        return ResponseEntity.ok("insertcategory with id ="+id);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deteleCategory(@PathVariable Long id){
        return ResponseEntity.ok("deletecategory with id="+id);
    }
}
