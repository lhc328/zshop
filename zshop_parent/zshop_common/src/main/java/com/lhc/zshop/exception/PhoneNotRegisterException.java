package com.lhc.zshop.exception;

public class PhoneNotRegisterException extends Exception {

    public PhoneNotRegisterException() {
        super();
    }

    public PhoneNotRegisterException(String s) {
        super(s);
    }

    public PhoneNotRegisterException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public PhoneNotRegisterException(Throwable throwable) {
        super(throwable);
    }
}
