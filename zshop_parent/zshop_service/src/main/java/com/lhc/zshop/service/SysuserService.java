package com.lhc.zshop.service;

import com.lhc.zshop.exception.SysuserNotExistException;
import com.lhc.zshop.params.SysuserParam;
import com.lhc.zshop.pojo.Sysuser;
import com.lhc.zshop.vo.SysuserVo;

import java.util.List;

public interface SysuserService {

    public List<Sysuser> findAll();

    public Sysuser findById(int id);

    public void add(SysuserVo sysuserVo);

    public void modify(Sysuser sysuser);

    public void modifyStatus(int id);

    public List<Sysuser> findParams(SysuserParam sysuserParam);

    public Sysuser login(String loginName, String password) throws SysuserNotExistException;
}
