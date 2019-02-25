package com.lhc.zshop.dao;

import com.lhc.zshop.pojo.Product;
import com.lhc.zshop.pojo.ProductType;

import java.util.List;

public interface ProductDao {

    public void insert(Product product);

    public Product selectByName(String name);

    public List<Product> selectAll();

    public Product selectById(int id);

    public void update(Product product);

    public void deleteById(int id);
}
