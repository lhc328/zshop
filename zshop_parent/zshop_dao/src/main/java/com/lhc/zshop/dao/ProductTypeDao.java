package com.lhc.zshop.dao;

import com.lhc.zshop.pojo.ProductType;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProductTypeDao {

    //查找所有商品类型
    public List<ProductType> selectAll();

    public ProductType selectById(int id);

    public ProductType selectByName(String name);

    public void insert(@Param("name") String name, @Param("status") int status);

    public void updateName(@Param("id") int id, @Param("name") String name);

    public void updateStatus(@Param("id")int id, @Param("status")int status);

    public void deleteById(int id);
}
