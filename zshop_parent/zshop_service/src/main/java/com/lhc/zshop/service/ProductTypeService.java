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

    /**
     * 修改商品类型名称
     */
    void modifyName(int id, String name) throws ProductTypeExistException;

    /**
     * 根据id删除商品类型
     *  判断是否存在该商品类型的商品
     *      如果存在则抛异常 不予删除
     */
    void removeById(int id);

    /**
     * 根据id修改商品类型状态
     */
    void modifyStatus(int id);
}
