package com.djblancoh.product.controller;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/categories")
@Getter
@Setter
public class CategoryController {

    @Value("${app.test-prop}")
    private String testProp;

    @GetMapping("/test-prop")
    public String getTestProp(){
        return this.testProp;
    }


}
