package com.lhc.zshop.exception;

public class ProductTypeExistException extends Exception{
    public ProductTypeExistException() {
        super();
    }

    public ProductTypeExistException(String s) {
        super(s);
    }

    public ProductTypeExistException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public ProductTypeExistException(Throwable throwable) {
        super(throwable);
    }

}
