package com.lhc.zshop.dao;

import com.lhc.zshop.pojo.Customer;
import org.apache.ibatis.annotations.Param;

public interface CustomerDao {

    public Customer selectByLoginNameAndPassword(@Param("loginName") String loginName,@Param("password") String password,@Param("isValid") Integer isValid);

    public Customer selectByPhone(String phone);
}
