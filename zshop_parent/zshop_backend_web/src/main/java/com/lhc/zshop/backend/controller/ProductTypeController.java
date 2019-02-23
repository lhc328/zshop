package com.lhc.zshop.backend.controller;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lhc.zshop.common.PaginationConstant;
import com.lhc.zshop.common.ResponseResultConstant;
import com.lhc.zshop.exception.ProductTypeExistException;
import com.lhc.zshop.pojo.ProductType;
import com.lhc.zshop.service.ProductTypeService;
import com.lhc.zshop.util.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/backend/productType")
public class ProductTypeController {

    @Autowired
    private ProductTypeService productTypeService;

    @RequestMapping("/findAll")
    public String findAll(Integer pageNum, Model model){
        if(ObjectUtils.isEmpty(pageNum)){
            pageNum = PaginationConstant.PAGE_NUM;
        }
        //设置分页
        PageHelper.startPage(pageNum, PaginationConstant.PAGE_SIZE);
        List<ProductType> productTypes =  productTypeService.findAll();
        PageInfo<ProductType> pageInfo = new PageInfo<>(productTypes);
        model.addAttribute("pageInfo", pageInfo);
        return "productTypeManager";
    }

    @RequestMapping("/add")
    @ResponseBody
    public ResponseResult add(String name){
        ResponseResult result = new ResponseResult();
        try {
            productTypeService.add(name);
            result.setStatus(ResponseResultConstant.RESPONSE_STATUS_SUCCESS);
            result.setMessage("添加成功");
        }catch (ProductTypeExistException ex){
            result.setStatus(ResponseResultConstant.RESPONSE_STATUS_FAIL);
            result.setMessage(ex.getMessage());
        }
        return result;
    }

    @RequestMapping("/findById")
    @ResponseBody
    public ResponseResult findById(Integer id){
        ProductType productType = productTypeService.findById(id);
        return ResponseResult.success(productType);
    }
}
