package com.lhc.zshop.service.impl;


import com.lhc.zshop.common.CustomerConstant;
import com.lhc.zshop.dao.CustomerDao;
import com.lhc.zshop.exception.LoginErrorException;
import com.lhc.zshop.exception.PhoneNotRegisterException;
import com.lhc.zshop.pojo.Customer;
import com.lhc.zshop.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerDao customerDao;

    @Override
    public Customer login(String loginName, String password) throws LoginErrorException {
        Customer customer = customerDao.selectByLoginNameAndPassword(loginName, password, CustomerConstant.CUSTOMER_VALID);
        if(customer != null){
            return customer;
        }
        throw new LoginErrorException("登录错误");
    }

    @Override
    public Customer findByPhone(String phone)  throws PhoneNotRegisterException {
        Customer customer =customerDao.selectByPhone(phone);
        if(customer != null){
            return customer;
        }
        throw new PhoneNotRegisterException("手机号尚未注册");
    }
}
