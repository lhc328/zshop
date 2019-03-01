package com.lhc.zshop.dao;

import com.lhc.zshop.params.SysuserParam;
import com.lhc.zshop.pojo.Sysuser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysuserDao {

    public List<Sysuser> selectAll();

    public Sysuser selectById(int id);

    public void insert(Sysuser sysuser);

    public void update(Sysuser sysuser);

    public void updateStatus(@Param("id")int id, @Param("isValid")int isValid);

    public List<Sysuser> selectByParams(SysuserParam sysuserParam);

    public Sysuser selectByLoginNameAndPassword(@Param("loginName") String loginName, @Param("password") String password,@Param("isValid") Integer isValid);
}
