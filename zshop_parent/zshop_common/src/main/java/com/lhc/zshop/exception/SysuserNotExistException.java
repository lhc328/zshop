package com.lhc.zshop.exception;

public class SysuserNotExistException extends Exception{

    public SysuserNotExistException() {
        super();
    }

    public SysuserNotExistException(String s) {
        super(s);
    }

    public SysuserNotExistException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public SysuserNotExistException(Throwable throwable) {
        super(throwable);
    }

}
