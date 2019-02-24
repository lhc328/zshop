package com.lhc.zshop.service;

import com.lhc.zshop.dto.ProductDto;
import org.apache.commons.fileupload.FileUploadException;

public interface ProductService {

    public void add(ProductDto productDto) throws FileUploadException;
}
