package com.lhc.zshop.dao;

import com.lhc.zshop.pojo.Sysuser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysuserDao {

    public List<Sysuser> selectAll();

    public Sysuser selectById(int id);

    public void insert(Sysuser sysuser);

    public void update(Sysuser sysuser);

    public void updateStatus(@Param("id")int id, @Param("isValid")int isValid);

}
