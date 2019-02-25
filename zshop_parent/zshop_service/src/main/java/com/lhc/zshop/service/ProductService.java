package com.lhc.zshop.service;

import com.lhc.zshop.dto.ProductDto;
import com.lhc.zshop.pojo.Product;
import org.apache.commons.fileupload.FileUploadException;

import java.util.List;

public interface ProductService {

    public void add(ProductDto productDto) throws FileUploadException;

    public boolean checkName(String name);

    List<Product> findAll();
}
