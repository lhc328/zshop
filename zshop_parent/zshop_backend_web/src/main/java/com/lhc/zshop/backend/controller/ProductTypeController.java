package com.lhc.zshop.backend.controller;


import com.lhc.zshop.pojo.ProductType;
import com.lhc.zshop.service.ProductTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/backend/productType")
public class ProductTypeController {

    @Autowired
    private ProductTypeService productTypeService;

    @RequestMapping("/findAll")
    public String findAll(Model model){
        //查找所有物品
        List<ProductType> productTypes =  productTypeService.findAll();
        model.addAttribute("productTypes", productTypes);
        return "productTypeManager";
    }
}
