package com.lhc.zshop.service.impl;

import com.lhc.zshop.common.SysuserConstant;
import com.lhc.zshop.dao.SysuserDao;
import com.lhc.zshop.params.SysuserParam;
import com.lhc.zshop.pojo.Role;
import com.lhc.zshop.pojo.Sysuser;
import com.lhc.zshop.service.SysuserService;
import com.lhc.zshop.vo.SysuserVo;
import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;
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
    public void add(SysuserVo sysuserVo) {
        Sysuser sysuser = new Sysuser();
        try {
            Role role = new Role();
            role.setId(sysuserVo.getRoleId());
            PropertyUtils.copyProperties(sysuser, sysuserVo);
            sysuser.setIsValid(SysuserConstant.SYSUSER_VALID);
            sysuser.setCreateDate(new Date());
            sysuser.setRole(role);
            sysuserDao.insert(sysuser);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void modify(Sysuser sysuser) {
        sysuserDao.update(sysuser);
    }

    @Override
    public void modifyStatus(int id) {
        Sysuser sysuser = sysuserDao.selectById(id);
        Integer isValid = sysuser.getIsValid();
        if(isValid== SysuserConstant.SYSUSER_VALID){
            isValid = SysuserConstant.SYSUSER_INVALID;
        }else{
            isValid = SysuserConstant.SYSUSER_VALID;
        }
        sysuserDao.updateStatus(id, isValid);
    }

    @Override
    public List<Sysuser> findParams(SysuserParam sysuserParam) {
        return sysuserDao.selectByParams(sysuserParam);
    }
}
