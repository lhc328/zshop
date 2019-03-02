package com.lhc.zshop.service;

import com.lhc.zshop.exception.LoginErrorException;
import com.lhc.zshop.exception.PhoneNotRegisterException;
import com.lhc.zshop.pojo.Customer;

public interface CustomerService {

    public Customer login(String loginName, String password) throws LoginErrorException;

    public Customer findByPhone(String phone)  throws PhoneNotRegisterException;

}
