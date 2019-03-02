package com.lhc.zshop.service;

import com.lhc.zshop.dto.ProductDto;
import com.lhc.zshop.params.ProductParam;
import com.lhc.zshop.pojo.Product;
import org.apache.commons.fileupload.FileUploadException;

import java.io.OutputStream;
import java.util.List;

public interface ProductService {

    public void add(ProductDto productDto) throws FileUploadException;

    public boolean checkName(String name);

    List<Product> findAll();

    Product findById(int id);

    void removeById(int id);

    void getImage(String path, OutputStream outputStream);

    void modify(ProductDto productDto) throws FileUploadException;

    List<Product> findByParams(ProductParam productParam);
}
