package com.lhc.zshop.front.controller;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lhc.zshop.common.PaginationConstant;
import com.lhc.zshop.params.ProductParam;
import com.lhc.zshop.pojo.Product;
import com.lhc.zshop.pojo.ProductType;
import com.lhc.zshop.service.ProductService;
import com.lhc.zshop.service.ProductTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/front/product")
public class ProductController {

    @Autowired
    private ProductTypeService productTypeService;

    @Autowired
    private ProductService productService;

    @RequestMapping("/search")
    public String search(ProductParam productParam, Integer pageNum, Model model){
        if(ObjectUtils.isEmpty(pageNum)){
            pageNum = PaginationConstant.PAGE_NUM;
        }

        PageHelper.startPage(pageNum, PaginationConstant.PAGE_SIZE_FRONT);
        List<Product> products = productService.findByParams(productParam);
        PageInfo<Product> pageInfo = new PageInfo<>(products);
        model.addAttribute("pageInfo", pageInfo);
        return "main";
    }

    @ModelAttribute("productTypes")
    public List<ProductType> loadProductTypes(){
        List<ProductType> productTypes = productTypeService.findEnable();
        return  productTypes;
    }
}
