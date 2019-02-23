package com.lhc.zshop.service;


import com.lhc.zshop.exception.ProductTypeExistException;
import com.lhc.zshop.pojo.ProductType;

import java.util.List;

public interface ProductTypeService {

    List<ProductType> findAll();

    /**
     * 添加商品类型
     * 判断商品是否存在
     *  存在抛异常
     *  不存在添加
     * @param name
     */
    void add(String name) throws ProductTypeExistException;

    /**
     * 通过id查找商品类型
     */
    ProductType findById(int id);
}
