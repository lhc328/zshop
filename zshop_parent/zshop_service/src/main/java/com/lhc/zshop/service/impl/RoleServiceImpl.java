package com.lhc.zshop.service.impl;

import com.lhc.zshop.dao.RoleDao;
import com.lhc.zshop.pojo.Role;
import com.lhc.zshop.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleDao roleDao;

    @Override
    public List<Role> findAll() {
        return roleDao.selectAll();
    }
}
