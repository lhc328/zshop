package com.lhc.zshop.service.impl;

import com.lhc.zshop.dao.SysuserDao;
import com.lhc.zshop.pojo.Sysuser;
import com.lhc.zshop.service.SysuserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class SysuserServiceImpl implements SysuserService {

    @Autowired
    private SysuserDao sysuserDao;

    @Override
    public List<Sysuser> findAll() {
        return sysuserDao.selectAll();
    }

    @Override
    public Sysuser findById(int id) {
        return sysuserDao.selectById(id);
    }

    @Override
    public void add(Sysuser sysuser) {
        sysuserDao.insert(sysuser);
    }

    @Override
    public void modify(Sysuser sysuser) {
        sysuserDao.update(sysuser);
    }

    @Override
    public void modifyStatus(int id, int isValid) {

    }
}
