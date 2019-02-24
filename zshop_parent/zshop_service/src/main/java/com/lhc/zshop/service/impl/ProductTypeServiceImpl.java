package com.lhc.zshop.service.impl;

import com.lhc.zshop.common.ProductTypeConstant;
import com.lhc.zshop.dao.ProductTypeDao;
import com.lhc.zshop.exception.ProductTypeExistException;
import com.lhc.zshop.pojo.ProductType;
import com.lhc.zshop.service.ProductTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class ProductTypeServiceImpl implements ProductTypeService {

    @Autowired
    private ProductTypeDao productTypeDao;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<ProductType> findAll(){
        return productTypeDao.selectAll();
    }

    public void add(String name) throws ProductTypeExistException{
        ProductType productType =  productTypeDao.selectByName(name);
        if(null != productType){
            throw new ProductTypeExistException("商品类型已存在");
        }
        productTypeDao.insert(name, ProductTypeConstant.PRODUCT_TYPE_ENABLE);

    }

    public ProductType findById(int id){
        return productTypeDao.selectById(id);
    }

    @Override
    public void modifyName(int id, String name) throws ProductTypeExistException{
        ProductType productType =  productTypeDao.selectByName(name);
        if(null != productType){
            throw new ProductTypeExistException("商品类型已存在");
        }
        productTypeDao.updateName(id, name);
    }

    @Override
    public void removeById(int id) {
        productTypeDao.deleteById(id);
    }

    @Override
    public void modifyStatus(int id) {
        ProductType productType = findById(id);
        int status = productType.getStatus();
        if(status == ProductTypeConstant.PRODUCT_TYPE_ENABLE){
            status = ProductTypeConstant.PRODUCT_TYPE_DISABLE;
        }else{
            status = ProductTypeConstant.PRODUCT_TYPE_ENABLE;
        }
        productTypeDao.updateStatus(id, status);
    }

    @Override
    public List<ProductType> findEnable() {
        return productTypeDao.selectByStatus(ProductTypeConstant.PRODUCT_TYPE_ENABLE);
    }
}
