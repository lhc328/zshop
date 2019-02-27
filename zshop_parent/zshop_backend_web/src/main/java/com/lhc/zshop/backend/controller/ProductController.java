package com.lhc.zshop.backend.controller;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lhc.zshop.backend.vo.ProductVo;
import com.lhc.zshop.common.PaginationConstant;
import com.lhc.zshop.dto.ProductDto;
import com.lhc.zshop.pojo.Product;
import com.lhc.zshop.pojo.ProductType;
import com.lhc.zshop.service.ProductService;
import com.lhc.zshop.service.ProductTypeService;
import com.lhc.zshop.util.ResponseResult;
import javafx.scene.control.Pagination;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public String findAll(Integer pageNum, Model model){
        if(ObjectUtils.isEmpty(pageNum)){
            pageNum = PaginationConstant.PAGE_NUM;
        }
        PageHelper.startPage(pageNum, PaginationConstant.PAGE_SIZE);
        List<Product> products = productService.findAll();
        PageInfo<Product> pageInfo = new PageInfo<Product>(products);
        model.addAttribute("pageInfo", pageInfo);
        return "productManager";
    }

    @RequestMapping("/add")
    public String add(ProductVo productVo, Integer pageNum, HttpSession session, Model model){
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

        return "forward:findAll?pageNum=" + pageNum;
    }

    @RequestMapping("/checkName")
    @ResponseBody
    public Map<String, Object> checkName(String name){
        Map<String, Object> map = new HashMap<>();
        if(productService.checkName(name)){
            map.put("valid", true);
        }else{
            map.put("valid",false);
            map.put("message","商品("+name+")已经存在");
        }
        return map;
    }

    @RequestMapping("/findById")
    @ResponseBody
    public ResponseResult findById(Integer id){
        Product product = productService.findById(id);
        return ResponseResult.success(product);
    }

    @RequestMapping("/getImage")
    public void getImage(String path, OutputStream outputStream){
        productService.getImage(path,outputStream);
    }

    @RequestMapping("/modify")
    public String modify(ProductVo productVo, Integer pageNum ,HttpSession session, Model model){
        String uploadPath = session.getServletContext().getRealPath("/WEB-INF/upload");
        try {
            //将vo转化成dto
            ProductDto productDto = new ProductDto();
            PropertyUtils.copyProperties(productDto, productVo);    //对象间属性的拷贝
            System.out.println(productVo.getFile().getOriginalFilename());
            productDto.setInputStream(productVo.getFile().getInputStream());
            productDto.setFileName(productVo.getFile().getOriginalFilename());
            productDto.setUploadPath(uploadPath);
            productService.modify(productDto);
            model.addAttribute("successMsg", "修改成功");
        }catch (Exception ex){
            ex.printStackTrace();
            model.addAttribute("errorMsg", ex.getMessage());
        }

        return "forward:findAll?pageNum="+pageNum;
    }

    @RequestMapping("/removeById")
    @ResponseBody
    public ResponseResult removeMyId(Integer id){
        productService.removeById(id);
        return ResponseResult.success();
    }
}
