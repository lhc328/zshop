package com.lhc.zshop.backend.controller;


import com.lhc.zshop.backend.vo.ProductVo;
import com.lhc.zshop.dto.ProductDto;
import com.lhc.zshop.pojo.ProductType;
import com.lhc.zshop.service.ProductService;
import com.lhc.zshop.service.ProductTypeService;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/backend/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductTypeService productTypeService;

    @ModelAttribute("productTypes")
    public List<ProductType> loadProductTypes(){
        List<ProductType> productTypes = productTypeService.findEnable();
        return productTypes;
    }

    @RequestMapping("/findAll")
    public String findAll(){
        return "productManager";
    }

    @RequestMapping("/add")
    public String add(ProductVo productVo,  HttpSession session, Model model){
        String uploadPath = session.getServletContext().getRealPath("/WEB-INF/upload");
        try {
            //将vo转化成dto
            ProductDto productDto = new ProductDto();
            PropertyUtils.copyProperties(productDto, productVo);    //对象间属性的拷贝
            System.out.println(productVo.getFile().getOriginalFilename());
            productDto.setInputStream(productVo.getFile().getInputStream());
            productDto.setFileName(productVo.getFile().getOriginalFilename());
            productDto.setUploadPath(uploadPath);
            productService.add(productDto);
            model.addAttribute("successMsg", "添加成功");
        }catch (Exception ex){
            ex.printStackTrace();
            model.addAttribute("errorMsg", ex.getMessage());
        }

        return "forward:findAll";
    }

    @RequestMapping("/add1")
    public String add1(@Param("name")String name, @Param("file") MultipartFile file, Model model){
        System.out.println(name+file);
        return "productManager";
    }
}
