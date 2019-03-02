package com.lhc.zshop.service.impl;

import com.lhc.zshop.dao.ProductDao;
import com.lhc.zshop.dto.ProductDto;
import com.lhc.zshop.params.ProductParam;
import com.lhc.zshop.pojo.Product;
import com.lhc.zshop.pojo.ProductType;
import com.lhc.zshop.service.ProductService;
import com.lhc.zshop.util.StringUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.fileupload.FileUploadException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StreamUtils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;


@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao;

    @Override
    public void add(ProductDto productDto) throws FileUploadException{
        //1.文件上传
        System.out.println(productDto.getFileName());
        String fileName = StringUtils.renameFileName(productDto.getFileName());
        String filePath = productDto.getUploadPath()+"/"+fileName;
        try {
            StreamUtils.copy(productDto.getInputStream(), new FileOutputStream(filePath));
        }catch (IOException e){
            throw new FileUploadException("文件上传失败"+e.getMessage());
        }

        //2.保存到数据库
        try {
            Product product = new Product();
            PropertyUtils.copyProperties(product, productDto);
            product.setImage(filePath);
            ProductType productType = new ProductType();
            productType.setId(productDto.getProductTypeId());
            product.setProductType(productType);

            productDao.insert(product);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    @Override
    public Product findById(int id) {
        return productDao.selectById(id);
    }

    @Override
    public List<Product> findAll() {
        return productDao.selectAll();
    }

    @Override
    public boolean checkName(String name) {
        Product product = productDao.selectByName(name);
        if(product!=null) {
            return false;
        }
        return true;
    }

    @Override
    public void removeById(int id) {
        productDao.deleteById(id);
    }


    @Override
    public void getImage(String path, OutputStream outputStream) {
        try {
            StreamUtils.copy(new FileInputStream(path), outputStream);
        }catch (IOException ex){
            ex.printStackTrace();
        }
    }

    @Override
    public void modify(ProductDto productDto) throws FileUploadException {
        System.out.println(productDto.getFileName());
        String fileName = StringUtils.renameFileName(productDto.getFileName());
        String filePath = productDto.getUploadPath()+"/"+fileName;
        try {
            StreamUtils.copy(productDto.getInputStream(), new FileOutputStream(filePath));
        }catch (IOException e){
            throw new FileUploadException("文件上传失败"+e.getMessage());
        }

        //2.保存到数据库
        try {
            Product product = new Product();
            PropertyUtils.copyProperties(product, productDto);
            product.setImage(filePath);
            ProductType productType = new ProductType();
            productType.setId(productDto.getProductTypeId());
            product.setProductType(productType);
            System.out.println(product.getId());
            productDao.update(product);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    @Override
    public List<Product> findByParams(ProductParam productParam) {
        return productDao.selectByParams(productParam);
    }
}
