package com.lhc.zshop.exception;

public class LoginErrorException extends Exception {
    public LoginErrorException() {
        super();
    }

    public LoginErrorException(String s) {
        super(s);
    }

    public LoginErrorException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public LoginErrorException(Throwable throwable) {
        super(throwable);
    }
}
