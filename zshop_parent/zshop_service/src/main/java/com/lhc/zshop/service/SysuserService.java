package com.lhc.zshop.service;

import com.lhc.zshop.pojo.Sysuser;

import java.util.List;

public interface SysuserService {

    public List<Sysuser> findAll();

    public Sysuser findById(int id);

    public void add(Sysuser sysuser);

    public void modify(Sysuser sysuser);

    public void modifyStatus(int id, int isValid);
}
